package com.bismay.app.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bismay.app.finance.model.Loan;
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

	@Override
	public LoanStatement getLoanStatement(String loanStatementId) {
		return loanStatementRepository.getLoanStatement(loanStatementId);
	}

	@Override
	public boolean deleteLoanStatement(String loanStatementId) {
		boolean flag = false;
		LoanStatement statement = loanStatementRepository.getLoanStatement(loanStatementId);
		Loan loan = loanRepository.getLoanById(statement.getLoan().getLoanId());
		//deleteing the statment
		try{
			loanStatementRepository.deleteLoanStatement(loanStatementId);
		}catch(Exception e){
			System.out.println(e);
			return flag;
			
		}
		//updating loan
		if(statement.getTransactionType().equalsIgnoreCase("DEBIT")){
			loan.setBalanceAmount(loan.getBalanceAmount() - statement.getTransactionAmount());
			loan.setTotalInterestPaid(loan.getTotalInterestPaid() - statement.getTransactionAmount());
		}
		if(statement.getTransactionType().equalsIgnoreCase("CREDIT")){
			loan.setBalanceAmount(loan.getBalanceAmount() + statement.getTransactionAmount());
			loan.setTotalPrincipalPaid(loan.getTotalPrincipalPaid() - statement.getTransactionAmount());
		}
		this.updateLoan(loan);
		flag = true;
		return flag;
	}

}
