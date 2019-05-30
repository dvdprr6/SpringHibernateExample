package com.david.springhibernate.persistence.service.common;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.david.springhibernate.model.db.Reservation;
import com.david.springhibernate.persistence.dao.common.IReservationOperations;

@Transactional(value = "hibernateTransactionManager")
public abstract class AbstractReservationService implements IReservationOperations{

	@Override
	public List<Reservation> getAll() {
		return getReservationDao().getAll();
	}

	@Override
	public Reservation getById(Long Id) {
		return getReservationDao().getById(Id);
	}

	@Override
	public void insert(Reservation model) {
		getReservationDao().insert(model);
		
	}

	@Override
	public void update(Reservation model) {
		getReservationDao().update(model);
		
	}

	@Override
	public void delete(Long id) {
		getReservationDao().delete(id);
		
	}
	
	protected abstract IReservationOperations getReservationDao();

}
