package com.fanset.dms.user.leave;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<LeaveRequest,Long> {
}
