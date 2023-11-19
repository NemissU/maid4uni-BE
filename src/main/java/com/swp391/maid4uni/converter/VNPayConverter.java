package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.entity.Payment;
import com.swp391.maid4uni.response.VNPayResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VNPayConverter {
    VNPayConverter INSTANCE = Mappers.getMapper(VNPayConverter.class);
    VNPayResponse fromPayMentToVNPayResponse(Payment payment);
}
