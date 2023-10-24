package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.ServiceDto;
import com.swp391.maid4uni.entity.Service;
import com.swp391.maid4uni.request.CreateServiceRequest;
import com.swp391.maid4uni.response.ServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface Service converter.
 */
@Mapper(componentModel = "spring")
public interface ServiceConverter {
    /**
     * The constant INSTANCE.
     */
    ServiceConverter INSTANCE = Mappers.getMapper(ServiceConverter.class);

    /**
     * From service to service response service response.
     *
     * @param service the service
     * @return the service response
     */
    ServiceResponse fromServiceToServiceResponse(Service service);

    /**
     * From service dto to service service.
     *
     * @param serviceDto the service dto
     * @return the service
     */
    Service fromServiceDtoToService(ServiceDto serviceDto);

    /**
     * From create service request to service dto service dto.
     *
     * @param createServiceRequest the create service request
     * @return the service dto
     */
    ServiceDto fromCreateServiceRequestToServiceDto(CreateServiceRequest createServiceRequest);

    /**
     * From service dto to service response service response.
     *
     * @param serviceDto the service dto
     * @return the service response
     */
    ServiceResponse fromServiceDtoToServiceResponse(ServiceDto serviceDto);
}
