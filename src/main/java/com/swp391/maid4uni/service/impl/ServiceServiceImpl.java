package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.converter.ServiceConverter;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.entity.Service;
import com.swp391.maid4uni.repository.ServiceRepository;
import com.swp391.maid4uni.response.PackageResponse;
import com.swp391.maid4uni.response.ServiceResponse;
import com.swp391.maid4uni.service.ServiceService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class ServiceServiceImpl implements ServiceService {
    ServiceRepository serviceRepository;

    @Override
    public List<ServiceResponse> getAllService() {
        List<Service> serviceList = serviceRepository.findAllByLogicalDeleteStatus(0);
        List<ServiceResponse> serviceResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(serviceList)) {
            serviceResponseList =
                    serviceList.stream()
                            .map(ServiceConverter.INSTANCE::fromServiceToServiceResponse)
                            .toList();
        }
        return serviceResponseList;
    }
}
