package com.waa.backend.exceptions.handler;

import com.aeontanvir.propertymanagementsystem.exceptions.ApiException;
import com.aeontanvir.propertymanagementsystem.exceptions.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiRequestExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException apiRequestException){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = ApiException.builder()
                .message(apiRequestException.getMessage())
                .throwable(apiRequestException)
                .httpStatus(badRequest)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity<>(apiException, badRequest);
    }
}
