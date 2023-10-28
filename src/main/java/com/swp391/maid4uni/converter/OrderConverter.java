package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.OrderDetailDto;
import com.swp391.maid4uni.dto.OrderDto;
import com.swp391.maid4uni.entity.Order;
import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.request.OrderRequest;
import com.swp391.maid4uni.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface OrderConverter {
    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    OrderResponse fromOrderToOrderResponse(Order order);

    OrderDto fromRequestToDto(OrderRequest request);

    Order fromDtoToEntity(OrderDto dto);

}
