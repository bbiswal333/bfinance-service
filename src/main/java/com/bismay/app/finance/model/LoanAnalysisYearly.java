package com.bismay.app.finance.model;

public class LoanAnalysisYearly {
	private double totalAmount;
	private String transactionType;
	private double year;
	
	public LoanAnalysisYearly(){
		
	}
	public LoanAnalysisYearly(double totalAmount,String transactionType,double year){
		this.totalAmount = totalAmount;
		this.transactionType = transactionType;
		this.year = year;
	}
	

	public double getTotalAmount() {
		return totalAmount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public double getYear() {
		return year;
	}

}
