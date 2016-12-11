/**
 * @author mathana
 */
package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test to verify that a Movie object can be created correctly and 
 * that the methods within it function as per intended
 */
public class MovieTest
{
	Movie movie;

	@Before
	public void setUp() throws Exception
	{
		movie = new Movie(23, "Jolly Jolly WITmas", 1996, "http://bit.ly/test");
	}

	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test to create a Movie object
	 */
	@Test
	public void testCreateMovie()
	{
		assertEquals(23,  movie.movieId);
		assertEquals("Jolly Jolly WITmas", movie.title);
		assertEquals(1996, movie.year);
		assertEquals("http://bit.ly/test", movie.url);
	}

	/**
	 * Test to get the average of all the ratings by users of this movie
	 */
	@Test
	public void testGetAverageRating()
	{
		movie.addUserRatings(1L, new Rating(1, movie.movieId, 1));
		movie.addUserRatings(2L, new Rating(1, movie.movieId, 3));
		movie.addUserRatings(3L, new Rating(1, movie.movieId, 5));
		assertEquals(9.0/3, movie.getAverageRating(), 0.01);
	}
}
