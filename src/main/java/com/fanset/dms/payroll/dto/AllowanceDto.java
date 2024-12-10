package com.fanset.dms.payroll.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AllowanceDto(
        @NotNull(message = "Reimbursement is required.")
        @Positive(message = "Reimbursement must be greater than zero.")
        Double reimbursement,

        @NotNull(message = "Total Non-Taxable Allowances are required.")
        @Positive(message = "Total Non-Taxable Allowances must be greater than zero.")
        Double totalNonTaxableAllowances,

        @NotNull(message = "Medical Aid is required.")
        @Positive(message = "Medical Aid must be greater than zero.")
        Double medicalAid,

        @NotNull(message = "Funeral Policy is required.")
        @Positive(message = "Funeral Policy must be greater than zero.")
        Double funeralPolicy
) { }
