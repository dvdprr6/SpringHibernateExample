package com.david.springhibernate.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.david.springhibernate.persistence.dao.IUserDao;
import com.david.springhibernate.persistence.dao.common.AbstractUserDao;

@Repository
public class UserDao extends AbstractUserDao implements IUserDao {

	public UserDao() {
		super();
	}
}
