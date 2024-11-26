package com.fanset.dms.user.leave.leaverecord;


import com.fanset.dms.user.model.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class LeaveRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    private Double leaveDaysAcrued = 0.0;
    private Double leaveDaysTaken   = 0.0;
    private Double leaveDaysRemaining = 0.0;
    private Double leaveDaysAvailableInAMonth = 1.83;

    private Double maximumAllowedDaysInaMonth = 2.0;


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

    public Double getLeaveDaysAcrued() {
        return leaveDaysAcrued;
    }

    public void setLeaveDaysAcrued(Double leaveDaysAcrued) {
        this.leaveDaysAcrued = leaveDaysAcrued;
    }

    public Double getLeaveDaysTaken() {
        return leaveDaysTaken;
    }

    public void setLeaveDaysTaken(Double leaveDaysTaken) {
        this.leaveDaysTaken = leaveDaysTaken;
    }

    public Double getLeaveDaysRemaining() {
        return leaveDaysRemaining;
    }

    public void setLeaveDaysRemaining(Double leaveDaysRemaining) {
        this.leaveDaysRemaining = leaveDaysRemaining;
    }

    public Double getLeaveDaysAvailableInAMonth() {
        return leaveDaysAvailableInAMonth;
    }

    public void setLeaveDaysAvailableInAMonth(Double leaveDaysAvailableInAMonth) {
        this.leaveDaysAvailableInAMonth = leaveDaysAvailableInAMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveRecord that = (LeaveRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) && Objects.equals(leaveDaysAcrued, that.leaveDaysAcrued) && Objects.equals(leaveDaysTaken, that.leaveDaysTaken) && Objects.equals(leaveDaysRemaining, that.leaveDaysRemaining) && Objects.equals(leaveDaysAvailableInAMonth, that.leaveDaysAvailableInAMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, leaveDaysAcrued, leaveDaysTaken, leaveDaysRemaining, leaveDaysAvailableInAMonth);
    }


    @Override
    public String toString() {
        return "LeaveRecord{" +
                "id=" + id +
                ", employee=" + employee +
                ", leaveDaysAcrued=" + leaveDaysAcrued +
                ", leaveDaysTaken=" + leaveDaysTaken +
                ", leaveDaysRemaining=" + leaveDaysRemaining +
                ", leaveDaysAvailableInAMonth=" + leaveDaysAvailableInAMonth +
                '}';
    }
}
