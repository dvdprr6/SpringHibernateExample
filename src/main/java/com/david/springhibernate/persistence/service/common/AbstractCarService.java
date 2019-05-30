package com.david.springhibernate.persistence.service.common;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.david.springhibernate.model.db.Car;
import com.david.springhibernate.persistence.dao.common.ICarOperations;

@Transactional(value = "hibernateTransactionManager")
public abstract class AbstractCarService implements ICarOperations{

	@Override
	public List<Car> getAll() {
		return getCarDao().getAll();
	}

	@Override
	public Car getById(Long Id) {
		return getCarDao().getById(Id);
	}

	@Override
	public void insert(Car model) {
		getCarDao().insert(model);
		
	}

	@Override
	public void update(Car model) {
		getCarDao().update(model);
		
	}

	@Override
	public void delete(Long id) {
		getCarDao().delete(id);
		
	}
	
	protected abstract ICarOperations getCarDao();

}
