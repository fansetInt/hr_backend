package com.fanset.dms.user.repository;

import com.fanset.dms.user.dto.AuthenticationResponse;
import com.fanset.dms.user.enums.Role;
import com.fanset.dms.user.model.User;
import com.fanset.dms.user.projections.UserProjectionInfo;
import com.fanset.dms.user.token.model.Token;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    Optional<User> findByEmail(String email);
    User findUserByPhoneNumber(String phoneNumber);
    @Query("SELECT u FROM User u JOIN u.tokens t " +
            "WHERE t.token = :token " +
            "AND NOT t.revoked " +
            "AND NOT t.expired")
    Optional<User> findUserByToken(@Param("token") String token);
    List<User> findUsersByRole(Role role);
    @Query("SELECT u FROM User u")
    List<UserProjectionInfo> findAllProjection();
}