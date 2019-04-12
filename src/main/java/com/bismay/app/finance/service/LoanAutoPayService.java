package com.bismay.app.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bismay.app.finance.model.LoanAutoPay;

@Service
public interface LoanAutoPayService {

	public LoanAutoPay getLoanAutoPayByLoan(String loanId);
	public List<LoanAutoPay> getLoanAutoPayList();
	public void createLoanAutoPay(LoanAutoPay autoPay);
	public void updateLoanAutoPay(LoanAutoPay autoPay);
	public void deleteLoanAutoPay(LoanAutoPay autoPay);
}
