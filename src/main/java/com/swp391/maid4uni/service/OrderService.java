package com.swp391.maid4uni.service;

import com.swp391.maid4uni.dto.OrderDto;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.request.OrderRequest;
import com.swp391.maid4uni.request.UpdateOrderRequest;
import com.swp391.maid4uni.response.OrderResponse;
import com.swp391.maid4uni.response.ResponseObject;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderResponse> getOrderInfoByCustomer(int id);

    OrderResponse createOrder(OrderDto dto);

    ResponseObject updateOrderStatus(UpdateOrderRequest request);

    List<OrderResponse> getAllOrder(int page);

    Map<String, Double> getTotalPriceByMonth(int month);

    Map<String, Double> getTotalPriceByMonthOfPackage(int month);
}
