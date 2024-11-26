package com.fanset.dms.user.leave;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LeaveRequestDto(
        @NotNull  Long userId,
        @NotNull LeaveType leaveType,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull int dayTaken,
        String reason
        ) {
}
