package com.hellodoctor.testservice.config;

import com.hellodoctor.common.constants.ReturnCode;
import com.hellodoctor.common.exceptions.ResourceNotFoundException;
import com.hellodoctor.common.response.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    public GlobalControllerAdvice() {
        super();
    }


    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> resourceNotFoundHandler(WebRequest request, Exception e){
        this.printLog(request, e);
        GenericResponse genericResponse = new GenericResponse(ReturnCode.NOTFOUND, e.getLocalizedMessage());
        return handleExceptionInternal(e, genericResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private void printLog(WebRequest request, Exception e){
        if(request instanceof ServletWebRequest){
            ServletWebRequest servletWebRequest = (ServletWebRequest)request;
            LOGGER.error("uri -> {} error -> {}", servletWebRequest.getRequest().getRequestURI(), e.getMessage());
        }

    }
}
