package com.fanset.dms.attendance;

import com.fanset.dms.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository  extends JpaRepository<AttendanceRecord,Long> {

    @Query("SELECT a FROM AttendanceRecord a WHERE a.employee = :employee AND a.attendanceDate = :attendanceDate")
    AttendanceRecord findByEmployeeAndAttendanceDate(@Param("employee") User employee, @Param("attendanceDate") LocalDate attendanceDate);

    @Query("Select a from AttendanceRecord a where a.attendanceDate =:date ")
    List<AttendanceRecord> findAllByDate(@Param("date") LocalDate date);

    @Query("SELECT a FROM AttendanceRecord a WHERE a.employee.id = :userId")
    List<AttendanceRecord> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT a FROM AttendanceRecord a WHERE a.isLateToday = true AND a.attendanceDate =:now")
    List<AttendanceRecord> findAllLateByDate(@Param("now")LocalDate now);
}
