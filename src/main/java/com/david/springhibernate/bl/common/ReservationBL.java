package com.david.springhibernate.bl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.david.springhibernate.bl.BusinessLogic;
import com.david.springhibernate.model.db.Reservation;
import com.david.springhibernate.persistence.service.IReservationService;
import com.david.springhibernate.utils.PersistenceConfig;

public class ReservationBL implements BusinessLogic{

	@Autowired
	@Qualifier("reservationService")
	private IReservationService reservationService;
	
	ReservationBL(){
		
	}
	
	public List<Reservation> getAllReservations(){
		return reservationService.getAll();
	}
	
	public Reservation getReservationById(Long id) {
		return reservationService.getById(id);
	}
	
	public void update(Reservation reservation) {
		reservationService.update(reservation);
	}
	
	public void insert(Reservation reservation) {
		reservationService.insert(reservation);
	}
	
	@Override
	public void init() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceConfig.class);
		reservationService = ctx.getBean(IReservationService.class);
		
	}

}
