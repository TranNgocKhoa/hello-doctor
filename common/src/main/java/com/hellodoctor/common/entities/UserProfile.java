package com.hellodoctor.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
    private Float weight;
    private Float height;
    @OneToMany(
            mappedBy = "patientId",
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<Booking> patientBooks;

}
