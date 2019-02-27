package com.bismay.app.finance.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bismay.app.finance.model.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {

	@Query(value = "SELECT * FROM password_reset_token WHERE token = ?1", nativeQuery = true)
    PasswordResetToken findByToken(String token);
	
	@Modifying
	@Query(value = "delete from password_reset_token where token = ?1", nativeQuery = true)
	void deleteToken(String token);

}
