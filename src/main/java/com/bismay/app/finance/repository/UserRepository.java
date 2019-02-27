package com.bismay.app.finance.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bismay.app.finance.model.PasswordChange;
import com.bismay.app.finance.model.User;

@Repository
public interface UserRepository  extends CrudRepository<User, String>{
	
	@Query(value = "SELECT * FROM app_user WHERE email = ?1", nativeQuery = true)
	User findByEmail(String email);
	
	@Modifying
    @Query(value = "update app_user set password = ?1 where user_id = ?2", nativeQuery = true)
    void updatePassword(String password, String userId);

	@Query(value = "SELECT * FROM app_user WHERE user_id = ?1", nativeQuery = true)
	User findUserByUserId(String userId);
	
	@Modifying
	@Query(value = "update app_user set password = ?2 where user_id = ?1", nativeQuery = true)
	void changePassword(String userId, String password);
}
