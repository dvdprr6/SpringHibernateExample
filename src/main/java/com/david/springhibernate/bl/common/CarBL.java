package com.david.springhibernate.bl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.david.springhibernate.bl.BusinessLogic;
import com.david.springhibernate.model.db.Car;
import com.david.springhibernate.persistence.service.ICarService;
import com.david.springhibernate.utils.PersistenceConfig;

public class CarBL implements BusinessLogic{
	
	@Autowired
	@Qualifier("carService")
	private ICarService carService;
	
	CarBL(){
		
	}
	
	public List<Car> getAllCars(){
		return carService.getAll();
	}
	
	public Car getCarById(Long id) {
		return carService.getById(id);
	}
	
	public void update(Car car) {
		carService.update(car);
	}
	
	public void insert(Car car) {
		carService.insert(car);
	}
	
	public void delete(Long id) {
		carService.delete(id);
	}

	@Override
	public void init() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceConfig.class);
		carService = ctx.getBean(ICarService.class);
	}
	
	
}
