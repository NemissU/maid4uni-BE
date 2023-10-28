package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.AccountConverter;
import com.swp391.maid4uni.converter.OrderConverter;
import com.swp391.maid4uni.converter.TaskConverter;
import com.swp391.maid4uni.dto.*;
import com.swp391.maid4uni.entity.*;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.AccountRepository;
import com.swp391.maid4uni.repository.OrderDetailRepository;
import com.swp391.maid4uni.repository.OrderRepository;
import com.swp391.maid4uni.repository.TaskRepository;
import com.swp391.maid4uni.request.OrderRequest;
import com.swp391.maid4uni.response.OrderResponse;
import com.swp391.maid4uni.service.OrderService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private AccountRepository accountRepository;
    private TaskRepository taskRepository;
    private OrderDetailRepository orderDetailRepository;
    private OrderConverter converter = OrderConverter.INSTANCE;

    @Override
    public List<OrderResponse> getOrderInfoByCustomer(int id) {
        List<Order> orderList = orderRepository.findAllByCustomerIdAndLogicalDeleteStatus(id, (short) 0);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderList)) {
            orderResponseList = orderList.stream()
                    .map(converter::fromOrderToOrderResponse)
                    .toList();
        }
        return orderResponseList;
    }

    @Override
    public OrderResponse createOrder(OrderDto dto) {
        Account getCustomer = accountRepository
                .findAccountByIdAndLogicalDeleteStatus(dto.getCustomer().getId(), 0);
        if (getCustomer == null) {
            throw Maid4UniException.notFound("Not found Customer info.");
        }
        dto.setTime(LocalDateTime.now());

        if (dto.getPeriodType().equals("ONE_MONTH")) {
            dto.setEndDay(dto.getStartDay().plus(Duration.ofDays(30))); // fix cứng, logic tính sau
            for (int i = 0; i < 4; i++) {
//                createOrderDetail(dto);
            }

        } else {
            dto.setEndDay(dto.getStartDay().plus(Duration.ofDays(60))); // fix cứng, logic tính sau
            for (int i = 0; i < 4; i++) {
            }
        }
        // workday sẽ handle = cách chạy for rồi cộng thêm dần dần vào
        // for

        Order order = converter.fromDtoToEntity(dto);
        order.setCustomer(getCustomer);
        orderRepository.save(order);
        createOrderDetail(order);
        // payment set sau khi thanh toán thành công
        return OrderConverter.INSTANCE.fromOrderToOrderResponse(order);
    }

    public OrderDetail createOrderDetail(Order order) {
        Duration d = Duration.ofHours(order.getDuration());
        OrderDetail detail = OrderDetail
                .builder()
                .order(order)
                //todo handle logic sau
                .status(false)
                .startTime(order.getStartTime())
                .endTime(order.getStartTime().plus(d))
                .build();
        List<Task> taskList = new ArrayList<>();
        for (com.swp391.maid4uni.entity.Service item: order.getAPackage().getServiceList()) {
            List<Account> staffList = accountRepository.findByRoleAndLogicalDeleteStatus(Role.STAFF, (short) 0);
            List<Tracker> trackerList = new ArrayList<>();
            for (Account staff: staffList) {
                trackerList.add(staff.getTracker());
            }
            Task task = Task
                    .builder()
                    .status(false)
                    .service(item)
                    //todo handle list
                    .staffs(staffList)
                    .belongedTrackers(trackerList)
                    .build();
            //todo
            // sửa cái mớ này lại
            taskRepository.save(task);
        }
        orderDetailRepository.save(detail);
        return detail;
    }

}
