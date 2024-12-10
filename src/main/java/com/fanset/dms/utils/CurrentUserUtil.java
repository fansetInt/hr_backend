package com.fanset.dms.utils;


import com.fanset.dms.user.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//@Component
public class CurrentUserUtil {

    /**
     * Retrieves the user ID of the currently logged-in user.
     *
     * @return the user ID of the logged-in user, or null if no user is authenticated.
     */

    public static Long getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return user.getUserId();
        }
        return 0L;
    }
}
