package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.converter.ServiceConverter;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.dto.ServiceDto;
import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.CreateServiceRequest;
import com.swp391.maid4uni.request.UpdateServiceRequest;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.ServiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @CrossOrigin(origins = "*")
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

    /**
     * Get all service response entity.
     *
     * @return the response entity
     */
    @GetMapping(API_PARAMS.GET_ALL_SERVICE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ResponseObject> getAllService(){
        log.info("Start get all service");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                        ,"GET ALL SERVICE SUCCESSFULLY"
                        ,serviceService.getAllService())
        );
    }

    /**
     * Update service response entity.
     *
     * @param id                   the id
     * @param updateServiceRequest the update service request
     * @return the response entity
     */
    @PutMapping(API_PARAMS.UPDATE_SERVICE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ResponseObject> updateService(@PathVariable int id,
                                                        @RequestBody UpdateServiceRequest updateServiceRequest){
        log.info("Start update service");
        ServiceDto serviceDto = ServiceConverter.INSTANCE.fromUpdateServiceRequestToServiceDto(updateServiceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                        ,"UPDATE SERVICE SUCCESSFULLY"
                        ,serviceService.updateService(serviceDto,id))
        );
    }

    /**
     * Delete service response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(API_PARAMS.DELETE_SERVICE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ResponseObject> deleteService(@PathVariable int id){
        log.info("Start delete service");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","DELETE SERVICE SUCCESSFULLY",serviceService.deleteService(id))

        );
    }
}
