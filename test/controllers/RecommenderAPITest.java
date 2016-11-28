package controllers;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.User;
import utils.Serializer;
import utils.XMLSerializer;

public class RecommenderAPITest
{
	private RecommenderAPI recommender;
	
	@Before
	public void setUp() throws Exception
	{
		File  datastore = new File("datastoreRecommenderAPITest.xml");
		Serializer serializer = new XMLSerializer(datastore);
		recommender = new RecommenderAPI(serializer);
		recommender.load();
	}

	@After
	public void tearDown() throws Exception
	{
		recommender = null;
	}

	@Test
	public void testAddUser() //Add getUserByUsername
	{
		recommender.addUser("Joe", "Bloggs", 54, 'M', "Network Administrator", "jbloggs", "secret");
		assertEquals(6, recommender.getUsers().size());
		assertEquals("Joe", recommender.getUserById(6L).firstName);
		assertEquals("jbloggs", recommender.getUserById(6L).username);
		assertEquals("secret", recommender.getUserById(6L).password);
	}

}
