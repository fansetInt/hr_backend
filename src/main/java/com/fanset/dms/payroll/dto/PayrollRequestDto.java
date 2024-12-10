package com.fanset.dms.payroll.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record PayrollRequestDto(
        @NotNull(message = "User ID is required.")
        Long userId,

        @NotNull(message = "Basic Salary is required.")
        @Positive(message = "Basic Salary must be greater than zero.")
        Double basicSalary,

        @NotNull(message = "Overtime is required.")
        @Positive(message = "Overtime must be greater than zero.")
        Double overtime,

        @NotNull(message = "Allowances are required.")
        AllowanceDto allowances,

        @NotNull(message = "Deductions are required.")
       DeductionDto deductions,

        @NotNull(message = "Commissions are required.")
        CommissionDto commissions
) { }
