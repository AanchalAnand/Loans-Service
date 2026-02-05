package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constants.LoanConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoanRepository;
import com.eazybytes.loans.service.LoanServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoanServiceImpl implements LoanServiceInterface {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> optionalLoans = loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Customer with this mobile number is already registered.");
        }

        loanRepository.save(createNewLoan(mobileNumber));
    }


    private Loans createNewLoan(String mobileNumber) {
        Loans loans = new Loans();
        loans.setMobileNumber(mobileNumber);
        loans.setLoanNumber(new Random().toString());
        loans.setLoanType(LoanConstants.HOME_LOAN);
        loans.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return loans;
    }

    @Override
    public LoansDto fetchLoanDetails(String mobileNumber) {
        Loans loanDetails = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan with this mobile number is not present"));

        return LoansMapper.mapToLoansDto(loanDetails, new LoansDto());


    }

    @Override
    public boolean updateLoanDetails(LoansDto loansDto) {
        Loans loans = loanRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(() -> new ResourceNotFoundException("Loan with this mobile number is not present"));

        LoansMapper.mapToLoans(loansDto, loans);
        loanRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan with this mobile number is not present"));
        loanRepository.deleteById(loans.getLoanId());
        return true;
    }
}
