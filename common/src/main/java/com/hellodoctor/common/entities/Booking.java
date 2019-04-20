package com.hellodoctor.common.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Profile doctorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Profile patientId;
    private Date dateTime;
    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private BookStatus status;
    private String note;
    private boolean commentable;
    private String statusReason;
}
