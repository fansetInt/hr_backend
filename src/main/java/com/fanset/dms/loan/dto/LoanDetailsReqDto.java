package com.fanset.dms.loan.dto;

import jakarta.validation.constraints.NotNull;

public record LoanDetailsReqDto(
        @NotNull Long loanId,
        @NotNull Long userId,
        @NotNull Double loanAmount,
        @NotNull Double interestRate,
        @NotNull Integer loanDuration,
        @NotNull String loanStatus,
        @NotNull String loanType
) {}

