package controllers;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static models.Fixtures.*;

import models.User;
import utils.Serializer;
import utils.XMLSerializer;

public class RecommenderAPITest
{
	private RecommenderAPI recommender;
	
	@Before
	public void setUp() throws Exception
	{
		recommender = new RecommenderAPI();
		for (User user: usersFixtures)
		{
			recommender.addUser(user.firstName, user.lastName, user.age, user.gender, 
													user.occupation, user.username, user.password);
		}
	}

	@After
	public void tearDown() throws Exception
	{
		recommender = null;
	}

	@Test
	public void testUser() //Add getUserByUsername
	{
		assertEquals(usersFixtures.length, recommender.getUsers().size());
		recommender.addUser("Joe", "Bloggs", 54, 'M', "Network Administrator", "jbloggs", "secret");
		assertEquals(usersFixtures.length + 1, recommender.getUsers().size());
		assertEquals(usersFixtures[0].ratedMovies.size(), recommender.getUserById(usersFixtures[0].userId).ratedMovies.size());			
	}
	
	@Test
	public void testUsers()
	{
		for (User user: usersFixtures)
		{
			assertEquals(user, recommender.getUserById(user.userId));
			assertNotSame(user, recommender.getUserById(user.userId));
		}
	}

}
