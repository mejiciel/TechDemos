package com.meji.UserManagement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class UserManager {
	private SessionFactory mysqlFactory;
	public UserManager()
	{
		Configuration cfg=new Configuration();
		cfg.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry(); 
		mysqlFactory = cfg.buildSessionFactory(serviceRegistry);
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		mysqlFactory.close();
	}

	public Boolean CheckUserExistByName(String userName) throws Exception
	{
		Session sess=mysqlFactory.getCurrentSession();
		Transaction t=sess.beginTransaction();
		List<UserProfile> usps=sess.createQuery("from UserProfile where UserName=:Name").setString("Name", userName).list();
		if(usps.size()==0)
			return false;
		else if(usps.size()>1)
			throw new Exception("Duplicated Name");
		
		return true;
		
	}
	public Boolean CheckUserExist(UserProfile user) throws Exception
	{
		Session sess=mysqlFactory.getCurrentSession();
		Transaction t=sess.beginTransaction();
		List<UserProfile> usps=sess.createQuery("from UserProfile where UserName=:@NAME AND Email=:EMAIL")
				.setString("NAME", user.getName())
				.setString("EMAIL", user.getEmail()).list();
		if(usps.size()==0)
			return false;
		else if(usps.size()>1)
			throw new Exception("Duplicated Name");
		
		return true;
	}

	public int CreateUser(UserProfile user) {
		Session sess=mysqlFactory.getCurrentSession();
		Transaction t=sess.beginTransaction();
		Integer id=(Integer)sess.save(user);
		t.commit();
		return id;
		
	}

	public boolean CheckUserExistByEmail(String email) {
		// TODO Auto-generated method stub
		Session sess=mysqlFactory.getCurrentSession();
		Transaction t=sess.beginTransaction();
		List<UserProfile> usps=(List<UserProfile>)sess.createQuery("from UserProfile where Email=:EMAIL")
				.setString("EMAIL", email).list();
		if(usps.size()==1)
			return true;
		return false;
	}

	public String GetPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		Session sess=mysqlFactory.getCurrentSession();
		Transaction t=sess.beginTransaction();
		List<UserProfile> usps=(List<UserProfile>)sess.createQuery("from UserProfile where Email=:EMAIL")
				.setString("EMAIL", email).list();
		if(usps.size()==1)
			return usps.get(0).getPassword();
		return null;
	}

	public boolean ValidateUser(UserProfile user) {
		// TODO Auto-generated method stub
		Session sess=mysqlFactory.getCurrentSession();
		Transaction t=sess.beginTransaction();
		List<UserProfile> usps=sess.createQuery("from UserProfile where UserName=:NAME AND Password=:PASSWORD")
				.setString("NAME", user.getName())
				.setString("PASSWORD", user.getPassword()).list();
		if(usps.size()==1)
			return true;
		
		return false;
	}


}
