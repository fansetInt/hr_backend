package com.fanset.dms.attendance;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // Sign-in endpoint
    @PostMapping("/signIn")
    public ResponseEntity<String > signIn(@RequestParam Long userId) {
        String success =attendanceService.signIn(userId);
        return ResponseEntity.ok(success);
    }

//    // Sign-out endpoint
//    @PostMapping("/signOut")
//    public AttendanceRecord signOut(@RequestParam Long userId) {
//        return attendanceService.signOut(userId);
//    }
//
//    // Get today's attendance records
//    @GetMapping("/today")
//    public List<AttendanceRecord> getTodayAttendance() {
//        return attendanceService.getAttendanceByDate(LocalDate.now());
//    }
//
//    // Get all attendance records for a user
//    @GetMapping("/user/{userId}")
//    public List<AttendanceRecord> getUserAttendance(@PathVariable Long userId) {
//        return attendanceService.getUserAttendanceRecords(userId);
//    }
//
//    // Get late users for today
//    @GetMapping("/late")
//    public List<AttendanceRecord> getLateUsersToday() {
//        return attendanceService.getLateUsers(LocalDate.now());
//    }
//
//
//    @GetMapping("/user/{userId}/calendar")
//    public List<AttendanceRecord> getUserAttendanceForMonth(@PathVariable Long userId,
//                                                            @RequestParam int year,
//                                                            @RequestParam int month) {
//        return attendanceService.getUserAttendanceRecords(userId)
//                .stream()
//                .filter(record -> record.getAttendanceDate().getYear() == year &&
//                        record.getAttendanceDate().getMonthValue() == month)
//                .collect(Collectors.toList());
//    }
}
