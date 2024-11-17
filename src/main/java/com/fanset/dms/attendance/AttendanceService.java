package com.fanset.dms.attendance;


import com.fanset.dms.user.model.User;
import com.fanset.dms.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


//@Transactional(READ_ONLY)
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, EntityManager entityManager, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }


    @Transactional
    public String signIn(Long userId) {
        LocalDate today = LocalDate.now();
        LocalTime signInTime = LocalTime.now();
        User employee = userRepository.getReferenceById(userId);

        AttendanceRecord record = attendanceRepository.findByEmployeeAndAttendanceDate(employee, today);
        if (record == null) {
            // Create a new record if none exists for today
            record = new AttendanceRecord(employee, today, signInTime);
        } else {
            // Update the existing record's sign-in time
            record.setSignInTime(signInTime);
        }
        saveOrUpdateAttendanceRecord(record);
        return "Sign-in successful for " + today + " at " + signInTime;
    }

    @Transactional
    public String signOut(Long userId) {
        LocalDate today = LocalDate.now();
        LocalTime signOutTime = LocalTime.now();
        User employee = userRepository.getReferenceById(userId);

        AttendanceRecord record = attendanceRepository.findByEmployeeAndAttendanceDate(employee, today);
        if (record == null) {
            throw new IllegalStateException("No sign-in record found for user " + userId + " on " + today);
        }
        // Update the existing record's sign-out time
        record.setSignOutTime(signOutTime);
        saveOrUpdateAttendanceRecord(record);
        return "Sign-out successful for " + today + " at " + signOutTime;
    }

    @Transactional
    private void saveOrUpdateAttendanceRecord(AttendanceRecord record) {
        if (record.getId() == null) {
            entityManager.persist(record);
        } else {
            entityManager.merge(record);
        }
    }



    public List<AttendanceRecord> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findAllByDate(date);
    }

    public List<AttendanceRecord> getUserAttendanceRecords(Long userId) {
        return attendanceRepository.findAllByUserId(userId);
    }


    public List<AttendanceRecord> getLateUsers(LocalDate now) {
        return attendanceRepository.findAllLateByDate(now);
    }
}
