package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.converter.ServiceConverter;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.dto.ServiceDto;
import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.CreateServiceRequest;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.ServiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Service controller.
 */
@RestController
@RequestMapping(API_PARAMS.API_VERSION)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceController {
    @Autowired
    ServiceService serviceService;

    /**
     * Create service response entity.
     *
     * @param createServiceRequest the create service request
     * @return the response entity
     */
    @PostMapping(API_PARAMS.CREATE_SERVICE)
    public ResponseEntity<ResponseObject> createService(@RequestBody CreateServiceRequest createServiceRequest){
        log.info("Start create package");
        ServiceDto serviceDto = ServiceConverter
                .INSTANCE
                .fromCreateServiceRequestToServiceDto(createServiceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                        ,"CREATE PACKAGE SUCCESSFULLY"
                        ,serviceService.createService(serviceDto))
        );
    }
}
