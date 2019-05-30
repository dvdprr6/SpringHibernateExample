package com.david.springhibernate.persistence.dao.common;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.david.springhibernate.model.db.Car;

public abstract class AbstractCarDao implements ICarOperations{
	
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public List<Car> getAll() {
		Query query = getCurrentSession().createQuery("From Car");
		List<Car> allCars = query.list();
		
		return allCars;
	}

	@Override
	public Car getById(Long Id) {
		return getCurrentSession().get(Car.class, Id);
	}

	@Override
	public void insert(Car model) {
		getCurrentSession().save(model);
		
	}

	@Override
	public void update(Car model) {
		getCurrentSession().merge(model);
		
	}

	@Override
	public void delete(Long id) {
		Car car = getCurrentSession().get(Car.class, id);
		getCurrentSession().delete(car);
		
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
