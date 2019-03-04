package com.bismay.app.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bismay.app.finance.model.Loan;
import com.bismay.app.finance.model.LoanAnalysisMonthly;
import com.bismay.app.finance.model.LoanStatement;

@Service
public interface LoanService {

	public void createLoan(Loan loan);
	public List<Loan> getLoanByUser(String userId);
	public void deleteLoanById(String loanId);
	public Loan getLoanById(String loanId);
	public void createLoanStatement(LoanStatement loanStatement);
	public LoanStatement getLoanStatement(String loanStatementId);
	public void updateLoan(Loan loan);
	public List<LoanStatement> getLoanStatementByLoanId(String loanId);
	public List<?> getLoanAnalysisMonthly(String loanId,int year);
	public List<?> getLoanAnalysisYearly(String loanId,int year);
	public boolean deleteLoanStatement(String loanStatementId);
}
