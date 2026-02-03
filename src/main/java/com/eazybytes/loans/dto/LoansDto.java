package com.eazybytes.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {

    @NotEmpty(message = "Mobile number cannot be empty")
    private String MobileNumber;

    @NotEmpty(message = "Loan number cannot be empty")
    private String loanNumber;

    @NotEmpty(message = "Loan type cannot be empty")
    private String loanType;

    @Positive(message = "Total loan should be positive")
    @NotEmpty(message = "Total loan cannot be empty")
    private int totalLoan;

    @PositiveOrZero(message = "Amount should be greater or equal to 0")
    @NotEmpty(message = "Amount paid cannot be empty")
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding should be 0 and positive")
    @NotEmpty(message = "Outstanding amount cannot be empty")
    private int outstandingAmount;
}
