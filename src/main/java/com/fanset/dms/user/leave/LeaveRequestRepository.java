package com.fanset.dms.user.leave;

import com.fanset.dms.user.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {


    @Query("SELECT l FROM LeaveRequest l WHERE l.approval = :approver AND l.employee.department.id = :departmentId")
    Page<LeaveRequest> findByDepartmentIdAndApprover(
            @Param("departmentId") Long departmentId,
            @Param("approver") String approver,
            Pageable pageable
    );

}
