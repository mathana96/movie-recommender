package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Parser;

public class UserTest
{

	@Before
	public void setUp() throws Exception
	{
		
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCreateUser() throws Exception
	{
		User user = new User(22L, "Bob", "Larkin", 63, 'M', "carpenter");
		assertEquals(22, user.userId);
		assertEquals("Bob", user.firstName);
		assertEquals("Larkin", user.lastName);
		assertEquals(63, user.age);
		assertEquals('M', user.gender);
		assertEquals("carpenter", user.occupation);	
	}
	
	@Test
	public void testUserCreatedFromParser()
	{
	
	}

}
