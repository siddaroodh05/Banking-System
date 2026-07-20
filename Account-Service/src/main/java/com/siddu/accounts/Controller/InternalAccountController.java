package com.siddu.accounts.Controller;

import com.siddu.accounts.Dto.Requests.VerifyAccountRequest;
import com.siddu.accounts.Dto.Responses.BranchResponse;
import com.siddu.accounts.Dto.Responses.VerifyAccountResponse;
import com.siddu.accounts.services.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class InternalAccountController {
    private final BankAccountService bankAccountService;

    @Autowired
    InternalAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("accounts/internals/branches")
    public ResponseEntity<Page<BranchResponse>> getBranches(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(bankAccountService.getBranches(page, size));
    }
    @PostMapping("/accounts/internals/verify-receiver")
    public ResponseEntity<VerifyAccountResponse> verifyreciverAccount(@Valid  @RequestBody VerifyAccountRequest request) {
        return ResponseEntity.ok(bankAccountService.verifyreciveraccount(request));

    }
    @PostMapping("/accounts/internals/verify-sender")
    public ResponseEntity<VerifyAccountResponse> verifysenderAccount(@Valid @RequestBody VerifyAccountRequest request) {
        return ResponseEntity.ok(bankAccountService.verifysenderaccount(request));
    }


}
