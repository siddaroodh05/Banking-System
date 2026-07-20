package com.siddu.accounts.Dto.Responses;

import com.siddu.accounts.Enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerifyAccountResponse {
    String accountNumber;
    AccountStatus status;

}
