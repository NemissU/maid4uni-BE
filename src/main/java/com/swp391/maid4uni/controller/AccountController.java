package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.AccountRequest;
import com.swp391.maid4uni.response.AccountResponse;
import com.swp391.maid4uni.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_PARAMS.API_VERSION)
public class AccountController {
    private final AccountService accountService;

    @PostMapping(API_PARAMS.LOGIN)
    public ResponseEntity<AccountResponse> loginUsingAccount(
            @RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.loginUsingAccount(request));
    }


}
