package com.hellodoctor.dbtoollib.entities;

import lombok.*;

import javax.persistence.*;
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
@DiscriminatorValue("DOC")
@EqualsAndHashCode(exclude = {"department"})
public class DoctorProfile extends Profile{
    private String title;
    private String addressTitle;
    private String address;
    private String workOffice;
    private float rateSummary;
    private BigDecimal basePrice;
    private BigDecimal balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
