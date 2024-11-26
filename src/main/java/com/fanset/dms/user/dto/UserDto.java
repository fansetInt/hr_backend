package com.fanset.dms.user.dto;
import com.fanset.dms.user.enums.Role;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserDto(
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull String email,
        @NotNull String password,
        @NotNull LocalDate dateOfBirth,
        @NotNull String phoneNumber,
        @NotNull Role role,
        @NotNull String nationalId,
        @NotNull String nationality,
        String passportNumber,
        @NotNull String department,
        @NotNull String jobTitle,
        @NotNull AddressDto address
) {
}

