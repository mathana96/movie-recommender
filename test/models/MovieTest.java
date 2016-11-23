package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTest
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
	public void testCreateMovie()
	{
		Movie movie = new Movie(23, "Jolly Jolly WITmas", 1996, "http://bit.ly/test");
		assertEquals(23,  movie.id);
		assertEquals("Jolly Jolly WITmas", movie.title);
		assertEquals(1996, movie.year);
		assertEquals("http://bit.ly/test", movie.url);
	}

}
