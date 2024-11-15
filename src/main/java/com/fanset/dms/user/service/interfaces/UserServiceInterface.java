package com.fanset.dms.user.service.interfaces;


import com.fanset.dms.user.dto.AuthenticationResponse;
import com.fanset.dms.user.dto.ChangePasswordRequestDto;
import com.fanset.dms.user.dto.UserDto;
import com.fanset.dms.user.enums.Role;
import com.fanset.dms.user.model.User;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface UserServiceInterface {
    boolean ifUserExists(Long userId);
    List<User> findAllUsers();
    List<User> findUsersByRole(Role role);

    Page<User> findAllUsers(int pageNumber, int pageSize);

    AuthenticationResponse createUser(UserDto userDto);

    User changePassword(ChangePasswordRequestDto changePasswordRequestDto, Principal connectedUser);
    User findUserById(Long userId);
    User findUserByEmail(String email);
    User updateUser(Long userId, UserDto userDto);

    String deleteUserById(Long userId);

    AuthenticationResponse findUserProfile(String token);

    Long getUserId(String username);
}
