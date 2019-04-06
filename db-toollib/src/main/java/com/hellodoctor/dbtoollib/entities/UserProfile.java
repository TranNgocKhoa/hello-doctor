package com.hellodoctor.dbtoollib.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Khoa
 * @created 3/28/2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PAT")
public class UserProfile extends Profile {
    private float weight;
    private float height;
    @OneToMany(
            mappedBy = "patientId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Booking> patientBooks;

}
