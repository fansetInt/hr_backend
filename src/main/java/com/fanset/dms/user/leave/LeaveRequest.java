package com.fanset.dms.user.leave;


import com.fanset.dms.user.leave.aprroval.Approval;
import com.fanset.dms.user.model.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


//    private String leaveType;
//    private String startDate;
//    private String endDate;
//    private String reason;

    @ManyToOne
    private User employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @OneToMany(mappedBy = "leaveRequest", cascade = CascadeType.ALL)
    private List<Approval> approvals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public List<Approval> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveRequest that = (LeaveRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(reason, that.reason) && leaveType == that.leaveType && status == that.status && Objects.equals(approvals, that.approvals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, startDate, endDate, reason, leaveType, status, approvals);
    }


    @Override
    public String toString() {
        return "LeaveRequest{" +
                "id=" + id +
                ", employee=" + employee +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reason='" + reason + '\'' +
                ", leaveType=" + leaveType +
                ", status=" + status +
                ", approvals=" + approvals +
                '}';
    }
}


