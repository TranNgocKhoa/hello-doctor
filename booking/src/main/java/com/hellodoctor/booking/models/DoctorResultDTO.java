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
    private Long id;
    private String phoneNumber;
    private String description;
    private String avatarImg;
    private String addressTitle;
    private String address;
    private String workOffice;
    private float rateSummary;
    private BigDecimal basePrice;
    private String department;
    private float distance;
}
