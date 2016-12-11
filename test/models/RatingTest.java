/**
 * @author mathana
 */
package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class RatingTest
{

	/**
	 * Test to verify that a Rating object can be created correctly
	 */
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
