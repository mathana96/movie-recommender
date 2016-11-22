package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RatingTest
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
	public void testCreateRating()
	{
		//Amend when able to give id to users and movie
		Rating rating = new Rating(1, 10, 5);
		assertEquals(1, rating.userId);
		assertEquals(10, rating.movieId);
		assertEquals(5, rating.rating);
	}

}
