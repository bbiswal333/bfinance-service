package com.bismay.app.finance.service;

import com.bismay.app.finance.model.PasswordChange;
import com.bismay.app.finance.model.User;

public interface UserService {

	public User findUserByEmail(String email);
	public User findUserByUserId(String userId);
	public void createUser(User user);
	public void updatePassword(String password, String userId);
	public User updateUserDetails(User user);
	public void changePassword(PasswordChange payload);
}
