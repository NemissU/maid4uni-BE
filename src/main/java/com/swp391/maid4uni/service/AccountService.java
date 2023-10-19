package com.swp391.maid4uni.service;


public interface AccountService{
    AccountDTOResponse createAccount(AccountDTOCreate accountDTOCreate);

    LoginDTOResponse login(LoginDTORequest loginDTORequest);
}
