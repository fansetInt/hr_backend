package com.fanset.dms.payroll.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DeductionDto(
        @NotNull(message = "Loan Deductions are required.")
        @Positive(message = "Loan Deductions must be greater than zero.")
        Double loanDeductions,

        @NotNull(message = "Total Deductions are required.")
        @Positive(message = "Total Deductions must be greater than zero.")
        Double totalDeductions,


        @NotNull(message = "Total Deductions are required.")
        @Positive(message = "Total Deductions must be greater than zero.")
        Double totalTaxableDeductions


) { }
