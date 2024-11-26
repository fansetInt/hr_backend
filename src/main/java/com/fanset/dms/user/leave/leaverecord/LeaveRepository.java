package com.fanset.dms.user.leave.leaverecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<LeaveRecord,Long> {
}
