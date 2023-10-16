package com.tobi.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends Throwable {
    public LoanAlreadyExistsException(String message){
        super(message);
    }

}
