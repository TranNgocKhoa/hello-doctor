package com.hellodoctor.auth2.entities;

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
@DiscriminatorValue("GOOG")
public class UserGoogle extends SnsUser {
    private String photoUrl;
}
