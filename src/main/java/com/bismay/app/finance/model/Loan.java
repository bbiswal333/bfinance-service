package com.bismay.app.finance.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "loan")
public class Loan {
	
	@Id
	@Column(name="loan_id")
	private String loanId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name="loan_type")
	private String loanType;
	
	@Column(name="loan_desc")
	private String loanDesc;
	
	@Column(name="principal_amount")
	private double principalAmount;
	
	@Column(name="interest_rate")
	private double interestRate;
	
	@Column(name="total_interest_paid")
	private double totalInterestPaid;
	
	@Column(name="balance_amount")
	private double balanceAmount;
	
	@Column(name="total_principal_paid")
	private double totalPrincipalPaid;
	
	@Column(name="loan_bank")
	private String loanBank;
	
	@Column(name="loan_year")
	private String loanYear;
	
//	@OneToMany(mappedBy="loan")
//	private List<LoanStatement> loanStatements;
	
	
//	public List<LoanStatement> getLoanStatements() {
//		return loanStatements;
//	}
//	public void setLoanStatements(List<LoanStatement> loanStatements) {
//		this.loanStatements = loanStatements;
//	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getLoanDesc() {
		return loanDesc;
	}
	public void setLoanDesc(String loanDesc) {
		this.loanDesc = loanDesc;
	}
	public double getPrincipalAmount() {
		return principalAmount;
	}
	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}
	
	public double getTotalInterestPaid() {
		return totalInterestPaid;
	}
	public void setTotalInterestPaid(double totalInterestPaid) {
		this.totalInterestPaid = totalInterestPaid;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public double getTotalPrincipalPaid() {
		return totalPrincipalPaid;
	}
	public void setTotalPrincipalPaid(double totalPrincipalPaid) {
		this.totalPrincipalPaid = totalPrincipalPaid;
	}
	public String getLoanBank() {
		return loanBank;
	}
	public void setLoanBank(String loanBank) {
		this.loanBank = loanBank;
	}
	public String getLoanYear() {
		return loanYear;
	}
	public void setLoanYear(String loanYear) {
		this.loanYear = loanYear;
	}
	
	

}
