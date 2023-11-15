package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.dto.ServiceDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.enums.Category;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.AccountRepository;
import com.swp391.maid4uni.repository.PackageRepository;
import com.swp391.maid4uni.repository.ServiceRepository;
import com.swp391.maid4uni.response.PackageResponse;
import com.swp391.maid4uni.service.PackageService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Package service.
 */
@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class PackageServiceImpl implements PackageService {
    PackageRepository packageRepository;
    AccountRepository accountRepository;
    ServiceRepository serviceRepository;

    @Override
    public List<PackageResponse> getAllPackage(int page) {
        Pageable p = PageRequest.of(page, 10);
        List<Package> packageList = packageRepository.findAllByLogicalDeleteStatusWithOffsetAndLimit(0,p);
        List<PackageResponse> packageResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(packageList)) {
            packageResponseList =
                    packageList.stream()
                            .map(PackageConverter.INSTANCE::fromPackageToPackageResponse)
                            .toList();
        }
        return packageResponseList;
    }

    @Override
    public PackageDto createPackage(PackageDto packageDto) {
        Account accountByGet = new Account();

        if (!accountRepository.findById(packageDto.getCreator().getId()).isPresent())
            throw Maid4UniException.notFound("CreatorId does not exist");
        else {
            accountByGet = accountRepository.findById(packageDto.getCreator().getId()).get();
        }
        List<com.swp391.maid4uni.entity.Service> serviceListByGet = new ArrayList<>();
        for (ServiceDto serviceDto : packageDto.getServiceList()) {
            com.swp391.maid4uni.entity.Service service = serviceRepository
                    .findByIdAndLogicalDeleteStatus(serviceDto.getId(), 0);
            if (service != null)
                serviceListByGet.add(service);
        }
        if (serviceListByGet.isEmpty())
            throw Maid4UniException.notFound("List service can't be empty");
        Package aPackage = PackageConverter
                .INSTANCE
                .fromPackageDtoToPackage(packageDto);
        aPackage.setPrice(getPriceFromListService(serviceListByGet));
        aPackage.setCreator(accountByGet);
        aPackage.setServiceList(serviceListByGet);
        packageRepository.save(aPackage);
        return PackageConverter.INSTANCE.fromPackageToPackageDto(aPackage);
    }

    @Override
    public PackageResponse deletePackage(int id) {
        Optional<Package> aPackage = packageRepository.findById(id);
        if(aPackage.isPresent() && aPackage.get().getLogicalDeleteStatus() == 0) {
            aPackage.get().setLogicalDeleteStatus((short) 1);
            packageRepository.save(aPackage.get());
        }
        else
            throw Maid4UniException.notFound("Package id " + id + " does not exist");
        return PackageConverter.INSTANCE.fromPackageToPackageResponse(aPackage.get());
    }

    @Override
    public PackageResponse updatePackage(PackageDto packageDto, int id) {
        Optional<Package> aPackage = packageRepository.findById(id);
        //Kiểm tra id package và logicalStatus
        if(aPackage.isPresent() && aPackage.get().getLogicalDeleteStatus() == 0) {

            //Convert packageDto thành package
            Package uPackage = PackageConverter.INSTANCE.fromPackageDtoToPackage(packageDto);

            //Update vào package có id được truyền vào
            aPackage.get().setServiceList(uPackage.getServiceList());
            aPackage.get().setName(uPackage.getName());
            aPackage.get().setDescription(uPackage.getDescription());
            aPackage.get().setImageUrl(uPackage.getImageUrl());
            packageRepository.save(aPackage.get());
        }
            else
                throw Maid4UniException.notFound("Package id " + id + " does not exist");
        return PackageConverter.INSTANCE.fromPackageToPackageResponse(aPackage.get());
    }

    @Override
    public PackageResponse getAPackage(int id) {
        Package aPackage = packageRepository.findByIdAndLogicalDeleteStatus(id,0);
        if(aPackage == null){
            throw Maid4UniException.notFound("Package id " + id + " does not exist");
        }
        return PackageConverter.INSTANCE.fromPackageToPackageResponse(aPackage);
    }

    @Override
    public List<PackageResponse> getPackageByCategory(int id, int page) {
        Category c = Category.values()[id];
        Pageable pageable = PageRequest.of(page, 10);
        List<Package> packages = packageRepository.findByCategoryAndLogicalDeleteStatusWithOffsetAndLimit(c,0,pageable);
        List<PackageResponse> packageResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(packages)) {
            packageResponseList =
                    packages.stream()
                            .map(PackageConverter.INSTANCE::fromPackageToPackageResponse)
                            .toList();
        }
        return packageResponseList;
    }

    @Override
    public List<PackageResponse> getMostPopularPackages() {
        List<Package> packages = packageRepository.findTop3PackagesWithMostOrders();
        List<PackageResponse> packageResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(packages)) {
            packageResponseList =
                    packages.stream()
                            .map(PackageConverter.INSTANCE::fromPackageToPackageResponse)
                            .toList();
        }
        return packageResponseList;
    }

    private double getPriceFromListService(List<com.swp391.maid4uni.entity.Service> serviceList) {
        double sum = 0;
        for (com.swp391.maid4uni.entity.Service s : serviceList) {
            sum+=s.getPrice();
        }
        return sum;
    }
}
