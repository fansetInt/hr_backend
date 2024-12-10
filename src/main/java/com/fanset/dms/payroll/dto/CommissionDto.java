package com.fanset.dms.payroll.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CommissionDto(
        @NotNull(message = "Installation Commissions are required.")
        @Positive(message = "Installation Commissions must be greater than zero.")
        Double installationCommissions,

        @NotNull(message = "Sales Commissions are required.")
        @Positive(message = "Sales Commissions must be greater than zero.")
        Double salesCommissions,

        @NotNull(message = "Total Payable is required.")
        @Positive(message = "Total Payable must be greater than zero.")
        Double totalPayable

) { }
