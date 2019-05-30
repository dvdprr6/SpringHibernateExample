package com.david.springhibernate.bl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.david.springhibernate.bl.BusinessLogic;
import com.david.springhibernate.model.db.User;
import com.david.springhibernate.persistence.service.IUserService;
import com.david.springhibernate.utils.PersistenceConfig;

public class UserBL implements BusinessLogic{
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	UserBL() {
		
	}
	
	public List<User> getAllUsers(){
		return userService.getAll();
	}
	
	public User getUserById(Long id) {
		return userService.getById(id);
	}
	
	public void update(User user) {
		userService.update(user);
	}
	public void insert(User user) {
		userService.insert(user);
	}
	
	public void delete(Long id) {
		userService.delete(id);
	}
	
	public int verify(String email, String password) {
		return userService.getUserByEmailAndPassword(email, password);
	}

	@Override
	public void init() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceConfig.class);
		userService = ctx.getBean(IUserService.class);
	}
}