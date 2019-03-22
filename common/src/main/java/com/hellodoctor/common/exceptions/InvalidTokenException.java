package com.hellodoctor.common.exceptions;

public class InvalidTokenException extends ApiRuntimeException {
    public InvalidTokenException(String msg) {
        super(msg);
    }
}
