package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.AccountConverter;
import com.swp391.maid4uni.converter.TrackerConverter;
import com.swp391.maid4uni.dto.TrackerDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Tracker;
import com.swp391.maid4uni.repository.AccountRepository;
import com.swp391.maid4uni.repository.TrackerRepository;
import com.swp391.maid4uni.service.TrackerService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class TrackerServiceImpl implements TrackerService {
    TrackerRepository trackerRepository;
    AccountRepository accountRepository;

    @Override
    public void createTrackerForStaff(int staffId) {
        Account staff = accountRepository.findAccountByIdAndLogicalDeleteStatus(staffId,0);
        Tracker tracker = trackerRepository.findByAccount(staff);
        if (tracker == null && staff != null){
            TrackerDto trackerDto = TrackerDto
                    .builder()
                    .account(AccountConverter.INSTANCE.fromAccountToAccountDTO(staff))
                    .taskDone(0)
                    .build();
            Tracker trackerDtoConverTracker = TrackerConverter.INSTANCE.fromTrackerDtoToTracker(trackerDto);
            trackerRepository.save(trackerDtoConverTracker);
        }
    }
}
