package com.fanset.dms.user.dto;


import com.fanset.dms.user.enums.Role;
import com.fanset.dms.user.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
//    @JsonProperty("user")
//   private User user;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    private String email;
    private Role role;

    private String username;
    private String phoneNumber;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
