package com.hellodoctor.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Khoa
 * @created 4/1/2019
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "department_relate_symptom",
            joinColumns = @JoinColumn(
                    name = "symptom_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "department_id", referencedColumnName = "id"))
    private List<Department> departments = new ArrayList<>();
}
