package com.david.springhibernate.main;

import java.sql.Timestamp;

import com.david.springhibernate.bl.common.BusinessLogicFactory;
import com.david.springhibernate.bl.common.UserBL;
import com.david.springhibernate.model.db.User;

public class Main {
	
	public static void main(String[] args) {
		
		User user = new User();
		user.setFirstname("david");
		user.setLastname("Parr");
		user.setUsername("david.parr");
		user.setEmail("david.parr@hotmail.ca");
		user.setAddress("123 boul");
		user.setPassword("yee");
		user.setModified(new Timestamp(System.currentTimeMillis()));
		user.setDeleted(0);
		
		BusinessLogicFactory.getBusinessLogic(UserBL.class).insert(user);

	}

}
