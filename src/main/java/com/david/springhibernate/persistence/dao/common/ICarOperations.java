package com.david.springhibernate.persistence.dao.common;

import java.util.List;

import com.david.springhibernate.model.db.Car;

public interface ICarOperations {
	public List<Car> getAll();
	public Car getById(Long Id);
	public void insert(Car model);
	public void update(Car model);
	public void delete(Long id);
}
