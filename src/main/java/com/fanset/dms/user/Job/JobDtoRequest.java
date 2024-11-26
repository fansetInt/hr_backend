package com.fanset.dms.user.Job;

import jakarta.validation.constraints.NotNull;

public record JobDtoRequest(
        @NotNull String heading
) {
}
