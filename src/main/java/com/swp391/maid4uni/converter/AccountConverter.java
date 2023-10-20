package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.AccountDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.model.TokenPayload;
import com.swp391.maid4uni.request.RegisterAccountRequest;
import com.swp391.maid4uni.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * The interface Account converter.
 */
@Mapper
public interface AccountConverter {
    /**
     * The constant INSTANCE.
     */
    AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

    /**
     * From account to account dto.
     *
     * @param account the account
     * @return the account dto
     */
    AccountDto fromAccountToAccountDTO(Account account);

    /**
     * From account to account response.
     *
     * @param account the account
     * @return the account response
     */
    AccountResponse fromAccountToAccountResponse(Account account);

//    @Mapping(target = "password", ignore = true)
    Account fromRegisterAccountRequestToAccount(RegisterAccountRequest registerAccountRequest);

    // build token payload từ account
    // dùng default để MapStruct k convert
    default TokenPayload fromAccountToTokenPayload(Account account){
        if (account == null){
            return null;
        }
        return TokenPayload
                .builder()
                .accountId(account.getId())
                .username(account.getUsername())
                .build();
    }
}
