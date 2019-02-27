package com.bismay.app.finance.model;

import javax.validation.constraints.NotEmpty;

import com.bismay.app.finance.constraints.FieldMatch;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class PasswordReset {
	    @NotEmpty
	    private String password;

	    @NotEmpty
	    private String confirmPassword;

	    @NotEmpty
	    private String token;

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getConfirmPassword() {
	        return confirmPassword;
	    }

	    public void setConfirmPassword(String confirmPassword) {
	        this.confirmPassword = confirmPassword;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }
}
