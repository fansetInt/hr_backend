package com.fanset.dms.attendance;


import com.fanset.dms.user.model.User;
import com.fanset.dms.utils.base.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
public class AttendanceRecord extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User employee;

    private LocalDate attendanceDate;
    private LocalTime signInTime;
    private LocalTime signOutTime;

    public User getEmployee() {
        return employee;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public LocalTime getSignInTime() {
        return signInTime;
    }

    public LocalTime getSignOutTime() {
        return signOutTime;
    }

    public boolean isLateToday() {
        return isLateToday;
    }

    public boolean isEmployeeMarkedInToday() {
        return employeeMarkedInToday;
    }

    public boolean isEmployeeMarkedOutToday() {
        return employeeMarkedOutToday;
    }

    private boolean isLateToday = false;
    private boolean employeeMarkedInToday = false;
    private boolean employeeMarkedOutToday = false;

    public AttendanceRecord(User employee, LocalDate attendanceDate, LocalTime signInTime, LocalTime signOutTime) {
        this.employee = employee;
        this.attendanceDate = attendanceDate;
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
        updateFlags();
    }

    public AttendanceRecord(User employee, LocalDate attendanceDate, LocalTime signInTime) {
        this.employee = employee;
        this.attendanceDate = attendanceDate;
        this.signInTime = signInTime;
        updateFlags();
    }

    public AttendanceRecord(LocalTime signOutTime, LocalDate attendanceDate, User employee) {
        this.signOutTime = signOutTime;
        this.attendanceDate = attendanceDate;
        this.employee = employee;
        updateFlags();
    }

    public AttendanceRecord() {
    }

    public boolean isLate() {
        return signInTime != null && signInTime.isAfter(LocalTime.of(8, 30));
    }

    private void updateFlags() {
        this.isLateToday = isLate();
        this.employeeMarkedInToday = signInTime != null;
        this.employeeMarkedOutToday = signOutTime != null;
    }

    public void setSignInTime(LocalTime signInTime) {
        this.signInTime = signInTime;
        updateFlags();
    }

    public void setSignOutTime(LocalTime signOutTime) {
        this.signOutTime = signOutTime;
        updateFlags();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), attendanceDate, employee);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AttendanceRecord that = (AttendanceRecord) obj;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(attendanceDate, that.attendanceDate) &&
                Objects.equals(employee, that.employee);
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "id=" + getId() +
                ", version=" + getVersion() +
                ", employee=" + employee +
                ", attendanceDate=" + attendanceDate +
                ", signInTime=" + signInTime +
                ", signOutTime=" + signOutTime +
                '}';
    }

}
