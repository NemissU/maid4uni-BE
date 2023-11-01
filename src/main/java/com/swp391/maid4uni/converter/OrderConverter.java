package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.OrderDto;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.entity.Order;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.request.OrderRequest;
import com.swp391.maid4uni.request.PackageDutyRequest;
import com.swp391.maid4uni.response.OrderResponse;
import com.swp391.maid4uni.response.PackageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface OrderConverter {
    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    default OrderResponse fromOrderToOrderResponse(Order order){
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.id( order.getId() );
        orderResponse.price( order.getPrice() );
        orderResponse.address( order.getAddress() );
        orderResponse.createdAt( order.getCreatedAt() );
        orderResponse.duration( order.getDuration() );
        orderResponse.periodType( order.getPeriodType() );
        orderResponse.endDay( order.getEndDay() );
        orderResponse.startDay( order.getStartDay() );
        orderResponse.pkgRes(INSTANCE.map(order.getAPackage()));
        return orderResponse.build();
    };

    OrderDto fromOrderRequestToOrderDto(OrderRequest request);

    @Mapping(target = "workDay", ignore = true)
    Order fromDtoToEntity(OrderDto dto);

    PackageDto map(PackageDutyRequest packageDutyRequest);

    PackageResponse map(Package pkg);

}
