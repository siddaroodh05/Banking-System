package com.siddu.accounts.Dto.Responses;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BranchResponse {
    private String branchName;
    private String IfscCode;
    private boolean Status;
    private String AddressLine;
    private String City;
    private String pincode;
}
