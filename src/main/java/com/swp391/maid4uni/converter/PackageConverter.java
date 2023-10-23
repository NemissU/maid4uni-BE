package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.entity.Service;
import com.swp391.maid4uni.response.PackageResponse;
import com.swp391.maid4uni.response.ServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackageConverter {
    PackageConverter INSTANCE = Mappers.getMapper(PackageConverter.class);

    PackageResponse fromPackageToPackageResponse(Package aPackage);

    List<ServiceResponse> map(List<Service> serviceList);

}
