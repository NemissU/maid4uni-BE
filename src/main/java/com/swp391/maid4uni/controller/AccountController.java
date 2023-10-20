package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.LoginByUsernameRequest;
import com.swp391.maid4uni.request.RegisterAccountRequest;
import com.swp391.maid4uni.response.AccountResponse;
import com.swp391.maid4uni.response.LoginByUsernameResponse;
import com.swp391.maid4uni.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Account controller.
 */
@RestController
@RequestMapping(API_PARAMS.API_VERSION)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * Login using username response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping(API_PARAMS.LOGIN_BY_USERNAME)
    public ResponseEntity<LoginByUsernameResponse> loginByUsername(
            @RequestBody LoginByUsernameRequest request) {
        log.info("Start login by username");
        return ResponseEntity.ok(accountService.loginByUsername(request));
    }

    /**
     * Get account list response entity.
     *
     * @return the response entity
     */
    @GetMapping(API_PARAMS.GET_ACCOUNT_LIST)
    public ResponseEntity<List<AccountResponse>> getAccountList() {
        log.info("Start get account list");
        return ResponseEntity.ok(accountService.getAccountList());
    }

    /**
     * Get manager list response entity.
     *
     * @return the response entity
     */
    @GetMapping(API_PARAMS.GET_MANAGER_LIST)
    public ResponseEntity<List<AccountResponse>> getManagerList() {
        log.info("Start get manager list");
        return ResponseEntity.ok(accountService.getManagerList());
    }

    /**
     * Get staff list response entity.
     *
     * @return the response entity
     */
    @GetMapping(API_PARAMS.GET_STAFF_LIST)
    public ResponseEntity<List<AccountResponse>> getStaffList() {
        log.info("Start get staff list");
        return ResponseEntity.ok(accountService.getStaffList());
    }

    @GetMapping(API_PARAMS.GET_CUSTOMER_LIST)
    public ResponseEntity<List<AccountResponse>> getCustomerList() {
        log.info("Start get customer list");
        return ResponseEntity.ok(accountService.getCustomerList());
    }
    @PostMapping(API_PARAMS.REGISTER)
    public ResponseEntity<AccountResponse> register(
            @RequestBody RegisterAccountRequest registerAccountRequest) {
            log.info("Start register");
            return ResponseEntity.ok(accountService.register(registerAccountRequest));
    }
}
