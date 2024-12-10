//package com.fanset.dms.payroll.model;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Commissions {
//    @Id
//    @SequenceGenerator(name = "commissions_sequence", sequenceName = "commissions_sequence", allocationSize =1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commissions_sequence")
//    private Long commisionId;
//    private Double installationCommissions;
//    private Double salesCommissions;
//    private Double totalCommissionsPayable;
//    private Payroll payroll;
//
//}
