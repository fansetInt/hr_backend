//package com.fanset.dms.payroll.service;
//
//
//import com.fanset.dms.payroll.dto.PayrollRequestDto;
//import com.fanset.dms.payroll.model.Allowances;
//import com.fanset.dms.payroll.model.Commissions;
//import com.fanset.dms.payroll.model.Deductions;
//import com.fanset.dms.payroll.model.Payroll;
//import com.fanset.dms.payroll.repository.AllowanceRepository;
//import com.fanset.dms.payroll.repository.PayrollRepository;
//import com.fanset.dms.user.model.User;
//import com.fanset.dms.user.repository.UserRepository;
//import jakarta.persistence.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//@Service
//public class PayrollService {
//
//    private final UserRepository userRepository;
//    private final EntityManager entityManager;
//    private final AllowanceService allowanceService;
//    private final CommisionService commitService;
//    private final DeductionService deductionService;
//    private final PayrollRepository payrollRepository;
//    private final JdbcTemplate jdbcTemplate;
//
//
//
//    //create a new instance
//    @Transactional
//    public String createPayroll(final PayrollRequestDto payrollRequestDto) {
//
//        Optional<User> user = userRepository.findById(payrollRequestDto.userId());
//        if (!user.isPresent()) {
//            throw new RuntimeException("User not found.");
//        }
//
////        Allowances allowances = allowanceService.createAllowances()
//
//        Allowances allow = new Allowances();
//        Deductions deductios = new Deductions();
//        Commissions commissions = new Commissions();
//        Payroll request = new Payroll();
//        request.setEmployee(user.get());
//        request.setBasicSalary(payrollRequestDto.basicSalary());
////        request.setOvertime(payrollRequestDto.overtime());
//        request.setGrossSalary(0.0);
//        request.setPaye(0.0);
//        request.setNssa(0.0);
//        request.setAidsLevy(0.0);
//        request.setTotalDeductions(0.0);
//        request.setNetSalaryBeforeAllowances(0.0);
//        request.setNetPayableSalary(0.0);
//
//        Payroll response = payrollRepository.save(request);
//
//        allow.setPayroll(response);
//        allow.setReimbursementsFromPreviousPayroll(payrollRequestDto.allowances().reimbursement());
//        allow.setTotalNonTaxableAllowances(payrollRequestDto.allowances().totalNonTaxableAllowances());
//        allow.setMedicalAid(payrollRequestDto.allowances().medicalAid());
//        allow.setFuneralPolicy(payrollRequestDto.allowances().funeralPolicy());
//        entityManager.persist(allow);
//
//
//
//
//        deductios.setPayroll(response);
//        deductios.setLoanDeductions(payrollRequestDto.deductions().loanDeductions());
//        deductios.setTotalTaxableDeductions(payrollRequestDto.deductions().totalTaxableDeductions());
//        deductios.setTotalDeductions(payrollRequestDto.deductions().totalDeductions());
//        entityManager.persist(deductios);
//
//
//
//        commissions.setPayroll(response);
//        commissions.setInstallationCommissions(payrollRequestDto.commissions().installationCommissions());
//        commissions.setSalesCommissions(payrollRequestDto.commissions().salesCommissions());
//        commissions.setTotalCommissionsPayable(payrollRequestDto.commissions().totalPayable());
//        entityManager.persist(commissions);
//        return "creation success full";
//    }
//
//
//    //getPayroll request
//    public Payroll getPayrollById(Long id) {
//        return payrollRepository.findById(id).orElseThrow(() -> new RuntimeException("Payroll not found."));
//
//    }
//
//
//    public PayrollRequestDto findPayrollRequestDtoById(Long payrollId) {
//        String jpql = """
//            SELECT new com.example.dto.PayrollRequestDto(
//                p.employee.id,
//                p.basicSalary,
//                p.overtime,
//                a.reimbursement,
//                a.totalNonTaxableAllowances,
//                a.medicalAid,
//                a.funeralPolicy,
//                d.loanDeductions,
//                d.totalDeductions,
//                c.installationCommissions,
//                c.salesCommissions,
//                c.totalPayable
//            )
//            FROM Payroll p
//            JOIN p.allowances a
//            JOIN p.deductions d
//            JOIN p.commissions c
//            WHERE p.id = :payrollId
//        """;
//
//        TypedQuery<PayrollRequestDto> query = entityManager.createQuery(jpql, PayrollRequestDto.class);
//        query.setParameter("payrollId", payrollId);
//
//        return query.getSingleResult();
//    }
//
//
//
//
//
//
//
//
////    public List<PayrollRequestDto> findAllPayrollRequestDtoById(Long payrollId) {
////        String sql = """
////            SELECT
////                p.employee_id AS employeeId,
////                p.basic_salary AS basicSalary,
////                p.overtime AS overtime,
////                a.reimbursement AS reimbursement,
////                a.total_non_taxable_allowances AS totalNonTaxableAllowances,
////                a.medical_aid AS medicalAid,
////                a.funeral_policy AS funeralPolicy,
////                d.loan_deductions AS loanDeductions,
////                d.total_deductions AS totalDeductions,
////                c.installation_commissions AS installationCommissions,
////                c.sales_commissions AS salesCommissions,
////                c.total_payable AS totalPayable
////            FROM payroll p
////            JOIN allowances a ON p.id = a.payroll_id
////            JOIN deductions d ON p.id = d.payroll_id
////            JOIN commissions c ON p.id = c.payroll_id
////            WHERE p.id = ?
////        """;
////
//////        return jdbcTemplate.query(sql, new Object[]{payrollId}, (rs, rowNum) ->
//////                new PayrollRequestDto(
//////                        rs.getLong("employeeId"),
//////                        rs.getBigDecimal("basicSalary"),
//////                        rs.getBigDecimal("overtime"),
//////                        rs.getBigDecimal("reimbursement"),
//////                        rs.getBigDecimal("totalNonTaxableAllowances"),
//////                        rs.getBigDecimal("medicalAid"),
//////                        rs.getBigDecimal("funeralPolicy"),
//////                        rs.getBigDecimal("loanDeductions"),
//////                        rs.getBigDecimal("totalDeductions"),
//////                        rs.getBigDecimal("installationCommissions"),
//////                        rs.getBigDecimal("salesCommissions"),
//////                        rs.getBigDecimal("totalPayable")
//////                )
//////        );
////    }
//
//}
