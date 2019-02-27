package com.bismay.app.finance.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bismay.app.finance.model.Mail;
import com.bismay.app.finance.model.PasswordForgot;
import com.bismay.app.finance.model.PasswordResetToken;
import com.bismay.app.finance.model.User;
import com.bismay.app.finance.repository.PasswordResetTokenRepository;
import com.bismay.app.finance.service.MailService;
import com.bismay.app.finance.service.UserService;

@RestController
public class PasswordForgotController {

	@Autowired 
	private UserService userService;
	@Autowired 
	private PasswordResetTokenRepository tokenRepository;
	@Autowired 
	private MailService emailService;
	
	@PostMapping("/forgot-password")
	public ResponseEntity<String> processForgotPasswordForm(@RequestBody @Valid PasswordForgot passwordForgot,BindingResult bindingResult, HttpServletRequest request) throws BindException{
		User user = userService.findUserByEmail(passwordForgot.getEmail());
        if (user == null){
        	bindingResult.rejectValue("email", null, "We could not find an account for that e-mail address.");
        }
		if(bindingResult.hasErrors()){
			throw new BindException(bindingResult);
		}
		
		PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);
        
        Mail mail = new Mail();
        mail.setFrom("bbiswal333@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");
        
        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "bFinance Team");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);
		
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "plain/text; charset=UTF-8");

		return new ResponseEntity<String>("Reset link has been sent to your email.",headers, HttpStatus.OK);
	}
}
