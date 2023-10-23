package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.dto.AccountDto;
import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.AccountService;
import com.swp391.maid4uni.service.PackageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Package controller.
 */
@RestController
@RequestMapping(API_PARAMS.API_VERSION)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping(API_PARAMS.GET_ALL_PACKAGE)
    public ResponseEntity<ResponseObject> getAllPackage(){
        log.info("Start get all package");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","GET ALL PACKAGE SUCCESSFULLY", packageService.getAllPackage())
        );
    }
}
