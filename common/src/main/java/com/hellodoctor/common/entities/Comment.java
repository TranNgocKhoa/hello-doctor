package com.hellodoctor.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Khoa
 * @created 3/30/2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn
    @MapsId
    private Booking book;
    private DoctorProfile doctorId;
    private Float rate;
    private String content;

}
