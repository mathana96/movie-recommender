package utils;

import java.util.Comparator;

import models.Rating;


public class RatingByRatingComparator implements Comparator<Rating>
{

	@Override
	public int compare(Rating r1, Rating r2)
	{
		if (r1.averageRating < r2.averageRating) 
			 return +1;
		 else if (r1.averageRating > r2.averageRating) 
			 return -1;
		 else return  0;
	}

}
