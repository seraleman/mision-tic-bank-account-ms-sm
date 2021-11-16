package com.seraleman.account_ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class InsufficientBalanceAdvice {

    @ResponseBody
    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String EntityInsufficientBalanceAdvice(InsufficientBalanceException ex) { // Se titula diferente para evitar el
                                                                              // error
        return ex.getMessage();
    }
}
