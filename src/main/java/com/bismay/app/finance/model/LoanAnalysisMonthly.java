package com.bismay.app.finance.model;

public class LoanAnalysisMonthly {
	
	private double totalAmount;
	private String transactionType;
	private double year;
	private String month;
	
	public LoanAnalysisMonthly(){
		
	}
	public LoanAnalysisMonthly(double totalAmount,String transactionType,double year, double month){
		this.totalAmount = totalAmount;
		this.transactionType = transactionType;
		this.month = getMonthInString(month);
		this.year = year;
	}
	
	private String getMonthInString(double month2) {
		String [] monthStr = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
		return monthStr[(int)month2 - 1];
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

	public String getMonth() {
		return month;
	}
	

}
