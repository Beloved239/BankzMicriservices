package com.tobi.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotNull(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    private String name;

    @NotNull(message = "Email should not be empty")
    @Email(message = "Email address should be valid")
    private String email;

    @NotNull(message = "Phone number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
    private String phoneNumber;


    private AccountsDto accountsDto;
}
