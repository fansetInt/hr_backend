//package com.fanset.dms.payroll.model;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Allowances {
//    @Id
//    @SequenceGenerator(name = "allowances_sequence", sequenceName = "allowances_sequence", allocationSize =1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allowances_sequence")
//    private Long allowanceId;
//    private Double reimbursementsFromPreviousPayroll;
//    private Double totalNonTaxableAllowances;
//    private Double medicalAid;
//    private Double funeralPolicy;
//
//    @OneToOne
//    @JoinColumn(name = "payroll_id", nullable = false)
//    private Payroll payroll;
//}
