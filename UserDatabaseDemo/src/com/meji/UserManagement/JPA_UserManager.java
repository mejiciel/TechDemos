package com.meji.UserManagement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.meji.UserManagement.Interface.UserDBManger;

public class JPA_UserManager implements UserDBManger {
	private EntityManagerFactory emf=Persistence.createEntityManagerFactory("UserDatabaseDemo");
	private EntityManager emgr;
	public JPA_UserManager()
	{
		emgr=emf.createEntityManager();
	}
	@Override
	public Boolean CheckUserExistByName(String userName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean CheckUserExist(UserProfile user) throws Exception {
		// TODO Auto-generated method stub
		List<UserProfile> usps=emgr.createQuery("select * from UserProfile where name=?1 and email=?2")
								.setParameter(1, user.getName())
								.setParameter(2, user.getEmail())
								.getResultList();
		if(usps.size()!=1) return false;
		
		return true;
	}

	@Override
	public int CreateUser(UserProfile user) {
		// TODO Auto-generated method stub
		emgr.persist(user);
		emgr.flush();
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
