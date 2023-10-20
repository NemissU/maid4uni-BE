package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.LoginByUsernameRequest;
import com.swp391.maid4uni.response.AccountResponse;
import com.swp391.maid4uni.response.LoginByUsernameResponse;
import com.swp391.maid4uni.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * Login using username response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping(API_PARAMS.LOGIN)
    public ResponseEntity<LoginByUsernameResponse> loginUsingUsername(
            @RequestBody LoginByUsernameRequest request) {
        return ResponseEntity.ok(accountService.loginByUsername(request));
    }

    /**
     * Get account list response entity.
     *
     * @return the response entity
     */
    @GetMapping(API_PARAMS.GET_ACCOUNT_LIST)
    public ResponseEntity<List<AccountResponse>> getAccountList(){
        return ResponseEntity.ok(accountService.getAccountList());
    }

    /**
     * Get manager list response entity.
     *
     * @return the response entity
     */
    @GetMapping(API_PARAMS.GET_MANAGER_LIST)
    public ResponseEntity<List<AccountResponse>> getManagerList(){
        return ResponseEntity.ok(accountService.getManagerList());
    }

    /**
     * Get staff list response entity.
     *
     * @return the response entity
     */
    @GetMapping(API_PARAMS.GET_STAFF_LIST)
    public ResponseEntity<List<AccountResponse>> getStaffList(){
        return ResponseEntity.ok(accountService.getStaffList());
    }
}
