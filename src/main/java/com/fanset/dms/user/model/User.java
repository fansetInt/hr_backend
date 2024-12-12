package com.fanset.dms.user.model;


//import com.fanset.dms.loan.model.LoanRecord;
//import com.fanset.dms.payroll.model.Payroll;
import com.fanset.dms.user.Job.JobRecord;
import com.fanset.dms.user.department.DepartmentRecord;
import com.fanset.dms.user.enums.Role;
import com.fanset.dms.user.token.model.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(
        name = "tbl_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = "email_address"
                ),
                @UniqueConstraint(
                        name = "phone_number_unique",
                        columnNames = "phone_number"
                )
        }
)
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize =1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long userId;
    @Version
    private  int version;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "email_address", nullable = false)
    private String email;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "password", nullable = false)
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;


    //security information
    private String nationalId;
    private String nationality;
    private String passportNumber;

    //contacts information
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;

    private String nextOfKeenAddress;
    private String nextOfKeenCity;
    private String nextOfKeenState;
    private String nextOfKeenCountry;
    private String nextOfKeenPhoneNumber;
    private String nextOfKeenEmail;
    private String nextOfKeenRelationship;
    private String nextOfKeenGender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentRecord department;


//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "user_id") // Use a different name for the foreign key column.
//    private List<Payroll> payroll;

    @OneToMany(fetch = FetchType.EAGER)
    private List<JobRecord> jobRecordSet = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Token> tokens;


    // Optional, for bidirectional mapping
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<LoanRecord> loanRecords;

    //

    private int remainingDays = 0;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
