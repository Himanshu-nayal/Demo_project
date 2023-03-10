package com.example.user.Service.UserService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException() {
        super();
    }
}

