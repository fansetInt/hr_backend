//package com.fanset.dms.payroll.controller;
//
//
//import com.fanset.dms.payroll.dto.PayrollRequestDto;
//import com.fanset.dms.payroll.model.Payroll;
//import com.fanset.dms.payroll.service.PayrollService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/payroll")
//public class PayrollController {
//
//
//    private PayrollService payrollService;
//
//    @PostMapping
//    public ResponseEntity<String> createPayroll(@RequestBody PayrollRequestDto payroll) {
//        return ResponseEntity.ok(payrollService.createPayroll(payroll));
//    }
////
////    @GetMapping
////    public ResponseEntity<List<Payroll>> getAllPayrolls() {
////        return ResponseEntity.ok(payrollService.getAllPayrolls());
////    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Payroll> getPayrollById(@PathVariable Long id) {
//        return ResponseEntity.ok(payrollService.getPayrollById(id));
//    }
//}
