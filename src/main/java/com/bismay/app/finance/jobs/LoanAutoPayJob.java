package com.bismay.app.finance.jobs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bismay.app.finance.model.Loan;
import com.bismay.app.finance.model.LoanAutoPay;
import com.bismay.app.finance.model.LoanStatement;
import com.bismay.app.finance.service.LoanAutoPayService;
import com.bismay.app.finance.service.LoanService;

@Component
public class LoanAutoPayJob {

	@Autowired
	LoanAutoPayService loanAutoPayService;

	@Autowired
	LoanService loanService;

	int count = 0;

	@Scheduled(cron = "0 0 10 * * ?",zone = "GMT+5:30")
	public void loanAutoPayService() {
		System.out.println("Loan Auto Pay job started. Current time is :: " + new Date());
		List<LoanAutoPay> tasks = fetchTodayTasks();
		boolean status = false;
		boolean hasTask = true;
		if (!tasks.isEmpty()) {
			status = executeTasks(tasks);
		} else {
			System.out.println("No tasks found");
			hasTask = false;
		}

		if (status) {
			System.out.println("Loan Auto Pay job finished successfully. Current time is :: " + new Date());
		} else {
			if (hasTask) {
				System.out.println("Something went wrong!");
			} else {
				System.out.println("No Tasks to execute");
			}
		}

	}

	public List<LoanAutoPay> fetchTodayTasks() {
		List<LoanAutoPay> list = loanAutoPayService.getLoanAutoPayList();
		List<LoanAutoPay> tasks = new ArrayList<LoanAutoPay>();

		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		int filter = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println("Day of Month :: " + filter);
		for (LoanAutoPay data : list) {
			if (data.getPayOn() == filter) {
				tasks.add(data);
			}
		}
		return tasks;
	}

	public boolean executeTasks(List<LoanAutoPay> tasks) {
		boolean status = false;
		int totalTask = tasks.size();
		System.out.println("Total task : " + totalTask);
		int count = 1;
		for (LoanAutoPay data : tasks) {
			System.out.println("Task - " + count + ":: STARTED");
			autoPay(data);
			System.out.println("Task - " + count + ":: COMPLETED");
			count++;
		}
		status = true;
		return status;
	}

	public void autoPay(LoanAutoPay data) {
		LoanStatement loanStatement = new LoanStatement();
		Loan loan = loanService.getLoanById(data.getLoanId());
		if (data.getTransactionType().equalsIgnoreCase("DEBIT")) {
			loanStatement.setTransactionType(data.getTransactionType());
			loanStatement.setTransactionAmount(data.getTransactionAmount());
			loanStatement.setDesc("Autopay - INTEREST");
			loan.setBalanceAmount(loan.getBalanceAmount() + data.getTransactionAmount());
			loanStatement.setOutstandingAmount(loan.getBalanceAmount());
			loan.setTotalInterestPaid(loan.getTotalInterestPaid() + data.getTransactionAmount());
		} else {
			loanStatement.setTransactionType(data.getTransactionType());
			loanStatement.setTransactionAmount(data.getTransactionAmount());
			loanStatement.setDesc("Autopay - REPAYMENT");
			loan.setBalanceAmount(loan.getBalanceAmount() - data.getTransactionAmount());
			loanStatement.setOutstandingAmount(loan.getBalanceAmount());
			loan.setTotalPrincipalPaid(loan.getTotalPrincipalPaid() + data.getTransactionAmount());
		}

		loanStatement.setLoanStatementId(UUID.randomUUID().toString());
		loanStatement.setDate(new Date());
		loanStatement.setLoan(loan);

		if (loan.getBalanceAmount() >= 0) {
			
			loanService.createLoanStatement(loanStatement);
			loanService.updateLoan(loan);
		}
	}
}
