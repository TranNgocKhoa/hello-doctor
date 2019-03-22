package com.hellodoctor.common.constants;

public enum ReturnCode {
    SUCCESS(200),
    BAD_REQUEST(400),
    UNAUTHORIZE(401),
    FORBIDDEN(403),
    NOTFOUND(404),
    INTERNAL_SERVER_ERROR(500),

    INVALID_TOKEN(-10001),
    INVALID_SNS_TOKEN(-10002),

    TO_MANY_FAILED_LOGIN(-10010),
    USERNAME_PW_NOT_FOUND(-10011),
    USER_NOT_VALIDATE(-10012),
    INCORRECT_PW(-10013),
    USER_SNS_NOT_AVAILABLE(-10014),
    USER_SNS_LOGIN_FAIL(-10015),
    USER_SNS_TW_TOKEN_INVALID(-10016),
    USER_SNS_TOKEN_MISSING_PARAMS(-10017),

    EMPTY_USERNAME(-10020),
    EMPTY_PW(-10021),
    EMPTY_EMAIL(-10022),
    EXISTED_USERNAME(-10023),
    EMAIL_NOT_FOUND(-10024),


    ARGS_NOT_VALID(-10200);

    private int code;

    ReturnCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
