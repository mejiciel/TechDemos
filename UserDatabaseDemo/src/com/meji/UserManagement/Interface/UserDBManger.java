package com.meji.UserManagement.Interface;

import com.meji.UserManagement.UserProfile;

public interface UserDBManger {

	public abstract Boolean CheckUserExistByName(String userName)
			throws Exception;

	public abstract Boolean CheckUserExist(UserProfile user) throws Exception;

	public abstract int CreateUser(UserProfile user);

	public abstract boolean CheckUserExistByEmail(String email);

	public abstract String GetPasswordByEmail(String email);

	public abstract boolean ValidateUser(UserProfile user);

}