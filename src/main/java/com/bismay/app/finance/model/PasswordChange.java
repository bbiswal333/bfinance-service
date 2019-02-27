package com.bismay.app.finance.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.bismay.app.finance.constraints.FieldMatch;

@FieldMatch(first = "newPassword", second = "confirmPassword", message = "The new password and confirm new password fields must match")
public class PasswordChange {

	@NotEmpty
	private String userId;
	
	@NotEmpty(message= "*Please provide your old password")
	private String oldPassword;
	
	@Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your new password")
	private String newPassword;
	
	@NotEmpty(message = "*Please confirm your new password")
	private String confirmPassword;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
