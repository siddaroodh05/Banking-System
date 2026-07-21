package com.siddu.accounts.Controller;


import com.siddu.accounts.Dto.Requests.CreateBranchRequest;
import com.siddu.accounts.Dto.Responses.ApiResponse;
import com.siddu.accounts.Dto.Responses.BranchResponse;
import com.siddu.accounts.services.AccountManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountManagementController {
    private final AccountManagementService accountManagementService;
    public AccountManagementController(AccountManagementService accountManagementService) {
        this.accountManagementService = accountManagementService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/branches")
    public ResponseEntity<ApiResponse<BranchResponse>> createBranch(@RequestBody CreateBranchRequest request) {
        return ResponseEntity.ok(accountManagementService.createBranch(request));
    }



}
