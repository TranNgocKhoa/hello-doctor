package com.hellodoctor.common.exceptions;

import com.hellodoctor.common.constants.ReturnCode;

public class ApiRuntimeException extends RuntimeException {
    private ReturnCode code;

    public ApiRuntimeException() {

    }

    public ApiRuntimeException(String message) {
        super(message);
    }

    public ApiRuntimeException(ReturnCode code, String message) {
        super(message);
        this.code = code;
    }

    public ReturnCode getCode() {
        return code;
    }

    public void setCode(ReturnCode code) {
        this.code = code;
    }
}
