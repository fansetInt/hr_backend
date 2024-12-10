//package com.fanset.dms.loan.service;
//
//import com.fanset.dms.loan.dto.LoanDetailsReqDto;
//import com.fanset.dms.loan.model.LoanRecord;
//import com.fanset.dms.loan.repository.ApprovalLoanRepository;
//import com.fanset.dms.loan.repository.CollateralRepository;
//import com.fanset.dms.loan.repository.LoanPaymentRepository;
//import com.fanset.dms.loan.repository.LoanRepository;
//import com.fanset.dms.user.model.User;
//import com.fanset.dms.user.repository.UserRepository;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//
//@Service
//@Transactional(readOnly = true)
//@Log4j2(topic = "LoanService")
//@RequiredArgsConstructor
//public class LoanService {
//
//    private final LoanRepository loanRepository;
//    private final LoanPaymentRepository loanPaymentRepository;
//    private final ApprovalLoanRepository approvalLoanRepository;
//    private final CollateralRepository collateralRepository;
//    private final UserRepository userRepository;
//
//    @Transactional
//    public Object submitLoan(@Valid LoanDetailsReqDto loanDetailsReqDto) {
//        // Business logic to submit the loan goes here
//
//        Optional<User> user = userRepository.findById(loanDetailsReqDto.userId());
//        if (user.isEmpty()) {
//            throw new RuntimeException("User not found");
//        }
//        LoanRecord loanRecord = new LoanRecord();
//        loanRecord.setLoanAmount(loanDetailsReqDto.loanAmount());
//        loanRecord.setInterestRate(loanDetailsReqDto.interestRate());
//        loanRecord.setLoanDuration(loanDetailsReqDto.loanDuration());
//        loanRecord.setStatus(loanDetailsReqDto.loanStatus());
//        loanRecord.setUser(user.get());
//        loanRecord.setLoanType(loanDetailsReqDto.loanType());
//        loanRecord.setStartDate(LocalDateTime.now());
//        loanRecord.setEndDate(loanRecord.getStartDate().plusMonths(loanDetailsReqDto.loanDuration()));
//        return loanRepository.save(loanRecord);
//    }
//}
