package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

public class Movie
{
	public String title, url;
	public int year;
	public long movieId;//, userRatingsId;
	public double averageRating;
	
	public static long counter = 1l;

	public Map<Long, Integer> userRatings = new HashMap<>();

	public Movie(long movieId, String title, int year, String url)
	{
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		this.url = url;
	}

	public void addUserRatings(Long userId, Integer rating)
	{
		//userRatingsId = counter++;
		userRatings.put(userId, rating);
	}

//	public void addAverageRating(int rating)
//	{
//		this.averageRating += rating;
//	}

	public double getAverageRating()
	{
		for (Integer rating: userRatings.values())
		{
			averageRating += rating;
		}
		if (userRatings.size() > 0)
		{
			return averageRating/userRatings.size();
		}
		else
		{
			return 0.0;
		}
		
	}
	
	public String toString()
	{
		return toStringHelper(this).addValue(movieId)
				.addValue(title)
				.addValue(year)
				.addValue(url)
				.toString();
	}

	@Override  
	public int hashCode()  
	{  
		return Objects.hashCode(this.movieId, this.title, this.year, this.url);  
	}  

	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof Movie)
		{
			final Movie other = (Movie) obj;
			return Objects.equal(movieId, other.movieId) 
					&& Objects.equal(title,  other.title)
					&& Objects.equal(year,  other.year)
					&& Objects.equal(url,  other.url);
		}
		else
		{
			return false;
		}
	}
}
