package com.bismay.app.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bismay.app.finance.model.LoanAutoPay;

@Repository
public interface LoanAutoPayRepository extends CrudRepository<LoanAutoPay, String>{

	@Query(value = "SELECT * FROM loan_autopay", nativeQuery = true)
	List<LoanAutoPay> getLoanAutoPayList();
	
	@Query(value = "SELECT * FROM loan_autopay where loan_id=?1", nativeQuery = true)
	LoanAutoPay getLoanAutoPayByLoan(String loanId);
}
