package com.tobi.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "Dollar Bank"
    )
    @NotNull(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "oluwatobi@dollarbank.com"
    )
    @NotNull(message = "Email should not be empty")
    @Email(message = "Email address should be valid")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "08112345678"
    )
    @NotNull(message = "Phone number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
    private String phoneNumber;


    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;
}
