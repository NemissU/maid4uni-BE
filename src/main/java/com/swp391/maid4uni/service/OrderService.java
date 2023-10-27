package com.swp391.maid4uni.service;

import com.swp391.maid4uni.response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getOrderInfoByCustomer(int id);
}
