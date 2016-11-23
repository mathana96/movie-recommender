package utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.User;

public class ParserTest
{

	Parser parser;
	
	@Before
	public void setUp() throws Exception 
	{
		parser = new Parser();
	}

	@After
	public void tearDown() throws Exception
	{
		parser = null;
	}

	@Test
	public void testParseUserData() throws Exception
	{
		assertEquals(5, parser.getUsers().size());
		
		User user =  parser.getUser(1);
		assertEquals("Leonard", user.firstName);
		assertEquals("Hernandez", user.lastName);
		assertEquals(24, user.age);
		assertEquals('M', user.gender);
		assertEquals("technician", user.occupation);	
	}
	
	@Test
	public void testParseMovieData() throws Exception
	{
		assertEquals(10, parser.getMovies().size());
	}
}
