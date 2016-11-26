package models;

import java.util.HashMap;
import java.util.Map;

public class Movie
{
	public String title, url;
	public int year;
	public long movieId, userRatingsId;
	
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
}
