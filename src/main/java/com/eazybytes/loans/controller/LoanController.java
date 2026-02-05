package com.eazybytes.loans.controller;

import com.eazybytes.loans.constants.LoanConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.dto.ResponseDto;
import com.eazybytes.loans.service.LoanServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan/api")
public class LoanController {

    @Autowired
    LoanServiceInterface loanService;

    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber){
        loanService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @GetMapping("/mobileNumber")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber){
        LoansDto loansDto = loanService.fetchLoanDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(loansDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@RequestBody LoansDto loansDto){
        boolean loanUpdated = loanService.updateLoanDetails(loansDto);

        if(loanUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/mobileNumber")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam String mobileNumber){
        boolean loanDeleted = loanService.deleteLoan(mobileNumber);
        if(loanDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
        }
    }
}

