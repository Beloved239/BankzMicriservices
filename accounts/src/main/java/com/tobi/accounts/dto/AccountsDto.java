package com.tobi.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {


    @Schema(
            description = "Account Number of Dollar Bank account", example = "3454433243"
    )
    @NotEmpty(message = "Account number should not be empty")
    @Pattern(regexp = ("^|[0-9],{10}"), message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of Dollar Bank account", example = "Savings"
    )
    @NotEmpty(message = "Account type should not be null or empty")
    private String accountType;

    @Schema(
            description = "Dollar Bank branch address", example = "123 NewYork"
    )
    @NotEmpty(message = "Branch Address should not be null or empty")
    private String branchAddress;
}
