//package com.fanset.dms.payroll.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.context.annotation.Primary;
//
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data// Deprecated due to Spring Boot 3.0.0+ version incompatibility. Use the new constructor instead.
//@Entity
//public class Deductions {
//    @Id
//    @SequenceGenerator(name = "deductions_sequence", sequenceName = "deductions_sequence", allocationSize =1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deductions_sequence")
//    private Long deductionId;
//    private Double loanDeductions;
//    private Double unionDues;
//    private Double totalTaxableDeductions;
//    private Double totalDeductions;
//
//    @OneToOne
//    @JoinColumn(name = "payroll_id", nullable = false)
//    private Payroll payroll;
//}