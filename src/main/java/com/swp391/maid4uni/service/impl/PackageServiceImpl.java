package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.dto.ServiceDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Package;
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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<PackageResponse> getAllPackage() {
        List<Package> packageList = packageRepository.findAllByLogicalDeleteStatus(0);
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
            return PackageConverter.INSTANCE.fromPackageToPackageResponse(aPackage.get());
        }
        else
            throw Maid4UniException.notFound("Package id " + id + " does not exist");
    }

    private double getPriceFromListService(List<com.swp391.maid4uni.entity.Service> serviceList) {
        double sum = 0;
        for (com.swp391.maid4uni.entity.Service s : serviceList) {
            sum+=s.getPrice();
        }
        return sum;
    }
}
