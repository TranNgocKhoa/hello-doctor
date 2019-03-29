package com.hellodoctor.dbtoollib.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author Khoa
 * @created 3/29/2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfile extends Profile{
    private String title;
    private String department;
    private String addressTitle;
    private String address;
    private String workOffice;
    private float rateSummary;
    private BigDecimal basePrice;
    private BigDecimal balance;
}
