package com.fanset.dms.attendance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository  extends JpaRepository<AttendanceRecord,Long> {
}
