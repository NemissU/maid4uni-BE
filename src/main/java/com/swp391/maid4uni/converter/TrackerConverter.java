package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.TrackerDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Tracker;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrackerConverter {
    TrackerConverter INSTANCE = Mappers.getMapper(TrackerConverter.class);

    Tracker fromTrackerDtoToTracker(TrackerDto trackerDto);
}
