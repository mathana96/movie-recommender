/**
 * @author mathana
 */
package utils;

import java.util.Comparator;

import models.Rating;

/**
 * A comparator class to sort the timestamp values of Rating objects in ascending order
 *
 */
public class RatingByTimeComparator implements Comparator<Rating>
{
	@Override
	public int compare(Rating r1, Rating r2)
	{
		if (r1.timestamp > r2.timestamp) 
			return +1;
		else if (r1.timestamp < r2.timestamp) 
			return -1;
		else return  0;
	}

}
