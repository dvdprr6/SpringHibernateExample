package com.david.springhibernate.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.david.springhibernate.persistence.dao.ICarDao;
import com.david.springhibernate.persistence.dao.common.AbstractCarDao;

@Repository
public class CarDao extends AbstractCarDao implements ICarDao{

	public CarDao() {
		super();
	}
}
