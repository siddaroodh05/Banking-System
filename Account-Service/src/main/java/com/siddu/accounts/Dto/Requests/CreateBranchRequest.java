package com.siddu.accounts.Dto.Requests;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class CreateBranchRequest {

    @NotBlank(message = "provide branch name")
    @Length(min = 10, max = 100)
    private String branchName;
    @NotBlank(message = "provide address")
    @Length(min = 10, max = 100)
    private String branchAddress;
    @NotBlank(message = "provide city name")
    @Length(min = 10, max = 100)
    private String city;
    @NotBlank(message = "provide state name")
    @Length(min = 10, max = 100)
    private String state;
    @NotBlank(message = "provide pincode ")
    @Length(min = 6, max = 6,message = "pincode must be 6 digit")
    private String pincode;
}
