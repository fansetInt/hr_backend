package com.fanset.dms.user.leave;

import jakarta.validation.constraints.NotNull;

public record RejectRequestDto (
    @NotNull String rejectReason
){
}
