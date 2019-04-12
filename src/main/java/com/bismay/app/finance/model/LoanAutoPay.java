package com.bismay.app.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loan_autopay")
public class LoanAutoPay {
	
	@Id
	@Column(name="loan_autopay_id")
	private String id;
	
	@Column(name="loan_id")
	private String loanId;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="transaction_amount")
	private double transactionAmount;
	
	@Column(name="pay_on")
	private int payOn;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public int getPayOn() {
		return payOn;
	}
	public void setPayOn(int payOn) {
		this.payOn = payOn;
	}
	
	

}
