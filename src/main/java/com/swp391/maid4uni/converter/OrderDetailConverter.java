package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.OrderDetailDto;
import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.entity.Task;
import com.swp391.maid4uni.request.OrderDetailRequest;
import com.swp391.maid4uni.response.OrderDetailResponse;
import com.swp391.maid4uni.response.TaskDutyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderDetailConverter {
    OrderDetailConverter INSTANCE = Mappers.getMapper(OrderDetailConverter.class);

    OrderDetailDto fromOrderDetailRequestToOrderDetailDto(OrderDetailRequest orderDetailRequest);

    OrderDetail fromOrderDetailDtoToOrderDetail(OrderDetailDto orderDetailDto);

    default ArrayList<Integer> convertWorkDayToArrayList(String inputString) {
        if (inputString == null || inputString.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String[] stringArray = inputString.trim().split(",");
        List<Integer> integerList = new ArrayList<>();

        for (String s : stringArray) {
            try {
                // Thử chuyển đổi từ chuỗi sang số
                int number = Integer.parseInt(s.trim());
                integerList.add(number);
            } catch (NumberFormatException e) {
                // Xử lý lỗi chuyển đổi (có thể log và bỏ qua hoặc xử lý tùy ý)
                e.printStackTrace();
            }
        }
        return new ArrayList<>(integerList);
    }

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
    TaskDutyResponse map(Task task);

    OrderDetailResponse fromOrderDetailToOrderDetailResponse(OrderDetail orderDetail);


}
