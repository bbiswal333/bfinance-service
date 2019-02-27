package com.bismay.app.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bismay.app.finance.model.LoanStatement;

@Repository
public interface LoanStatementRepository extends CrudRepository<LoanStatement, String> {

	@Query(value = "SELECT * FROM loan_statement WHERE loan_id = ?1 order by date desc", nativeQuery = true)
	List<LoanStatement> getLoanStatementByLoanId(String loanId);
}
