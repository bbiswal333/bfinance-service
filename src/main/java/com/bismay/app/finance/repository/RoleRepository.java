package com.bismay.app.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bismay.app.finance.model.Role;

@Repository
public interface RoleRepository  extends CrudRepository<Role, Integer>{
	
	@Query(value = "SELECT * FROM app_role WHERE role_name = ?1", nativeQuery = true)
	Role findByRole(String role);
	
	@Query(value = "SELECT * FROM app_role", nativeQuery = true)
	List<Role> getAllRoles();
}
