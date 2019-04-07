package com.hellodoctor.booking.entities;

/**
 * @author Khoa
 * @created 3/30/2019
 */
public enum BookStatus {
    WAITING("WAITING"),
    PATIENT_CANCEL("PATIENT_CANCEL"),
    DOCTOR_CANCEL("DOCTOR_CANCEL"),
    EXPIRED("EXPIRED");

    private String status;
    BookStatus(String status) {
        this.status = status;
    }

    public static BookStatus getBookStatusByString(String value) {
        for (BookStatus s : BookStatus.values()) {
            if (s.status == value) {
                return s;
            }
        }
        return EXPIRED;
    }
}
