package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.TrackerDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Tracker;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface TrackerConverter {
    TrackerConverter INSTANCE = Mappers.getMapper(TrackerConverter.class);

    Tracker fromTrackerDtoToTracker(TrackerDto trackerDto);

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
