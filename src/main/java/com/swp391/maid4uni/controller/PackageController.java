package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.CreatePackageRequest;
import com.swp391.maid4uni.request.UpdatePackageRequest;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.PackageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Package controller.
 */
@RestController
@RequestMapping(API_PARAMS.API_VERSION)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageController {
    @Autowired
    private PackageService packageService;

    /**
     * Get all package response entity.
     *
     * @return the response entity
     */
    @GetMapping(API_PARAMS.GET_ALL_PACKAGE)
    public ResponseEntity<ResponseObject> getAllPackage() {
        log.info("Start get all package");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                        , "GET ALL PACKAGE SUCCESSFULLY"
                        , packageService.getAllPackage())
        );
    }

    /**
     * Create package response entity.
     *
     * @param createPackageRequest the create package request
     * @return the response entity
     */
    @PostMapping(API_PARAMS.CREATE_PACKAGE)
    public ResponseEntity<ResponseObject> createPackage(
            @RequestBody CreatePackageRequest createPackageRequest) {
        log.info("Start create package");
        PackageDto packageDto = PackageConverter
                .INSTANCE
                .fromCreatePackageRequestToPackageDto(createPackageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                        , "CREATE PACKAGE SUCCESSFULLY"
                        , PackageConverter
                        .INSTANCE
                        .fromPackageDtoToPackageResponse(packageService.createPackage(packageDto)))
        );
    }

    /**
     * Delete package response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(API_PARAMS.DELETE_PACKAGE)
    public ResponseEntity<ResponseObject> deletePackage(@PathVariable int id) {
        log.info("Start delete package");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                        , "DELETE PACKAGE SUCCESSFULLY"
                        , packageService.deletePackage(id))
        );
    }

    /**
     * Update package response entity.
     *
     * @param id                   the id
     * @param updatePackageRequest the update package request
     * @return the response entity
     */
    @PutMapping(API_PARAMS.UPDATE_PACKAGE)
    public ResponseEntity<ResponseObject> updatePackage(
            @PathVariable int id,
            @RequestBody UpdatePackageRequest updatePackageRequest) {
        log.info("Start update package");
        PackageDto packageDto = PackageConverter
                .INSTANCE
                .fromUpdatePackageRequestToPackageDto(updatePackageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                ,"UPDATE PACKAGE SUCCESSFULLY"
                , packageService.updatePackage(packageDto, id))
        );
    }
}
