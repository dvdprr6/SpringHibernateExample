package com.david.springhibernate.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.david.springhibernate.persistence.dao.IReservationDao;
import com.david.springhibernate.persistence.dao.common.AbstractReservationDao;

@Repository
public class ReservationDao extends AbstractReservationDao implements IReservationDao{

	public ReservationDao() {
		super();
	}
}
