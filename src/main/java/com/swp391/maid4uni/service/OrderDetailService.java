package com.swp391.maid4uni.service;

import com.swp391.maid4uni.dto.OrderDetailDto;
import com.swp391.maid4uni.request.OrderDetailRequest;
import com.swp391.maid4uni.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    OrderDetailResponse updateOrderDetail(OrderDetailDto orderDetailDto);

    List<OrderDetailResponse> getOrderDetailByOrderId(int id);
}
