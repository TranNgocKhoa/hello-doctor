package com.hellodoctor.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Khoa
 * @created 3/29/2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="profile_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Profile implements Serializable {
    @Id
    private Long id;
    private Gender gender;
    private String name;
    private String phoneNumber;
    private Date dateOfBirth;
    private String description;
    private String avatarImg;
    private String personIdNumber;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @MapsId
    private User user;



}
