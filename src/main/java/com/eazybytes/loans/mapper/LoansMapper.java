package com.eazybytes.loans.mapper;

import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());

        return loansDto;

    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }
}
