package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.FeedbackConverter;
import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.converter.ServiceConverter;
import com.swp391.maid4uni.entity.Feedback;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.repository.PackageRepository;
import com.swp391.maid4uni.response.FeedbackResponse;
import com.swp391.maid4uni.response.PackageResponse;
import com.swp391.maid4uni.response.ServiceResponse;
import com.swp391.maid4uni.service.PackageService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class PackageServiceImpl implements PackageService {
    PackageRepository packageRepository;

    @Override
    public List<PackageResponse> getAllPackage() {
        List<Package> packageList = packageRepository.findAllByLogicalDeleteStatus(0);
        List<PackageResponse> packageResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(packageList)) {
            packageResponseList =
                    packageList.stream()
                            .map(PackageConverter.INSTANCE::fromPackageToPackageResponse)
                            .toList();
        }
        return packageResponseList;
    }
}
