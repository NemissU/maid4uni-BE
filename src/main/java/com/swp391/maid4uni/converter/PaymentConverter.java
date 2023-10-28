package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.PaymentDto;
import com.swp391.maid4uni.entity.Payment;
import com.swp391.maid4uni.response.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentConverter {
    PaymentConverter INSTANCE = Mappers.getMapper(PaymentConverter.class);

    Payment fromDtoToEntity(PaymentDto dto);

    PaymentResponse fromEntityToResponse(Payment payment);
}
