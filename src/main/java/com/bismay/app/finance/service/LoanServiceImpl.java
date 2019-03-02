package com.bismay.app.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bismay.app.finance.model.Loan;
import com.bismay.app.finance.model.LoanAnalysisMonthly;
import com.bismay.app.finance.model.LoanStatement;
import com.bismay.app.finance.repository.LoanRepository;
import com.bismay.app.finance.repository.LoanStatementRepository;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private LoanStatementRepository loanStatementRepository;

	@Override
	public void createLoan(Loan loan) {
		loanRepository.save(loan);
	}

	@Override
	public List<Loan> getLoanByUser(String userId) {
		List<Loan> loanDetails = loanRepository.getLoanByUser(userId);
		return loanDetails;
	}

	@Override
	public void deleteLoanById(String loanId) {
		loanRepository.deleteLoanStatementByLoanId(loanId);
		loanRepository.deleteLoanById(loanId);
		
	}

	@Override
	public Loan getLoanById(String loanId) {
		Loan loan = loanRepository.getLoanById(loanId);
		return loan;
	}

	@Override
	public void createLoanStatement(LoanStatement loanStatement) {
		loanStatementRepository.save(loanStatement);
	}

	@Override
	public void updateLoan(Loan loan) {
		loanRepository.save(loan);
	}

	@Override
	public List<LoanStatement> getLoanStatementByLoanId(String loanId) {
		return loanStatementRepository.getLoanStatementByLoanId(loanId);
	}

	@Override
	public List<?> getLoanAnalysisMonthly(String loanId) {
		return loanStatementRepository.getLoanAnalysisMonthly(loanId);
	}

	@Override
	public List<?> getLoanAnalysisYearly(String loanId, int year) {
		return loanStatementRepository.getLoanAnalysisYearly(loanId, year);
	}

}
