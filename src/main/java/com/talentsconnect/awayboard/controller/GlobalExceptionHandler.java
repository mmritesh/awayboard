package com.talentsconnect.awayboard.controller;

import com.talentsconnect.awayboard.dto.ServiceResponse;
import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ritesh on 27/3/18.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ServiceResponse<Employee>> handleCustomException(CustomException e, HttpServletRequest request) {
        LOG.error(request.getMethod() + " to URL: " + request.getRequestURL() + " has failed.", e);
        return ResponseEntity.badRequest().body(new ServiceResponse<>(null, e.getErrorDescription(), true));
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<ServiceResponse<Employee>> handleEmptyResultDataAccessException(EmptyResultDataAccessException e, HttpServletRequest request) {
        LOG.error(request.getMethod() + " to URL: " + request.getRequestURL() + " has failed.", e);
        return ResponseEntity.badRequest().body(new ServiceResponse<>(null, e.getMessage(), true));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ServiceResponse<Employee>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        LOG.error(request.getMethod() + " to URL: " + request.getRequestURL() + " has failed.", e);
        return ResponseEntity.badRequest().body(new ServiceResponse<>(null, "Inputs are not in correct format.", true));
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "An unknown error as occurred.")
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ServiceResponse<Employee>>  handleException(Exception e, HttpServletRequest request) {
        LOG.error(request.getMethod() + " to URL: " + request.getRequestURL() + " has failed.", e);
        return ResponseEntity.badRequest().body(new ServiceResponse<>(null, e.getMessage(), true));
    }

}
