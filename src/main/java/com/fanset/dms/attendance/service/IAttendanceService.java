package com.fanset.dms.attendance.service;

import com.fanset.dms.attendance.model.AttendanceRecord;
import com.fanset.dms.user.model.User;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IAttendanceService {
    String signIn(Long userId);
    String signOut(Long userId);
    List<AttendanceRecord> getAttendanceByDate(LocalDate date) ;
    List<AttendanceRecord> getUserAttendanceRecords(Long userId) ;
  List<AttendanceRecord> getLateUsers(LocalDate now);
}
