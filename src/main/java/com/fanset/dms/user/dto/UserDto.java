package com.fanset.dms.user.dto;



import com.fanset.dms.user.enums.Role;

import java.time.LocalDate;

public record UserDto(
        String firstName,
        String lastName,
        String email,
        String password,
        LocalDate dateOfBirth,
        String phoneNumber,
        Role role


) {

}
