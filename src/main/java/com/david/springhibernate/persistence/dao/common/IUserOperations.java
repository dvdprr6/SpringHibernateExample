package com.david.springhibernate.persistence.dao.common;

import java.util.List;

import com.david.springhibernate.model.db.User;

public interface IUserOperations {
	public List<User> getAll();
	public User getById(Long Id);
	public void insert(User model);
	public void update(User model);
	public void delete(Long id);
	
	public int getUserByEmailAndPassword(String username, String password);
}
