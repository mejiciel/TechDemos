package com.meji.UserManagement.Test;

import junit.framework.Assert;

import org.junit.Test;

import com.meji.UserManagement.UserManager;

public class UserManagerTest {

	@Test
	public void CheckUserTest()
	{
		UserManager umgr=new UserManager();
		try {
			Assert.assertTrue(umgr.CheckUserByName("meji"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
