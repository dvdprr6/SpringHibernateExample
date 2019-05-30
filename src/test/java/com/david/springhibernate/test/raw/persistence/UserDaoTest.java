package com.david.springhibernate.test.raw.persistence;

import java.sql.Timestamp;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.david.springhibernate.bl.common.BusinessLogicFactory;
import com.david.springhibernate.bl.common.UserBL;
import com.david.springhibernate.model.db.User;
import com.david.springhibernate.test.utils.DbUnitTest;
import com.david.springhibernate.test.utils.TestConstants;

public class UserDaoTest extends DbUnitTest {
	
	public UserDaoTest(String name) {
		super(name);
	}
	
	@Before
	public void setUp() throws Exception {
		initTables();
		initializeTableSequence();
	}

	@After
	public void tearDown() throws Exception {
		dropTables();
	}

	@Test
	public void testGetAll() throws Exception{
		List<User> allUsers = BusinessLogicFactory.getBusinessLogic(UserBL.class).getAllUsers();
		
		assertNotNull(allUsers);
		
	}
	
	@Test
	public void testGetById() throws Exception{
		String expectedFirstName = "Morty";
		long id = 1;
		
		User user = BusinessLogicFactory.getBusinessLogic(UserBL.class).getUserById(id);
		
		String actualFirstName = user.getFirstname();
		
		assertTrue(expectedFirstName.equals(actualFirstName));
	}
	
	@Test
	public void testUpdate() throws Exception{
		String expectedUpdateFirstName = "Jerry";
		long id = 1;
		
		User user = BusinessLogicFactory.getBusinessLogic(UserBL.class).getUserById(id);
		
		
		user.setFirstname(expectedUpdateFirstName);
		
		BusinessLogicFactory.getBusinessLogic(UserBL.class).update(user);
		
		user = BusinessLogicFactory.getBusinessLogic(UserBL.class).getUserById(id);
		
		String actualUpdateFirstName = user.getFirstname();
		
		assertTrue(expectedUpdateFirstName.equals(actualUpdateFirstName));
		
	}
	
	@Test
	public void testInsert() throws Exception{
		String expectedFirstName = "Jerry";
		
		
		User user = new User();
		user.setFirstname(expectedFirstName);
		user.setLastname("Smith");
		user.setUsername("jerry.smith");
		user.setEmail("jerry.smith@hotmail.ca");
		user.setAddress("Washington, USA, Earth");
		user.setPassword("Password01");
		user.setModified(new Timestamp(System.currentTimeMillis()));
		user.setDeleted(0);
		
		BusinessLogicFactory.getBusinessLogic(UserBL.class).insert(user);
		
		assertTrue(true);
		
	}
	
	@Test
	public void testDelete() throws Exception{
		long id = 1;
		User user = new User();
		user.setId(1L);
		user.setFirstname("Morty");
		user.setLastname("Smith");
		user.setUsername("morty.smith");
		user.setEmail("morty.smith@hotmail.ca");
		
		BusinessLogicFactory.getBusinessLogic(UserBL.class).delete(id);
		
		assertTrue(true);

	}
	
	@Test
	public void testGetByEmailAndPassword() throws Exception{
		String username = "admin";
		String password = "Password01";
		
		int result = BusinessLogicFactory.getBusinessLogic(UserBL.class).verify(username, password);
		
		assertEquals(1, result);
	}

	@Override
	protected String getPathToDataset() {
		return TestConstants.XML_DAO_USER_DATASET;
	}

}
