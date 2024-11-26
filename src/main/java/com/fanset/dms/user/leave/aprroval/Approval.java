package com.fanset.dms.user.leave.aprroval;

import com.fanset.dms.user.leave.ApprovalStatus;
import com.fanset.dms.user.leave.LeaveRequest;
import com.fanset.dms.user.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private LeaveRequest leaveRequest;

    @ManyToOne
    private User approver;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status; // PENDING, APPROVED, REJECTED
    private LocalDateTime approvalDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LeaveRequest getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Approval approval = (Approval) o;
        return Objects.equals(id, approval.id) && Objects.equals(leaveRequest, approval.leaveRequest) && Objects.equals(approver, approval.approver) && status == approval.status && Objects.equals(approvalDate, approval.approvalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leaveRequest, approver, status, approvalDate);
    }

    @Override
    public String toString() {
        return "Approval{" +
                "id=" + id +
                ", leaveRequest=" + leaveRequest +
                ", approver=" + approver +
                ", status=" + status +
                ", approvalDate=" + approvalDate +
                '}';
    }
}