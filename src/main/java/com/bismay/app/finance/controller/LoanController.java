package com.bismay.app.finance.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bismay.app.finance.model.Loan;
import com.bismay.app.finance.model.LoanStatement;
import com.bismay.app.finance.model.User;
import com.bismay.app.finance.service.LoanService;
import com.bismay.app.finance.service.UserService;

@RestController
public class LoanController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoanService loanService;
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/loan/{loanId}/statement")
	public ResponseEntity<List<LoanStatement>> getLoanStatementByLoanId(@PathVariable(value = "loanId") final String loanId){
		List<LoanStatement> statements = loanService.getLoanStatementByLoanId(loanId);
		return new ResponseEntity<List<LoanStatement>>(statements,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/loan/{loanId}/statement")
	public void createLoanStatement(@RequestBody final LoanStatement loanStatement, @PathVariable(value = "loanId") final String loanId) throws ParseException{
		Loan loan = loanService.getLoanById(loanId);
		if(loanStatement.getTransactionType().equalsIgnoreCase("DEBIT")){
			loan.setBalanceAmount(loan.getBalanceAmount() + loanStatement.getTransactionAmount());
			loan.setTotalInterestPaid(loan.getTotalInterestPaid() + loanStatement.getTransactionAmount());
		}else{
			loan.setBalanceAmount(loan.getBalanceAmount() - loanStatement.getTransactionAmount());
			loan.setTotalPrincipalPaid(loan.getTotalPrincipalPaid() + loanStatement.getTransactionAmount());
		}
		
		loanStatement.setLoanStatementId( UUID.randomUUID().toString());
		loanStatement.setLoan(loan);
		
		
		loanService.createLoanStatement(loanStatement);
		loanService.updateLoan(loan);
		
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/loan/{loanId}")
	public ResponseEntity<Loan> getLoanById(@PathVariable(value = "loanId") final String loanId){
		Loan loan = loanService.getLoanById(loanId);
		if(loan != null){
			return new ResponseEntity<Loan>(loan,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/loan/{loanId}")
	public void deleteLoanByLoanId(@PathVariable(value = "loanId") final String loanId){
		loanService.deleteLoanById(loanId);
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/loan")
	public ResponseEntity<Loan> createLoan(@RequestBody final Loan loan, Authentication auth){
		//create loanId
		final String loanId = UUID.randomUUID().toString();
		loan.setLoanId(loanId);
		//set user
		User user = userService.findUserByEmail(auth.getName());
		loan.setUser(user);
		
		//statements
		//loan.setLoanStatements(null);
		
		// set amount
		loan.setTotalInterestPaid(0.00);
		if(loan.getBalanceAmount() != 0){
			loan.setTotalPrincipalPaid(loan.getPrincipalAmount()-loan.getBalanceAmount());
		}else{
			loan.setTotalPrincipalPaid(0);
		}
		
		loanService.createLoan(loan);
		return new ResponseEntity<Loan>(loan, HttpStatus.CREATED);
	}

	@GetMapping("/loan")
	public ResponseEntity<List<Loan>> getLoanDetailsByUser(Authentication auth){
		User user = userService.findUserByEmail(auth.getName());
		List<Loan> loanDetails = loanService.getLoanByUser(user.getId());
		return new ResponseEntity<List<Loan>>(loanDetails,HttpStatus.OK);
	}
}