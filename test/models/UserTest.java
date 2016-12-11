/**
 * @author mathana
 */
package models;

import static org.junit.Assert.*;

import org.junit.Test;


public class UserTest
{

	/**
	 * Test to verify is a user can be created
	 * @throws Exception
	 */
	@Test
	public void testCreateUser() throws Exception
	{
		User user = new User(22L, "Bob", "Larkin", 63, 'M', "carpenter", "blarkin", "IAmAMenace");
		assertEquals(22, user.userId);
		assertEquals("Bob", user.firstName);
		assertEquals("Larkin", user.lastName);
		assertEquals(63, user.age);
		assertEquals('M', user.gender);
		assertEquals("carpenter", user.occupation);	
		assertEquals("blarkin", user.username);
		assertEquals("IAmAMenace", user.password);
	}

}
