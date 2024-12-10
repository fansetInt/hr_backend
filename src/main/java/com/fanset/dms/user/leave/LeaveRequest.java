package com.fanset.dms.user.leave;


import com.fanset.dms.user.enums.Role;
import com.fanset.dms.user.leave.aprroval.Approval;
import com.fanset.dms.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

@Data
@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private User employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
    private int daystotaldays;


    private String approval;
    private LocalDateTime submitedAt;
    private LocalDateTime updatedAt;
    private String rejectionReason = "";

//    @Version
//    private Long version;

//    @OneToMany(mappedBy = "leaveRequest", cascade = CascadeType.ALL)
//    private HashMap<Approval,Integer> approvals;
    public LeaveRequest(@NotNull User employee,
                        @NotNull LeaveType leaveType,
                        @NotNull LocalDate localDate,
                        @NotNull LocalDate localDate1,
                        int i, String reason) {
        this.employee =employee;
        this.leaveType = leaveType;
        this.startDate = localDate;
        this.endDate = localDate1;
        this.daystotaldays = i;
        this.reason = reason;
        this.status = LeaveStatus.PENDING;
    }

    public LeaveRequest() {
    }


}


