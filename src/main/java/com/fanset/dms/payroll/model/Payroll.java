//package com.fanset.dms.payroll.model;
//
//import com.fanset.dms.user.model.User;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.time.LocalDateTime;
//
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "payroll")
//public class Payroll {
//    @Id
//    @SequenceGenerator(name = "payroll_sequence", sequenceName = "payroll_sequence", allocationSize =1)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long payrollId;
//
//    @ManyToOne
//    @JoinColumn(name = "employee_no", nullable = false)
//    private User employee;
//
//    @CreationTimestamp
//    private LocalDateTime dateTime;
//
//    private Double basicSalary;
//    private String overtime;
//    private Double grossSalary;
//    private Double paye;
//    private Double nssa;
//    private Double aidsLevy;
//    private Double totalDeductions;
//    private Double netSalaryBeforeAllowances;
//    private Double netPayableSalary;
//}
