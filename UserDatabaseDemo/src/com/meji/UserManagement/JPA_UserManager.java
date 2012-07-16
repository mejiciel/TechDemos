package com.meji.UserManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA_UserManager implements UserDBManger {
	private EntityManagerFactory emf=Persistence.createEntityManagerFactory("UserDatabaseDemo");
	public JPA_UserManager()
	{
		
	}
	@Override
	public Boolean CheckUserExistByName(String userName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean CheckUserExist(UserProfile user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int CreateUser(UserProfile user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean CheckUserExistByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String GetPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ValidateUser(UserProfile user) {
		// TODO Auto-generated method stub
		return false;
	}

}
