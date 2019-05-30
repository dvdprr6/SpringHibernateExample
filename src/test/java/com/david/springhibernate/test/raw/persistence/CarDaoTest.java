package com.david.springhibernate.test.raw.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.david.springhibernate.bl.common.BusinessLogicFactory;
import com.david.springhibernate.bl.common.CarBL;
import com.david.springhibernate.model.db.Car;
import com.david.springhibernate.test.utils.DbUnitTest;
import com.david.springhibernate.test.utils.TestConstants;

import java.util.List;

import org.hibernate.query.Query;

public class CarDaoTest extends DbUnitTest{

	public CarDaoTest(String name) {
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
		
		List<Car> allCars = BusinessLogicFactory.getBusinessLogic(CarBL.class).getAllCars();
		
		assertNotNull(allCars);
	}
	
	@Test
	public void testGetById() throws Exception{
		String expectedModel = "Morty Carrier";
		long id = 3;
		
		Car car = BusinessLogicFactory.getBusinessLogic(CarBL.class).getCarById(id);
		
		String actualModel = car.getModel();
		
		assertTrue(expectedModel.equals(actualModel));
	}
	
	@Test
	public void testUpdate() throws Exception{
		String expectedUpdateModel = "Lancer";
		long id = 2;
		
		Car car = BusinessLogicFactory.getBusinessLogic(CarBL.class).getCarById(id);
		
		car.setModel(expectedUpdateModel);
		
		BusinessLogicFactory.getBusinessLogic(CarBL.class).update(car);
		
		car = BusinessLogicFactory.getBusinessLogic(CarBL.class).getCarById(id);
		
		String actualUpdateModel = car.getModel();
		
		assertTrue(expectedUpdateModel.equals(actualUpdateModel));
		
	}
	
	@Test
	public void testInsert() throws Exception{
		String expectedModel = "Beta-seven fighter";
		String expectedLicensePlate = "XXX-XXX";
		Integer expectedMileage = 900000;
		
		Car car = new Car();
		car.setModel(expectedModel);
		car.setLicensePlate(expectedLicensePlate);
		car.setMileage(expectedMileage);
		
		BusinessLogicFactory.getBusinessLogic(CarBL.class).insert(car);
		
		assertTrue(true);
		
	}
	
//	@Test
//	public void testDelete() throws Exception{
//		
//		Car car = new Car();
//		car.setId(1L);
//		car.setModel("Space Cruiser");
//		car.setLicensePlate("XXX-XXX");
//		car.setMileage(9000);
//		
//		BusinessLogicFactory.getBusinessLogic(CarBL.class).delete(1L);
//		
//		assertNull(car);
//		
//	}

	@Override
	protected String getPathToDataset() {
		return TestConstants.XML_DAO_CAR_DATASET;
	}

}
