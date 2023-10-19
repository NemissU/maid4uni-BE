package com.swp391.maid4uni.service;


import com.swp391.maid4uni.request.AccountRequest;
import com.swp391.maid4uni.response.AccountResponse;

public interface AccountService{
    AccountResponse loginUsingAccount(AccountRequest request);
}
