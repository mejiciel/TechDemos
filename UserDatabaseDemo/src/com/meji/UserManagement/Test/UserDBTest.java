package com.meji.UserManagement.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.meji.UserManagement.UserProfile;

public class UserDBTest {

	private SessionFactory mysqlFactory;
	
	@Before
	public void setUp() throws Exception {
		
		Configuration cfg=new Configuration();
		cfg.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry(); 
		mysqlFactory = cfg.buildSessionFactory(serviceRegistry);

	}

	@Test
	public void test() {
		Session s=mysqlFactory.getCurrentSession();
		Transaction t=s.beginTransaction();
		UserProfile usp=new UserProfile();
		usp.setName("meji");
		usp.setPassword("1234");
		usp.setEmail("meji@123.com");
		
		UserProfile usp2=new UserProfile();
		usp2.setName("Fanfan");
		usp2.setPassword("233444");
		usp2.setEmail("fanfan@123.com");
		s.save(usp);
		s.save(usp2);
		t.commit();
		
		
		/*s=mysqlFactory.getCurrentSession();
		Query q=s.createQuery("select * from UserProfile where UserName='meji'");
		q.e
		t=s.beginTransaction();
		s.delete(usp);
		t.commit();*/

	}
	
	@After
	public void teadDown() throws Exception{
		mysqlFactory.close();
	}

}
