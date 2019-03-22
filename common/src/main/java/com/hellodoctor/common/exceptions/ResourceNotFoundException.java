package com.hellodoctor.common.exceptions;

import com.hellodoctor.common.constants.ReturnCode;

public class ResourceNotFoundException extends ApiRuntimeException {
    private static final long serialVersionUID = 5861310537366287163L;

    public ResourceNotFoundException() {
        super(ReturnCode.NOTFOUND.toString());
    }

}
