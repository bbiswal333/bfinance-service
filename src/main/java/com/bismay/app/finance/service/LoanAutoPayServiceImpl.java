package com.bismay.app.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bismay.app.finance.model.LoanAutoPay;
import com.bismay.app.finance.repository.LoanAutoPayRepository;

@Service
public class LoanAutoPayServiceImpl implements LoanAutoPayService{

	@Autowired
	private LoanAutoPayRepository loanAutoPayRepository;
	
	@Override
	public void createLoanAutoPay(LoanAutoPay autoPay) {
		loanAutoPayRepository.save(autoPay);
	}

	@Override
	public void updateLoanAutoPay(LoanAutoPay autoPay) {
		loanAutoPayRepository.save(autoPay);
		
	}

	@Override
	public void deleteLoanAutoPay(LoanAutoPay autoPay) {
		loanAutoPayRepository.delete(autoPay);
	}

	@Override
	public List<LoanAutoPay> getLoanAutoPayList() {
		List<LoanAutoPay> list = loanAutoPayRepository.getLoanAutoPayList();
		return list;
	}

	@Override
	public LoanAutoPay getLoanAutoPayByLoan(String loanId) {
		LoanAutoPay loanAutoPay = loanAutoPayRepository.getLoanAutoPayByLoan(loanId);
		return loanAutoPay;
	}

}
