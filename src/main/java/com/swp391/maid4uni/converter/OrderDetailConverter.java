package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.OrderDetailDto;
import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.request.OrderDetailRequest;
import com.swp391.maid4uni.response.OrderDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface OrderDetailConverter {
    OrderDetailConverter INSTANCE = Mappers.getMapper(OrderDetailConverter.class);

    OrderDetailDto fromOrderDetailRequestToOrderDetailDto(OrderDetailRequest orderDetailRequest);

    OrderDetail fromOrderDetailDtoToOrderDetail(OrderDetailDto orderDetailDto);

    OrderDetailResponse fromOrderDetailToOrderDetailResponse(OrderDetail orderDetail);

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
