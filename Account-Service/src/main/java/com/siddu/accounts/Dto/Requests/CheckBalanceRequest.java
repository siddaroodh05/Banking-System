package com.siddu.accounts.Dto.Requests;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class CheckBalanceRequest {
    @NotBlank(message = "account number must be 12 digits")
    @Length(min = 12, max = 12)
    String accountnumber;
}
