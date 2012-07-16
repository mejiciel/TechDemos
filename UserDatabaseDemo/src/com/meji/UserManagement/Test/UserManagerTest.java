package com.meji.UserManagement.Test;

import junit.framework.Assert;

import org.junit.Test;

import com.meji.UserManagement.UserManager;
import com.meji.UserManagement.UserProfile;
import com.meji.UserManagement.Interface.UserDBManger;

public class UserManagerTest {

	@Test
	public void CheckUserTest()
	{
		UserDBManger umgr=new UserManager();
		try {
			Assert.assertTrue(umgr.CheckUserExistByName("meji"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void CheckUserByObjectTest()
	{
		UserDBManger umgr=new UserManager();
		UserProfile user=new UserProfile();
		
		
		try {
			user.setName("meji");
			user.setName("meji@123.com");
			Assert.assertTrue(umgr.CheckUserExist(user));
			
			user.setName("meji");
			user.setName("mejimeji@123.com");
			Assert.assertFalse(umgr.CheckUserExist(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
