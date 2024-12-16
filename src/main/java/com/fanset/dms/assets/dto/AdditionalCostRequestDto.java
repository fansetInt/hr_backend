package com.fanset.dms.assets.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AdditionalCostRequestDto(
        @NotNull(message = "Description cannot be null")
        String description,
        @Positive(message = "Cost must be positive")
        Double cost
) {
}
