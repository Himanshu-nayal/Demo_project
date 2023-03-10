package com.example.user.Service.UserService.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> validationErrors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationErrors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        ExceptionResponse exceptionResponseFormat =
                new ExceptionResponse(new Date(),
                        "Validation error, please check the details for more info.",
                        validationErrors.toString());

        return new ResponseEntity(exceptionResponseFormat, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(
            StoreNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
     /*
        @ExceptionHandler(ProductNotFoundException.class)
        public final ResponseEntity<Object> handleProductNotFoundException(
                CategoryNotFoundException ex, WebRequest request) {

            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    new Date(), ex.getMessage(), request.getDescription(false));

            return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
        }
        //For handling ProductVariation NotFoundException
        @ExceptionHandler(ProductVariationNotFoundException.class)
        public final ResponseEntity<Object> handleProductVariationNotFoundException(
                CategoryNotFoundException ex, WebRequest request) {

            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    new Date(), ex.getMessage(), request.getDescription(false));

            return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
        }
        //For handling CategoryNotFoundException
        @ExceptionHandler(CategoryNotFoundException.class)
        public final ResponseEntity<Object> handleCategoryNotFoundException(
                CategoryNotFoundException ex, WebRequest request) {

            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    new Date(), ex.getMessage(), request.getDescription(false));

            return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
        }

    }*/
