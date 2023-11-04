package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.ServiceConverter;
import com.swp391.maid4uni.dto.ServiceDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.entity.Service;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.AccountRepository;
import com.swp391.maid4uni.repository.PackageRepository;
import com.swp391.maid4uni.repository.ServiceRepository;
import com.swp391.maid4uni.response.ServiceResponse;
import com.swp391.maid4uni.service.ServiceService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Service service.
 */
@org.springframework.stereotype.Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class ServiceServiceImpl implements ServiceService {
    ServiceRepository serviceRepository;
    AccountRepository accountRepository;
    PackageRepository packageRepository;

    @Override
    public List<ServiceResponse> getAllService(int page) {
        Pageable pageable = PageRequest.of(page,10);
        List<Service> serviceList = serviceRepository.findAllByLogicalDeleteStatusWithOffsetAndLimit(0,pageable);
        List<ServiceResponse> serviceResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(serviceList)) {
            serviceResponseList =
                    serviceList.stream()
                            .map(ServiceConverter.INSTANCE::fromServiceToServiceResponse)
                            .toList();
        }
        return serviceResponseList;
    }

    @Override
    public ServiceResponse createService(ServiceDto serviceDto) {
        //Xử lý tìm kiếm creator
        Account accountByGet = new Account();

        if (!accountRepository.findById(serviceDto.getCreator().getId()).isPresent())
            throw Maid4UniException.notFound("CreatorId does not exist");
        else {
            accountByGet = accountRepository.findById(serviceDto.getCreator().getId()).get();
        }

        //Convert dto -> service entity, set creator tìm kiếm được ở trên
        Service service = ServiceConverter.INSTANCE.fromServiceDtoToService(serviceDto);
        service.setCreator(accountByGet);
        serviceRepository.save(service);
        return ServiceConverter.INSTANCE.fromServiceToServiceResponse(service);
    }

    @Override
    public ServiceResponse updateService(ServiceDto serviceDto, int id) {
        Service service = serviceRepository.findByIdAndLogicalDeleteStatus(id, 0);
        if (service == null)
            throw Maid4UniException.notFound("Service id: " + id + " does not exist");
        service.setName(serviceDto.getName());
        service.setDescription(serviceDto.getDescription());
        service.setPrice(serviceDto.getPrice());
        serviceRepository.save(service);
        reUpdatePackage(service);
        return ServiceConverter.INSTANCE.fromServiceToServiceResponse(service);
    }

    @Override
    public ServiceResponse deleteService(int id) {
        Service service = serviceRepository.findByIdAndLogicalDeleteStatus(id,0);
        if (service == null)
            throw Maid4UniException.notFound("Service id: " + id + " does not exist");
        service.setLogicalDeleteStatus((short) 1);
        serviceRepository.save(service);
        reUpdatePackageAfterDeleteService(service);
        return ServiceConverter.INSTANCE.fromServiceToServiceResponse(service);
    }

    private void reUpdatePackageAfterDeleteService(Service service) {
        List<Package> packages = service.getBelongedPackage();
        for (Package p:packages) {
            p.getServiceList().remove(service);
            p.setPrice(p.getServiceList().stream().mapToDouble(Service::getPrice).sum());
        }
        packageRepository.saveAll(packages);
    }

    private void reUpdatePackage(Service service){
        List<Package> packages = service.getBelongedPackage();
        for (Package p:packages) {
            //Hàm tính sum Price của Package dựa trên List<Service> của Package
            p.setPrice(p.getServiceList().stream().mapToDouble(Service::getPrice).sum());
            packageRepository.save(p);
        }
    }
}
