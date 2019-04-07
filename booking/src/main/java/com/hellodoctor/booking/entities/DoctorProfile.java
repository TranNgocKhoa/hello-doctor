package com.hellodoctor.booking.entities;

import com.hellodoctor.booking.models.DoctorResultDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
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
        name="getListDoctorBySymptomAndTime",
        query="select p.id id, " +
                "(6 - (COUNT(DISTINCT(b.id)) * 5 / 60) + p.rate_summary) score, " +
                "      p.phone_number phoneNumber, " +
                "      p.description description, " +
                "      p.`name` doctorName, " +
                "      d.`name` department, " +
                "      p.avatar_img avatarImg, " +
                "      p.address_title addressTitle, " +
                "      p.address address," +
                "      p.rate_summary rateSummary," +
                " p.base_price basePrice " +
                "from department d" +
                "      LEFT JOIN department_relate_symptom drs ON d.id = drs.department_id" +
                "      LEFT JOIN symptom s ON drs.symptom_id = s.id" +
                "      LEFT JOIN `profile` p ON p.department_id = d.id" +
                "      LEFT JOIN booking b on p.id = b.doctor_id " +
                "WHERE s.`name` LIKE CONCAT('%',lower(:symptom),'%') " +
                "      and b.date_time < NOW() + INTERVAL 7 DAY and HOUR(b.date_time) >= HOUR(:startPart)" +
                "      and HOUR(b.date_time) <= HOUR(:endPart)" +
                "      ORDER BY score",
        resultSetMapping="doctorResultMapping"
)
@SqlResultSetMapping(
        name = "doctorResultMapping",
        classes = @ConstructorResult(
                targetClass = DoctorResultDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "score", type = float.class),
                        @ColumnResult(name = "phoneNumber", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "doctorName", type = String.class),
                        @ColumnResult(name = "department", type = String.class),
                        @ColumnResult(name = "avatarImg", type = String.class),
                        @ColumnResult(name = "addressTitle", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "rateSummary", type = float.class),
                        @ColumnResult(name = "basePrice", type = BigDecimal.class),
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
    @OneToMany(
            mappedBy = "doctorId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Booking> doctorBooks;

}
