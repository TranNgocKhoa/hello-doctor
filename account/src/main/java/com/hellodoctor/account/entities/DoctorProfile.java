package com.hellodoctor.account.entities;

import com.hellodoctor.account.models.CommentDTO;
import com.hellodoctor.account.models.DoctorProfileDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Khoa
 * @created 3/29/2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("DOC")
@EqualsAndHashCode(exclude = {"department"})

@NamedNativeQuery(
        name="getDoctorProfileDTO",
        query="SELECT " +
                " p.id doctorId, " +
                " p.gender gender, " +
                " p.`name` `name`, " +
                " p.phone_number phoneNumber, " +
                " p.date_of_birth dateOfBirth, " +
                " p.description, " +
                " p.avatar_img avatarImg, " +
                " p.title, " +
                " p.address_title addressTitle, " +
                " p.address, " +
                " p.work_office workOffice, " +
                " p.rate_summary rateSummary, " +
                " p.base_price basePrice, " +
                " d.`name` department  " +
                "FROM " +
                " `profile` p " +
                " LEFT JOIN booking b ON p.id = b.doctor_id " +
                " LEFT JOIN department d on p.department_id = d.id " +
                " LEFT JOIN `comment` c ON c.book_id = b.id " +
                "WHERE " +
                " p.id = :doctorId",
        resultSetMapping="doctorProfileDTOMapping"
)
@SqlResultSetMapping(
        name = "doctorProfileDTOMapping",
        classes = @ConstructorResult(
                targetClass = DoctorProfileDTO.class,
                columns = {
                        @ColumnResult(name = "doctorId", type = Long.class),
                        @ColumnResult(name = "gender", type = Gender.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "phoneNumber", type = String.class),
                        @ColumnResult(name = "dateOfBirth", type = Date.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "avatarImg", type = String.class),
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "addressTitle", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "workOffice", type = String.class),
                        @ColumnResult(name = "rateSummary", type = Float.class),
                        @ColumnResult(name = "basePrice", type = BigDecimal.class),
                        @ColumnResult(name = "department", type = String.class),
                })
)

@NamedNativeQuery(
        name="getCommentByDoctorId",
        query="SELECT " +
                " c.book_id id, " +
                " p.`name` patientName, " +
                " p.avatar_img avatarImg, " +
                " c.rate rate, " +
                " c.content content " +
                "FROM " +
                " `comment` c " +
                " LEFT JOIN booking b ON c.book_id = b.id " +
                " LEFT JOIN `profile` p ON b.patient_id = p.id  " +
                "WHERE " +
                " b.doctor_id = 1",
        resultSetMapping="getCommentByDoctorIdMapping"
)

@SqlResultSetMapping(
        name = "getCommentByDoctorIdMapping",
        classes = @ConstructorResult(
                targetClass = CommentDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "patientName", type = String.class),
                        @ColumnResult(name = "avatarImg", type = String.class),
                        @ColumnResult(name = "rate", type = Float.class),
                        @ColumnResult(name = "content", type = String.class)
                })
)
public class DoctorProfile extends Profile{
    private String title;
    private String addressTitle;
    private String address;
    private String workOffice;
    private float rateSummary;
    private BigDecimal basePrice;
    private BigDecimal balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
