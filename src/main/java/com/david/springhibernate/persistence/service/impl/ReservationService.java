package com.david.springhibernate.persistence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.david.springhibernate.persistence.dao.IReservationDao;
import com.david.springhibernate.persistence.dao.common.IReservationOperations;
import com.david.springhibernate.persistence.service.IReservationService;
import com.david.springhibernate.persistence.service.common.AbstractReservationService;

@Service
public class ReservationService extends AbstractReservationService implements IReservationService{

	@Autowired
	@Qualifier("reservationDao")
	private IReservationDao reservationDao;
	
	public ReservationService() {
		super();
	}
	
	@Override
	protected IReservationOperations getReservationDao() {
		return reservationDao;
	}

}
