package com.swp391.maid4uni.service;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.response.PackageResponse;

import java.util.List;


/**
 * The interface Package service.
 */
public interface PackageService {

    /**
     * Gets all package.
     *
     * @return the all package
     */
    List<PackageResponse> getAllPackage();

    /**
     * Create package package dto.
     *
     * @param packageDto the package dto
     * @return the package dto
     */
    PackageDto createPackage(PackageDto packageDto);

    /**
     * Delete package package response.
     *
     * @param id the id
     * @return the package response
     */
    PackageResponse deletePackage(int id);

    /**
     * Update package package response.
     *
     * @param packageDto the package dto
     * @param id         the id
     * @return the package response
     */
    PackageResponse updatePackage(PackageDto packageDto, int id);

    PackageResponse getAPackage(int id);

    List<PackageResponse> getPackageByCategory(int id, int page);
}
