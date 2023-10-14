package com.tobi.accounts.controller;

import com.tobi.accounts.constant.AccountConstants;
import com.tobi.accounts.dto.AccountsDto;
import com.tobi.accounts.dto.CustomerDto;
import com.tobi.accounts.dto.ErrorResponseDto;
import com.tobi.accounts.dto.ResponseDto;
import com.tobi.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author DollarBank
 * */

@Tag(
        name = "CRUD REST APIs for Accounts in DollarBank",
        description = "CRUD REST APIs in DollarBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    private IAccountService iAccountService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside DollarBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
    iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on email"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Email(message = "Email must be valid")
                                                           String email){

       CustomerDto customerDto= iAccountService.fetchAccount(email);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }


    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on email"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Email(message = "Email must be valid")
                                                            String email) {
        boolean isDeleted = iAccountService.deleteAccount(email);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

}
