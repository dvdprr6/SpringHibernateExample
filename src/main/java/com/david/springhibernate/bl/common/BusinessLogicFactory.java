package com.david.springhibernate.bl.common;

import com.david.springhibernate.bl.BusinessLogic;

public class BusinessLogicFactory {

	public static <BL extends BusinessLogic> BL getBusinessLogic(Class<BL> businessLogicClass){
		BL businessLogic = null;
		
		try {
			businessLogic = businessLogicClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		businessLogic.init();
		return businessLogic;
	}
}
