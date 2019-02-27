package com.bismay.app.finance.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordForgot {
	@Email
    @NotEmpty(message = "Email should not be left blank")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
