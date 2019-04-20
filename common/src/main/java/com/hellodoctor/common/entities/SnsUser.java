package com.hellodoctor.common.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Khoa
 * @created 3/30/2019
 */

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="sns_type", discriminatorType = DiscriminatorType.STRING)
public class SnsUser {
    @Id
    private SnsKey userId;

    private String userName;
    private String email;
    private String gender;
    private String birthDay;
    private String rawData;
}
