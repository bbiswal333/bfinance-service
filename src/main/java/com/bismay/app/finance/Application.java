package com.bismay.app.finance;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.bismay.app.finance.config.ContextInitializer;
import com.bismay.app.finance.model.Role;
import com.bismay.app.finance.repository.RoleRepository;
import com.bismay.app.finance.utils.Util;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args)  {
		new SpringApplicationBuilder(Application.class)
		.initializers(new ContextInitializer()).run(args);
	}

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		//load the roles into the table
		List<Role> roleList = new ArrayList<Role>();
		
		//create role-1
		Role role1 = new Role();
		role1.setId(new Util().generateLong());
		role1.setName("USER");
		
		//create role-2
		Role role2 = new Role();
		role2.setId(new Util().generateLong());
		role2.setName("ADMIN");
		
		roleList.add(role1);
		roleList.add(role2);
		
		//saving into DB
		if(roleRepository.getAllRoles().isEmpty()){
			roleRepository.saveAll(roleList);
			System.out.println("========= Roles created successfully ========");
		}else{
			System.out.println("Roles are already present in the Database");
		}
		
		
	}
}
