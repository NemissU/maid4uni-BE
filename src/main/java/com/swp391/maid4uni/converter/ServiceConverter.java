package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.ServiceDto;
import com.swp391.maid4uni.entity.Service;
import com.swp391.maid4uni.request.CreateServiceRequest;
import com.swp391.maid4uni.response.ServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceConverter {
    ServiceConverter INSTANCE = Mappers.getMapper(ServiceConverter.class);

    ServiceResponse fromServiceToServiceResponse(Service service);

    Service fromServiceDtoToService(ServiceDto serviceDto);

    ServiceDto fromCreateServiceRequestToServiceDto(CreateServiceRequest createServiceRequest);

    ServiceResponse fromServiceDtoToServiceResponse(ServiceDto serviceDto);
}
