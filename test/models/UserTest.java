package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void testCreateUser()
	{
		User user = new User("Bob", "Larkin", 63, 'M', "Carpenter");
		assertEquals("Bob", user.firstName);
		assertEquals("Larkin", user.lastName);
		assertEquals(63, user.age);
		assertEquals('M', user.gender);
		assertEquals("Carpenter", user.occupation);	
	}
	
	@Test
	public void testRemoveUser()
	{
		fail("Not implemented");
	}

}
