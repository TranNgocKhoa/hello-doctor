package com.hellodoctor.account.controllers;

import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.exceptions.ResourceNotFoundException;
import com.hellodoctor.common.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    public ExceptionControllerHandler() {
        super();
    }

    @ExceptionHandler({ApiRuntimeException.class})
    public ResponseEntity<Object> apiRuntimeHandler(WebRequest request, ApiRuntimeException e) {
        this.printLog(request, e);
        GenericResponse genericResponse = new GenericResponse(e.getMessage());
        return handleExceptionInternal(e, genericResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> resourceNotFoundHandler(WebRequest request, Exception e) {
        this.printLog(request, e);
        GenericResponse genericResponse = new GenericResponse(e.getMessage());
        return handleExceptionInternal(e, genericResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(WebRequest request, StorageFileNotFoundException e) {
//        this.printLog(request, e);
//        return ResponseEntity.notFound().build();
//    }


    private void printLog(WebRequest request, Exception e) {
        if (request instanceof ServletWebRequest) {
            ServletWebRequest servletWebRequest = (ServletWebRequest) request;
            log.error("uri -> {} error -> {}", servletWebRequest.getRequest().getRequestURI(), e.getMessage());
        }

    }
}