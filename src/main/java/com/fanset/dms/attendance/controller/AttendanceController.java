package com.fanset.dms.attendance.controller;


import com.fanset.dms.attendance.model.AttendanceRecord;
import com.fanset.dms.attendance.service.impl.AttendanceService;
import com.fanset.dms.user.model.User;
import com.fanset.dms.utils.CurrentUserUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
@RequestMapping("/api/v1/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    // Sign-in endpoint
    @PostMapping("/signIn")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
    public ResponseEntity<String > signIn(@RequestParam Long userId) {
        String success =attendanceService.signIn(CurrentUserUtil.getCurrentUser());
        return ResponseEntity.ok(success);
    }

    // Sign-out endpoint
    @PostMapping("/signOut")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
    public ResponseEntity<String>  signOut(@RequestParam Long userId) {

//        Authentication authentication = SecurityContemm
        String success = attendanceService.signOut(CurrentUserUtil.getCurrentUser());
        return ResponseEntity.ok(success);
    }

    // Get today's attendance records
    @GetMapping("/today")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
    public List<AttendanceRecord> getTodayAttendance() {
        return attendanceService.getAttendanceByDate(LocalDate.now());
    }

//    // Get all attendance records for a user
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
    public List<AttendanceRecord> getUserAttendance(@PathVariable Long userId) {
        return attendanceService.getUserAttendanceRecords(userId);
    }
//
//    // Get late users for today
    @GetMapping("/late")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
    public List<AttendanceRecord> getLateUsersToday() {
        return attendanceService.getLateUsers(LocalDate.now());
    }
//
//
    @GetMapping("/user/{userId}/calendar")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
    public List<AttendanceRecord> getUserAttendanceForMonth(@PathVariable Long userId,
                                                            @RequestParam int year,
                                                            @RequestParam int month) {
        return attendanceService.getUserAttendanceRecords(userId)
                .stream()
                .filter(record -> record.getAttendanceDate().getYear() == year &&
                        record.getAttendanceDate().getMonthValue() == month)
                .collect(Collectors.toList());
    }
}
