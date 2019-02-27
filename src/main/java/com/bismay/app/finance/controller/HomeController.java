package com.bismay.app.finance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/dashboard")
	@ResponseBody
	public String home(){
		return "WELCOME TO bFinance";
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/secure/api")
	@ResponseBody
	public ResponseEntity<String> secure(Authentication auth){
		String name  = auth.getName();
		return new ResponseEntity<String>("Access to Secure Services - "+name,HttpStatus.OK);
	}

}
