package com.bismay.app.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bismay.app.finance.model.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan, String> {

	@Query(value = "SELECT * FROM loan WHERE user_id = ?1", nativeQuery = true)
	List<Loan> getLoanByUser(String userId);
	
	@Query(value = "SELECT * FROM loan WHERE loan_id = ?1", nativeQuery = true)
	Loan getLoanById(String loanId);
	
	@Modifying
	@Query(value = "delete from loan where loan_id = ?1", nativeQuery = true)
	void deleteLoanById(String loanId);
	
	@Modifying
	@Query(value = "delete from loan_statement where loan_id = ?1", nativeQuery = true)
	void deleteLoanStatementByLoanId(String loanId);
	
}
