package com.swp391.maid4uni.service;

import com.swp391.maid4uni.dto.PaymentDto;
import com.swp391.maid4uni.response.PaymentResponse;

public interface VNPayService {

    String createPayment(int orderTotal, String orderInfo);

    String getVNPayPayment(PaymentDto dto, int orderId);
}
