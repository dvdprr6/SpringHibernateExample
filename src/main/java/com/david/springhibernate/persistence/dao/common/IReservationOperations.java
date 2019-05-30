package com.david.springhibernate.persistence.dao.common;

import java.util.List;

import com.david.springhibernate.model.db.Reservation;

public interface IReservationOperations{
	public List<Reservation> getAll();
	public Reservation getById(Long Id);
	public void insert(Reservation model);
	public void update(Reservation model);
	public void delete(Long id);
}
