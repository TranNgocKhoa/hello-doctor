package com.hellodoctor.booking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Khoa
 * @created 4/2/2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DoctorResultDTO {
    private Long doctorId;
    private float score;
    private String phoneNumber;
    private String description;
    private String doctorName;
    private String avatarImg;
    private String addressTitle;
    private String address;
    private String workOffice;
    private float rateSummary;
    private BigDecimal basePrice;
    private String department;
    private float distance;

    public DoctorResultDTO(Long doctorId,
                           float score,
                           String phoneNumber,
                           String description,
                           String doctorName,
                           String department,
                           String avatarImg,
                           String addressTitle,
                           String address,
                           float rateSummary,
                           BigDecimal basePrice) {
        this.doctorId = doctorId;
        this.score = score;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.doctorName = doctorName;
        this.department = department;
        this.avatarImg = avatarImg;
        this.addressTitle = addressTitle;
        this.address = address;
        this.rateSummary = rateSummary;
        this.basePrice = basePrice;

    }
}
