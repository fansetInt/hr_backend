//package com.fanset.dms.loan.model;
//
//import com.fanset.dms.user.model.User;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class LoanRecord {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long loanId;
//    private String loanType;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//    private Double loanAmount;
//    private Double interestRate;
//    private String status;
//    private Integer loanDuration;
//
//
//    @Column(name = "start_date", columnDefinition = "TIMESTAMP")
//    private LocalDateTime startDate;
//
//    @Column(name = "end_date", columnDefinition = "TIMESTAMP")
//    private LocalDateTime endDate;
//
//}
