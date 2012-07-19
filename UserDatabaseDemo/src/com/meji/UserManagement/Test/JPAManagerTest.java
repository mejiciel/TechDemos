package com.meji.UserManagement.Test;

import junit.framework.Assert;

import org.junit.Test;

import com.meji.UserManagement.JPA_UserManager;
import com.meji.UserManagement.Interface.UserDBManger;

public class JPAManagerTest {
	@Test
	public void CheckUserExistTest()
	{
		UserDBManger umgr=new JPA_UserManager();
		try {
			Assert.assertTrue(umgr.CheckUserExistByName("meji"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
