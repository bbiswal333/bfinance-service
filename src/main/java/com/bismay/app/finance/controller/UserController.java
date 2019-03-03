package com.bismay.app.finance.controller;

import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bismay.app.finance.model.PasswordChange;
import com.bismay.app.finance.model.User;
import com.bismay.app.finance.service.UserService;

@RestController
public class UserController {
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService,@Lazy BCryptPasswordEncoder bCryptPasswordEncoder){
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/signup")
	public ResponseEntity<User> createUser(@RequestBody @Valid final User user,BindingResult bindingResult) throws BindException{
		//check if user exists
		User userExist = userService.findUserByEmail(user.getEmail());
		if(userExist != null){
			bindingResult
            .rejectValue("email", "error.user",
                    "There is already a user registered with the email provided");
		}
		if(bindingResult.hasErrors()){
			throw new BindException(bindingResult);
		}else{
			final String UserId = UUID.randomUUID().toString();
			user.setId(UserId);
			userService.createUser(user);
		}
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/user")
	public ResponseEntity<User> getLoggedInUser(Authentication auth){
		String email = auth.getName();
		User user = userService.findUserByEmail(email);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody @Valid final User user,BindingResult bindingResult)throws BindException{
		if(bindingResult.hasErrors()){
			throw new BindException(bindingResult);
		}
		User updatedUser = userService.updateUserDetails(user);
		return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/user/changePassword")
	@Transactional
	public void changePassword(@RequestBody @Valid final PasswordChange payload, BindingResult bindingResult) throws BindException{
		//old password verification
		User user = userService.findUserByUserId(payload.getUserId());
		if(!bCryptPasswordEncoder.matches(payload.getOldPassword(),user.getPassword())){
			bindingResult
            .rejectValue("oldPassword", "error.passwordchange",
                    "Invalid old password provided");
		}
		
		if(bindingResult.hasErrors()){
			throw new BindException(bindingResult);
		}
		userService.changePassword(payload);
	}
	
}
