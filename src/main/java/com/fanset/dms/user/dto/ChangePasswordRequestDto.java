package com.fanset.dms.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequestDto {
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
