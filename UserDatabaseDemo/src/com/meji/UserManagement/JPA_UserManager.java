package com.meji.UserManagement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.meji.UserManagement.Interface.UserDBManger;

public class JPA_UserManager implements UserDBManger {
	private EntityManagerFactory emf;
	private EntityManager emgr;
	public JPA_UserManager()
	{
		emf=Persistence.createEntityManagerFactory("UserDatabaseDemo");
		emgr=emf.createEntityManager();
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		emf.close();
	}
	@Override
	public Boolean CheckUserExistByName(String userName) throws Exception {

		List<UserProfile> usps=emgr.createQuery("from UserProfile where name=?1")
				.setParameter(1, userName)
				.getResultList();
		if(usps.size()!=1) return false;

		return true;
	}

	@Override
	public Boolean CheckUserExist(UserProfile user) throws Exception {
 
		List<UserProfile> usps=emgr.createQuery("select * from UserProfile where name=?NAME and email=?EMAIL")
								.setParameter("NAME", user.getName())
								.setParameter("EMAIL", user.getEmail())
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
