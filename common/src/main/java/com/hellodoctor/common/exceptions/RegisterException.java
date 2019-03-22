package com.hellodoctor.common.exceptions;

import com.hellodoctor.common.constants.ReturnCode;

public class RegisterException extends ApiRuntimeException {
    public RegisterException(ReturnCode code) {
        super(code.name());
        setCode(code);
    }
}
