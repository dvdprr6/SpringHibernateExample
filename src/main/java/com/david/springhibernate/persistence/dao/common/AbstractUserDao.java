package com.david.springhibernate.persistence.dao.common;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.david.springhibernate.model.db.User;

import org.hibernate.query.Query;

public abstract class AbstractUserDao implements IUserOperations{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getAll() {
		return getCurrentSession().createQuery("FROM User u WHERE u.deleted = 0").list();
	}

	@Override
	public User getById(Long Id) {
		return getCurrentSession().get(User.class, Id);
	}

	@Override
	public void insert(User model) {
		getCurrentSession().save(model);
		
	}

	@Override
	public void update(User model) {
		getCurrentSession().merge(model);
		
	}

	@Override
	public void delete(Long id) {
		User user = getCurrentSession().get(User.class, id);
		
		getCurrentSession().delete(user);
		
	}

	@Override
	public int getUserByEmailAndPassword(String username, String password) {
		Query query = getCurrentSession().createQuery("FROM User u WHERE u.username like :username and u.password like :password and u.deleted = 0", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		try {
			query.getSingleResult();
			return 1;
		}catch(NoResultException e){
			return 0;
		}
	}
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
