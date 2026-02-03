package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constants.LoanConstants;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
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
}
