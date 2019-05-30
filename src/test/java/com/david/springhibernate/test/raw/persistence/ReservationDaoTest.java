package com.david.springhibernate.test.raw.persistence;

import java.sql.Timestamp;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.david.springhibernate.bl.common.BusinessLogicFactory;
import com.david.springhibernate.bl.common.ReservationBL;
import com.david.springhibernate.model.db.Car;
import com.david.springhibernate.model.db.Reservation;
import com.david.springhibernate.model.db.User;
import com.david.springhibernate.test.utils.DbUnitTest;
import com.david.springhibernate.test.utils.TestConstants;

public class ReservationDaoTest extends DbUnitTest{
	
	public ReservationDaoTest(String name) {
		super(name);
	}
	
	@Before
	public void setUp() throws Exception{
		initTables();
		initializeTableSequence();
	}
	
	@After
	public void tearDown() throws Exception{
		dropTables();
	}
	
	@Test
	public void testGetAll() throws Exception{
		List<Reservation> reservations = BusinessLogicFactory.getBusinessLogic(ReservationBL.class).getAllReservations();
		
		assertNotNull(reservations);
	}
	
	@Test
	public void testGetById() throws Exception{
		long id = 1;

		Reservation reservation = BusinessLogicFactory.getBusinessLogic(ReservationBL.class).getReservationById(id);
		
		assertNotNull(reservation);
	}
	
	@Test
	public void testGetByIdUser() throws Exception{
		String expectedFirstName = "Morty";
		long id = 1;
		
		Reservation reservation = BusinessLogicFactory.getBusinessLogic(ReservationBL.class).getReservationById(id);
		
		String actualFirstName = reservation.getUser().getFirstname();
		
		assertTrue(expectedFirstName.equals(actualFirstName));
	}
	
	@Test
	public void testGetByIdCar() throws Exception{
		String expectedModel = "Morty Carrier";
		long id = 3;
		
		Reservation reservation = BusinessLogicFactory.getBusinessLogic(ReservationBL.class).getReservationById(id);
		
		String actualModel = reservation.getCar().getModel();
		
		assertTrue(expectedModel.equals(actualModel));
	}


	@Test
	public void testUpdate() throws Exception{
		long carId = 4;
		long userId = 1;
		long Id = 3;
		String expectedModel = "Vindicators' Ship";
		
		Car car = new Car();
		car.setId(carId);
		car.setModel(expectedModel);
		car.setLicensePlate("XXX-XXX");
		car.setMileage(800);
		
		User user = new User();
		user.setId(userId);
		user.setFirstname("Morty");
		user.setLastname("Smith");
		user.setEmail("morty.smith@hotmail.ca");
		
		Reservation reservation = new Reservation();
		reservation.setId(Id);
		reservation.setStartDate(new Timestamp(System.currentTimeMillis()));
		reservation.setEndDate(new Timestamp(System.currentTimeMillis()));
		
		reservation.setCar(car);
		reservation.setUser(user);
		
		BusinessLogicFactory.getBusinessLogic(ReservationBL.class).update(reservation);
		
		reservation = BusinessLogicFactory.getBusinessLogic(ReservationBL.class).getReservationById(Id);
		
		String actualModel = reservation.getCar().getModel();
		
		assertTrue(expectedModel.equals(actualModel));
	}
	
	@Test
	public void testInsert() throws Exception{
		String expectedModel = "Vindicators' Ship";
		long carId = 4;
		long userId = 1;
		
		Car car = new Car();
		car.setId(carId);
		car.setModel(expectedModel);
		car.setLicensePlate("XXX-XXX");
		car.setMileage(800);
		
		User user = new User();
		user.setId(userId);
		user.setFirstname("Morty");
		user.setLastname("Smith");
		user.setEmail("morty.smith@hotmail.ca");
		
		Reservation reservation = new Reservation();
		reservation.setStartDate(new Timestamp(System.currentTimeMillis()));
		reservation.setEndDate(new Timestamp(System.currentTimeMillis()));
		reservation.setCar(car);
		reservation.setUser(user);
		
		assertTrue(true);
	}

	@Override
	protected String getPathToDataset() {
		return TestConstants.XML_DAO_RESERVATION_DATASET;
	}

}
