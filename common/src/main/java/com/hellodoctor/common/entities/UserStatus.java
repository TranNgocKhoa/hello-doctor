package com.hellodoctor.common.entities;

/**
 * @author Khoa
 * @created 3/28/2019
 */
public enum UserStatus {
    PENDING("PENDING"),
    NORMAL("NORMAL"),
    BLOCKED("BLOCKED");

    String status;

    UserStatus(String status) {
        this.status = status;
    }

    public static UserStatus getUserStatusByString(String value) {
        for (UserStatus s : UserStatus.values()) {
            if (s.status.equals(value)) {
                return s;
            }
        }
        return PENDING;
    }
}
