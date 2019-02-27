package com.bismay.app.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bismay.app.finance.model.Loan;
import com.bismay.app.finance.model.LoanStatement;

@Service
public interface LoanService {

	public void createLoan(Loan loan);
	public List<Loan> getLoanByUser(String userId);
	public void deleteLoanById(String loanId);
	public Loan getLoanById(String loanId);
	public void createLoanStatement(LoanStatement loanStatement);
	public void updateLoan(Loan loan);
	public List<LoanStatement> getLoanStatementByLoanId(String loanId);
}
