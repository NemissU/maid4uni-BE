package com.swp391.maid4uni.service;

import com.swp391.maid4uni.dto.ServiceDto;
import com.swp391.maid4uni.request.UpdateServiceRequest;
import com.swp391.maid4uni.response.ServiceResponse;

import java.util.List;

/**
 * The interface Service service.
 */
public interface ServiceService {
    /**
     * Gets all service.
     *
     * @return the all service
     */
    List<ServiceResponse> getAllService();

    /**
     * Create service service response.
     *
     * @param serviceDto the service dto
     * @return the service response
     */
    ServiceResponse createService(ServiceDto serviceDto);

    ServiceResponse updateService(ServiceDto ServiceDto, int id);

    ServiceResponse deleteService(int id);
}
