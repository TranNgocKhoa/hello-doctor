package com.hellodoctor.dbtoollib.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Khoa
 * @created 3/29/2019
 */
@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Gender gender;
    private String phoneNumber;
    private Date dateOfBirth;
    private String description;
    private String avatarImg;
    private String personIdNumber;
}
