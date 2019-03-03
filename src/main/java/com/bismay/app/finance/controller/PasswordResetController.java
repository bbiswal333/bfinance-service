package com.bismay.app.finance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bismay.app.finance.model.PasswordReset;
import com.bismay.app.finance.model.PasswordResetToken;
import com.bismay.app.finance.model.User;
import com.bismay.app.finance.repository.PasswordResetTokenRepository;
import com.bismay.app.finance.service.UserService;

@RestController
public class PasswordResetController {
	
	@Autowired private UserService userService;
    @Autowired private PasswordResetTokenRepository tokenRepository;
    @Lazy
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping("/reset-password")
    public String displayResetPasswordPage(@RequestParam(required = false) String token){
    	String res = "";
    	PasswordResetToken resetToken = tokenRepository.findByToken(token);
    	if(resetToken == null){
    		res = "Invalid reset token";
    	}
    	else if(resetToken.isExpired()){
    		res = "Token has expired.Please request a new password reset.";
    	}
    	else{
    		res = "Please input password and confirmPassword in the request body send a POST request.";
    	}
    	return res;
    }
    
    @PostMapping("/reset-password")
    @Transactional
    public String handlePasswordReset(@RequestBody @Valid PasswordReset passwordReset, BindingResult bindingResult) throws BindException{
    	String res = "";
    	if(bindingResult.hasErrors()){
			throw new BindException(bindingResult);
		}
    	if(tokenRepository.findByToken(passwordReset.getToken()).isExpired()){
    		res = "Token has expired.Please request a new password reset.";
    	}
    	PasswordResetToken token = tokenRepository.findByToken(passwordReset.getToken());
    	User user = token.getUser();
    	String updatePassword = passwordEncoder.encode(passwordReset.getPassword());
    	userService.updatePassword(updatePassword, user.getId());
    	tokenRepository.deleteToken(token.getToken());
		
		res = "Password succesfully updated for the user "+user.getFirstname();
		return res;
    	
    }

}
