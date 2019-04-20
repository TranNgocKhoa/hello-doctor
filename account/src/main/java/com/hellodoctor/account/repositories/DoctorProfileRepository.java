package com.hellodoctor.account.repositories;

import com.hellodoctor.account.models.CommentDTO;
import com.hellodoctor.account.models.DoctorProfileDTO;
import com.hellodoctor.common.entities.DoctorProfile;
import com.hellodoctor.common.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Khoa
 * @created 4/13/2019
 */
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {
    @Query(value = "SELECT " +
            " new com.hellodoctor.account.models.DoctorProfileDTO ( " +
            " p.user.id , " +
            " p.id , " +
            " p.gender, " +
            " p.name, " +
            " p.phoneNumber , " +
            " p.dateOfBirth , " +
            " p.description, " +
            " p.avatarImg , " +
            " p.title, " +
            " p.addressTitle , " +
            " p.address, " +
            " p.workOffice , " +
            " p.rateSummary , " +
            " p.basePrice , " +
            " d.name )  " +
            "FROM " +
            " DoctorProfile p " +
            " LEFT JOIN Booking b ON p.id = b.doctorId " +
            " LEFT JOIN Department d on p.department.id = d.id " +
            " LEFT JOIN Comment c ON c.book.id = b.id " +
            "WHERE " +
            " p.id = :doctorId")
    DoctorProfileDTO getDoctorProfileDTO(@Param("doctorId") Long doctorId);

    @Query(value = "select " +
            "new  com.hellodoctor.account.models.CommentDTO(" +
            "c.id, p.name, p.avatarImg, c.rate, c.content) " +
            "from " +
            "Comment c " +
            "LEFT JOIN Booking b ON c.book.id = b.id " +
            "LEFT JOIN UserProfile p on b.patientId.id = p.id " +
            "WHERE " +
            "b.doctorId.id = :doctorId")
    List<CommentDTO> getCommentByDoctorId(@Param("doctorId") Long doctorId);
}


