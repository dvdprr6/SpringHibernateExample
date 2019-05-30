package com.david.springhibernate.persistence.service.common;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.david.springhibernate.model.db.User;
import com.david.springhibernate.persistence.dao.common.IUserOperations;

@Transactional(value = "hibernateTransactionManager")
public abstract class AbstractUserService implements IUserOperations{

	@Override
	public List<User> getAll() {
		return getUserDao().getAll();
	}

	@Override
	public User getById(Long Id) {
		return getUserDao().getById(Id);
	}

	@Override
	public void insert(User model) {
		getUserDao().insert(model);
	}

	@Override
	public void update(User model) {
		getUserDao().update(model);
		
	}

	@Override
	public void delete(Long id) {
		getUserDao().delete(id);
		
	}

	@Override
	public int getUserByEmailAndPassword(String username, String password) {
		return getUserDao().getUserByEmailAndPassword(username, password);
	}
	
	protected abstract IUserOperations getUserDao();
	
}
