package com.hellodoctor.account.models;

import com.hellodoctor.account.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Khoa
 * @created 4/13/2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfileDTO {
    private Long doctorId;
    private Gender gender;
    private String name;
    private String phoneNumber;
    private Date dateOfBirth;
    private String description;
    private String avatarImg;
    private String title;
    private String addressTitle;
    private String address;
    private String workOffice;
    private Float rateSummary;
    private BigDecimal basePrice;
    private String department;
    private List<CommentDTO> comments = new ArrayList<>();

    public DoctorProfileDTO(Long doctorId, Gender gender, String name, String phoneNumber, Date dateOfBirth, String description, String avatarImg, String title, String addressTitle, String address, String workOffice, Float rateSummary, BigDecimal basePrice, String department) {
        this.doctorId = doctorId;
        this.gender = gender;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.avatarImg = avatarImg;
        this.title = title;
        this.addressTitle = addressTitle;
        this.address = address;
        this.workOffice = workOffice;
        this.rateSummary = rateSummary;
        this.basePrice = basePrice;
        this.department = department;
    }
}
