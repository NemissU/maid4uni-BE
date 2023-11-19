package com.swp391.maid4uni.service;

import com.swp391.maid4uni.dto.PaymentDto;
import com.swp391.maid4uni.request.VNPayRequest;
import com.swp391.maid4uni.response.PaymentResponse;
import com.swp391.maid4uni.response.VNPayResponse;

public interface VNPayService {

    String createPayment(int orderTotal, String orderInfo);

    VNPayResponse getVNPayPayment(PaymentDto dto, int orderId);
}
