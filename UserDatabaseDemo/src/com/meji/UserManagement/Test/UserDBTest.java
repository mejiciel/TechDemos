package com.meji.UserManagement.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
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
		
		Session s=mysqlFactory.getCurrentSession();
		Transaction t=s.beginTransaction();
		
		//List usps=s.createSQLQuery("select * from userprofile").list();
		List<UserProfile> usps=GenerateUserProfile();
		for(UserProfile usp : usps)
		{
			
			//System.out.println(usp);
			//s.delete(usp);
			s.createSQLQuery("DELETE FROM sitemanagement.userprofile WHERE UserName=?").setString(0, usp.getName()).executeUpdate();
		}
		t.commit();
		

	}

	@Test
	public void InsertTest() {
		Session s=mysqlFactory.getCurrentSession();
		Transaction t=s.beginTransaction();
		List<UserProfile> usps=GenerateUserProfile();
		for(UserProfile usp : usps)
		{
			
			Integer id=(Integer)s.save(usp);
			Assert.assertTrue("id<0", id>0);
		}
		t.commit();


	}
	@Test
	public void QueryTest()
	{
		InsertTest();
		Session s=mysqlFactory.getCurrentSession();
		Transaction t=s.beginTransaction();
		List<UserProfile> lst=s.createQuery("from UserProfile where UserName=:NAME").setString("NAME", "meji").list();
		Assert.assertEquals("meji", lst.get(0).getName());
		
		//UserProfile usp=new UserProfile();
		//usp.setName("meji");
		//Assert.assertTrue("user not found",s.contains(usp));
		
	}
	
	@Test
	public void UpdateTest()
	{
		InsertTest();
		Session s=mysqlFactory.getCurrentSession();
		Transaction t=s.beginTransaction();
		List<UserProfile> lst=s.createQuery("from UserProfile where UserName=:NAME").setString("NAME", "meji").list();
		lst.get(0).setName("mejimeji");
		s.update(lst.get(0));
		t.commit();
		
		s=mysqlFactory.getCurrentSession();
		t=s.beginTransaction();
		lst=s.createQuery("from UserProfile where UserName=:NAME").setString("NAME", "mejimeji").list();
		Assert.assertTrue(lst.size()>0);
	}
	private List<UserProfile> GenerateUserProfile()
	{
		List<UserProfile> usps=new ArrayList<UserProfile>();
		UserProfile usp=new UserProfile();
		usp.setName("meji");
		usp.setPassword("1234");
		usp.setEmail("meji@123.com");
		usps.add(usp);
		
		UserProfile usp2=new UserProfile();
		usp2.setName("Fanfan");
		usp2.setPassword("233444");
		usp2.setEmail("fanfan@123.com");
		usps.add(usp2);
		return usps;
		
	}
	@After
	public void teadDown() throws Exception{
		mysqlFactory.close();
	}

}
