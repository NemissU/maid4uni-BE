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

/**
 * The interface Package converter.
 */
@Mapper(componentModel = "spring")
public interface PackageConverter {
    /**
     * The constant INSTANCE.
     */
    PackageConverter INSTANCE = Mappers.getMapper(PackageConverter.class);

    /**
     * From package to package dto package dto.
     *
     * @param aPackage the a package
     * @return the package dto
     */
    PackageDto fromPackageToPackageDto(Package aPackage);

    /**
     * From package to package response package response.
     *
     * @param aPackage the a package
     * @return the package response
     */
    PackageResponse fromPackageToPackageResponse(Package aPackage);

    /**
     * Map create service requests list.
     *
     * @param createServiceRequests the create service requests
     * @return the list
     */
    List<ServiceDto> mapCreateServiceRequests(List<CreateServiceRequest> createServiceRequests);

    /**
     * Map service list list.
     *
     * @param serviceList the service list
     * @return the list
     */
    List<ServiceResponse> mapServiceList(List<Service> serviceList);

    /**
     * Map create service request service dto.
     *
     * @param createServiceRequest the create service request
     * @return the service dto
     */
    ServiceDto mapCreateServiceRequest(CreateServiceRequest createServiceRequest);

    /**
     * Map account response.
     *
     * @param account the account
     * @return the account response
     */
    AccountResponse map(Account account);

    /**
     * Map account dto.
     *
     * @param accountDutyRequest the account duty request
     * @return the account dto
     */
    AccountDto map(AccountDutyRequest accountDutyRequest);

    /**
     * From package dto to package package.
     *
     * @param packageDto the package dto
     * @return the package
     */
    Package fromPackageDtoToPackage(PackageDto packageDto);

    /**
     * From create package request to package dto package dto.
     *
     * @param createPackageRequest the create package request
     * @return the package dto
     */
    PackageDto fromCreatePackageRequestToPackageDto(CreatePackageRequest createPackageRequest);

    /**
     * From package dto to package response package response.
     *
     * @param aPackage the a package
     * @return the package response
     */
    PackageResponse fromPackageDtoToPackageResponse(PackageDto aPackage);

    /**
     * From update package request to package dto package dto.
     *
     * @param updatePackageRequest the update package request
     * @return the package dto
     */
    PackageDto fromUpdatePackageRequestToPackageDto(UpdatePackageRequest updatePackageRequest);

    /**
     * Map update service request list list.
     *
     * @param serviceList the service list
     * @return the list
     */
    List<ServiceDto> mapUpdateServiceRequestList(List<UpdateServiceRequest> serviceList);
}
