package com.hellodoctor.booking.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Khoa
 * @created 3/30/2019
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("FACE")
public class UserFacebook extends SnsUser {

    private String firstName;
    private String lastName;
}
