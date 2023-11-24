package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.OrderDetailConverter;
import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.dto.OrderDetailDto;
import com.swp391.maid4uni.entity.Order;
import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.enums.OrderStatus;
import com.swp391.maid4uni.enums.Status;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.OrderDetailRepository;
import com.swp391.maid4uni.repository.OrderRepository;
import com.swp391.maid4uni.repository.TaskRepository;
import com.swp391.maid4uni.response.OrderDetailResponse;
import com.swp391.maid4uni.response.PackageResponse;
import com.swp391.maid4uni.service.OrderDetailService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailRepository orderDetailRepository;
    TaskRepository taskRepository;
    OrderRepository orderRepository;

    @Override
    public OrderDetailResponse updateOrderDetail(OrderDetailDto orderDetailDto) {
        Optional<OrderDetail> oldOrderDetail = orderDetailRepository.findById(orderDetailDto.getId());
        if (oldOrderDetail.isPresent()) {
            OrderDetail updateOrderDetail = OrderDetailConverter.INSTANCE.fromOrderDetailDtoToOrderDetail(orderDetailDto);
            /** UPDATE Ở ĐÂY CÓ NGHĨA LÀ UPDATE STATUS -> VÀ CHỈ UPDATE STATUS.CANCEL(CUSTOMER HỦY/MANAGER HỦY)*/
            oldOrderDetail.get().setStatus(updateOrderDetail.getStatus());
            oldOrderDetail.get().getTaskList().clear(); //Clear taskList này là do update cancel
            orderDetailRepository.save(oldOrderDetail.get());

            /** NẾU MUỐN UPDATE CÁC STATUS KHÁC VÀ XÓA TASK HÔM ĐÓ THÌ NHỚ THÊM &&*/
            if (oldOrderDetail.get().getStatus().equals(Status.CANCEL)){
                deleteAssociatedTask(oldOrderDetail.get());
            }
            Order orderValid = oldOrderDetail.get().getOrder();
            List<OrderDetail> orderDetails = orderValid.getOrderDetailList();
            if ( orderDetails.get(orderDetails.size() - 1).equals(oldOrderDetail.get())) {
                orderValid.setOrderStatus(OrderStatus.DONE);
                orderRepository.save(orderValid);
            }
        } else throw Maid4UniException.notFound("Order detail id " + orderDetailDto.getId() + " does not exist");
        return OrderDetailConverter.INSTANCE.fromOrderDetailToOrderDetailResponse(oldOrderDetail.get());
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailByOrderId(int id) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderIdAndLogicalDeleteStatus(id, (short) 0);
        List<OrderDetailResponse> responseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderDetailList)) {
            responseList =
                    orderDetailList.stream()
                            .map(OrderDetailConverter.INSTANCE::fromOrderDetailToOrderDetailResponse)
                            .toList();
        }
        return responseList;

    }

    private void deleteAssociatedTask(OrderDetail orderDetail){
        taskRepository.deleteByorderDetailId(orderDetail.getId());
    }

}
