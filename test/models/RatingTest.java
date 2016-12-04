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
		Rating rating = new Rating(1, 10, 5, 234324234);
		assertEquals(1, rating.userId);
		assertEquals(10, rating.movieId);
		assertEquals((Integer) 5, rating.rating);
		assertEquals(234324234, rating.timestamp);
	}

}
