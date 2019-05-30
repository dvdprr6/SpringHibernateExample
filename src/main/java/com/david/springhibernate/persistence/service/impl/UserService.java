package com.david.springhibernate.persistence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.david.springhibernate.persistence.dao.IUserDao;
import com.david.springhibernate.persistence.dao.common.IUserOperations;
import com.david.springhibernate.persistence.service.IUserService;
import com.david.springhibernate.persistence.service.common.AbstractUserService;

@Service
public class UserService extends AbstractUserService implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private IUserDao userDao;
	
	public UserService() {
		super();
	}
	
	@Override
	protected IUserOperations getUserDao() {
		return userDao;
	}


}
