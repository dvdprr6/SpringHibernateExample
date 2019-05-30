package com.david.springhibernate.persistence.dao.common;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.david.springhibernate.model.db.Reservation;

public abstract class AbstractReservationDao implements IReservationOperations{

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Override
	public List<Reservation> getAll() {
		return getCurrentSession().createQuery("FROM Reservation").list();
	}

	@Override
	public Reservation getById(Long Id) {
		return getCurrentSession().get(Reservation.class, Id);
	}

	@Override
	public void insert(Reservation model) {
		getCurrentSession().save(model);
		
	}

	@Override
	public void update(Reservation model) {
		getCurrentSession().merge(model);
		
	}

	@Override
	public void delete(Long id) {
		throw new UnsupportedOperationException();
		
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
