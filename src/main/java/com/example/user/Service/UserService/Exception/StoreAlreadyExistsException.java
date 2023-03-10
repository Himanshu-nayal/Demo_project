package com.example.user.Service.UserService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class StoreAlreadyExistsException extends RuntimeException{
    public StoreAlreadyExistsException() {
        super(message);
    }
}
