package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.OrderDto;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.entity.Order;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.entity.Payment;
import com.swp391.maid4uni.request.OrderRequest;
import com.swp391.maid4uni.request.PackageDutyRequest;
import com.swp391.maid4uni.response.OrderResponse;
import com.swp391.maid4uni.response.PackageResponse;
import com.swp391.maid4uni.response.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

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
        orderResponse.orderStatus(order.getOrderStatus());
        orderResponse.payment(INSTANCE.map(order.getPayment()));
        orderResponse.pkgRes(INSTANCE.map(order.getAPackage()));
        return orderResponse.build();
    };

    OrderDto fromOrderRequestToOrderDto(OrderRequest request);

    Order fromDtoToEntity(OrderDto dto);

    PackageDto map(PackageDutyRequest packageDutyRequest);

    PackageResponse map(Package pkg);

    PaymentResponse map(Payment payment);

    default String convertToString(ArrayList<Integer> inputList) {
        StringBuilder sb = new StringBuilder();
        for (Integer element : inputList) {
            sb.append(element).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);  // Xóa dấu ',' cuối cùng
        }
        return sb.toString();
    }

}
