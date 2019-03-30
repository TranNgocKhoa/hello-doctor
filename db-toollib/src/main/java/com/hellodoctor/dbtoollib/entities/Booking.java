package com.hellodoctor.dbtoollib.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Khoa
 * @created 3/30/2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Profile doctorId;
    private Profile patientId;
    private Date dateTime;
    private BookStatus status;
    private String note;
    private boolean commentable;
    private String statusReason;
}
