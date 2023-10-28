package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.OrderConverter;
import com.swp391.maid4uni.dto.OrderDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Order;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.AccountRepository;
import com.swp391.maid4uni.repository.OrderRepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private AccountRepository accountRepository;

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
        Order order = converter.fromDtoToEntity(dto);
        order.setCustomer(getCustomer);
        orderRepository.save(order);
        return OrderConverter.INSTANCE.fromOrderToOrderResponse(order);
    }


}
