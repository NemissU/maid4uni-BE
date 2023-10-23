package com.swp391.maid4uni.service;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.request.CreatePackageRequest;
import com.swp391.maid4uni.response.PackageResponse;

import java.util.List;


public interface PackageService {

    List<PackageResponse> getAllPackage();

    PackageDto createPackage(PackageDto packageDto);
}
