package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.AccountConverter;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.enums.ENUMS;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.model.TokenPayload;
import com.swp391.maid4uni.repository.AccountRepository;
import com.swp391.maid4uni.request.LoginByUsernameRequest;
import com.swp391.maid4uni.response.AccountResponse;
import com.swp391.maid4uni.response.LoginByUsernameResponse;
import com.swp391.maid4uni.service.AccountService;
import com.swp391.maid4uni.ulti.JwtTokenUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Account service.
 */
@Service
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@NoArgsConstructor(force = true)
@Builder
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenUtil jwtTokenUtil;

    /** Login by username */
    @Override
    public LoginByUsernameResponse loginByUsername(LoginByUsernameRequest request) {
        Account account = accountRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Account not found."));

        boolean isAuthentication = passwordEncoder
                .matches(request.getPassword(), account.getPassword());

        if (!isAuthentication) {
            throw Maid4UniException.badRequest("Username or password is incorrect.");
        }

        // build token payload từ account
        TokenPayload tokenPayload = TokenPayload
                .builder()
                .accountId(account.getId())
                .username(account.getUsername())
                .build();

        String accessToken = jwtTokenUtil.generateToken(tokenPayload, ENUMS.ONE_DAY);
        return LoginByUsernameResponse
                .builder()
                .account(AccountConverter.INSTANCE.fromAccountToAccountDTO(account))
                .accessToken(accessToken)
                .build();
    }

    /** Get list account */

    @Override
    public List<AccountResponse> getAccountList() {
        List<Account> accountList = accountRepository.findAll();
        // !CollectionUtils.isEmpty(accountList) trả về true nếu list k rỗng và khác null
        List<AccountResponse> accountResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(accountList)) {
            accountList.stream()
                    .map(AccountConverter.INSTANCE::fromAccountToAccountResponse)
                    .toList();
        }
        return accountResponseList;
    }

    /** Get list manager */

    @Override
    public List<AccountResponse> getManagerList() {
        List<Account> managerList = accountRepository.findByRole(2);
        List<AccountResponse> managerResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(managerList)) {
            managerList.stream()
                    .map(AccountConverter.INSTANCE::fromAccountToAccountResponse)
                    .toList();
        }
        return managerResponseList;
    }

    /** Get list staff */

    @Override
    public List<AccountResponse> getStaffList() {
        List<Account> staffList = accountRepository.findByRole(3);
        List<AccountResponse> staffResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(staffList)) {
            staffList.stream()
                    .map(AccountConverter.INSTANCE::fromAccountToAccountResponse)
                    .toList();
        }
        return staffResponseList;
    }


}
