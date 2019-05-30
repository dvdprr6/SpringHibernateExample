package com.david.springhibernate.persistence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.david.springhibernate.persistence.dao.ICarDao;
import com.david.springhibernate.persistence.dao.common.ICarOperations;
import com.david.springhibernate.persistence.service.ICarService;
import com.david.springhibernate.persistence.service.common.AbstractCarService;

@Service
public class CarService extends AbstractCarService implements ICarService{

	@Autowired
	@Qualifier("carDao")
	private ICarDao carDao;
	
	public CarService() {
		super();
	}
	
	@Override
	protected ICarOperations getCarDao() {
		return carDao;
	}

}
