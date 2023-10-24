package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.AccountDto;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.dto.ServiceDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.entity.Service;
import com.swp391.maid4uni.request.*;
import com.swp391.maid4uni.response.AccountResponse;
import com.swp391.maid4uni.response.PackageResponse;
import com.swp391.maid4uni.response.ServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackageConverter {
    PackageConverter INSTANCE = Mappers.getMapper(PackageConverter.class);

    PackageDto fromPackageToPackageDto(Package aPackage);

    PackageResponse fromPackageToPackageResponse(Package aPackage);

    List<ServiceDto> mapCreateServiceRequests(List<CreateServiceRequest> createServiceRequests);

    List<ServiceResponse> mapServiceList(List<Service> serviceList);

    ServiceDto mapCreateServiceRequest(CreateServiceRequest createServiceRequest);

    AccountResponse map(Account account);

    AccountDto map(AccountDutyRequest accountDutyRequest);

    Package fromPackageDtoToPackage(PackageDto packageDto);

    PackageDto fromCreatePackageRequestToPackageDto(CreatePackageRequest createPackageRequest);

    PackageResponse fromPackageDtoToPackageResponse(PackageDto aPackage);

    PackageDto fromUpdatePackageRequestToPackageDto(UpdatePackageRequest updatePackageRequest);

    List<ServiceDto> mapUpdateServiceRequestList(List<UpdateServiceRequest> serviceList);
}
