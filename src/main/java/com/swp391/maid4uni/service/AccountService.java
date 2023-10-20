package com.swp391.maid4uni.service;


import com.swp391.maid4uni.request.LoginByUsernameRequest;
import com.swp391.maid4uni.request.RegisterAccountRequest;
import com.swp391.maid4uni.response.AccountResponse;
import com.swp391.maid4uni.response.LoginByUsernameResponse;

import java.util.List;

/**
 * The interface Account service.
 */
public interface AccountService {

    /**
     * Login by username login by username response.
     *
     * @param request the request
     * @return the login by username response
     */
    LoginByUsernameResponse loginByUsername(LoginByUsernameRequest request);

    /**
     * Gets account list.
     *
     * @return the account list
     */
    List<AccountResponse> getAccountList();

    /**
     * Gets manager list.
     *
     * @return the manager list
     */
    List<AccountResponse> getManagerList();

    /**
     * Gets staff list.
     *
     * @return the staff list
     */
    List<AccountResponse> getStaffList();

    List<AccountResponse> getCustomerList();


    AccountResponse register(RegisterAccountRequest registerAccountRequest);

}
