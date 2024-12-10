//package com.fanset.dms.loan.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Entity
//public class LoanPayment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long paymentId;
//    private LocalDateTime paymentDate;
//    private Double paymentAmount;
//    private LoanRecord loan;
//}