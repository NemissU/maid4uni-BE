package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.request.AccountRequest;
import com.swp391.maid4uni.response.AccountResponse;
import com.swp391.maid4uni.service.AccountService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@NoArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Override
    public AccountResponse loginUsingAccount(AccountRequest request) {
        return null;
    }
}
