package com.hellodoctor.common.response;

import com.hellodoctor.common.constants.ReturnCode;
import org.springframework.boot.json.JsonParser;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class GenericResponse {
    private String message;
    private ReturnCode code;

    public GenericResponse(final String message) {
        super();
        this.message = message;
    }

    public GenericResponse(ReturnCode code, String message) {
        this.message = message;
        this.code = code;
    }

    public GenericResponse(List<ObjectError> allErrors, ReturnCode code) {
        this.code = code;
        String temp = allErrors.stream().map(e -> {
            if (e instanceof FieldError) {
                return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            } else {
                return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            }
        }).collect(Collectors.joining(","));
        this.message = "[" + temp + "]";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public ReturnCode getCode() {
        return code;
    }

    public void setCode(ReturnCode code) {
        this.code = code;
    }


}
