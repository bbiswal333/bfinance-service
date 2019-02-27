package com.bismay.app.finance.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;

import com.bismay.app.finance.model.PasswordChange;
import com.bismay.app.finance.model.Role;
import com.bismay.app.finance.model.User;
import com.bismay.app.finance.repository.RoleRepository;
import com.bismay.app.finance.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,@Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		User user = userRepository.findByEmail(email);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
        	authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(),authorities);

        return userDetails;
	}

	@Override
	public void updatePassword(String password, String userId) {
		userRepository.updatePassword(password, userId);
	}

	@Override
	public User findUserByUserId(String userId) {
		User user = userRepository.findUserByUserId(userId);
		return user;
	}

	@Override
	public User updateUserDetails(User user) {
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	@Override
	public void changePassword(PasswordChange payload) {
		//encrypting password
		payload.setNewPassword(bCryptPasswordEncoder.encode(payload.getNewPassword()));
		userRepository.changePassword(payload.getUserId(), payload.getNewPassword());
	}

}
