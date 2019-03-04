package com.bismay.app.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bismay.app.finance.model.LoanStatement;

@Repository
public interface LoanStatementRepository extends CrudRepository<LoanStatement, String> {

	@Query(value = "SELECT * FROM loan_statement WHERE loan_id = ?1 order by date desc", nativeQuery = true)
	List<LoanStatement> getLoanStatementByLoanId(String loanId);
	
	@Query(value = "SELECT SUM(transaction_amount) as totalAmount, transaction_type as transactionType , extract(year from date) as year, extract(month from date) as month from loan_statement where loan_id= ?1 " + 
					"and extract(year from date)=extract(year from current_date) group by transaction_type,  extract(month from date),  extract(year from date) order by extract(month from date),extract(year from date) asc", nativeQuery = true)
	List<?> getLoanAnalysisMonthly(String loanId);
	
	@Query(value = "SELECT SUM(transaction_amount) as totalAmount, transaction_type as transactionType , extract(year from date) as year from loan_statement where loan_id= ?1 " + 
			"and extract(year from date)=?2 group by transaction_type,  extract(year from date)", nativeQuery = true)
	List<?> getLoanAnalysisYearly(String loanId,int year);
	
	@Query(value = "SELECT * FROM loan_statement WHERE loan_statement_id = ?1", nativeQuery = true)
	LoanStatement getLoanStatement(String loanStatementId);
	
	@Modifying
	@Query(value = "DELETE FROM loan_statement WHERE loan_statement_id = ?1", nativeQuery = true)
	void deleteLoanStatement(String loanStatementId);
	
}
