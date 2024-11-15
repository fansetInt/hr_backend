package com.fanset.dms.attendance;


import com.fanset.dms.user.model.User;
import com.fanset.dms.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final UserRepository userRepository;

    public AttendanceService( AttendanceRepository attendanceRepository,UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    public String signIn(Long userId) {
        LocalDate today = LocalDate.now();
        LocalTime signInTime = LocalTime.now();
        User employee = userRepository.getReferenceById(userId);
        AttendanceRecord record = new  AttendanceRecord(employee, today, signInTime);
        attendanceRepository.save(record);
//        entityManager.persist(record);

        return "sign  in success";
    }


}
