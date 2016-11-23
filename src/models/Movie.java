package models;

import java.util.HashMap;
import java.util.Map;

public class Movie
{
	public String title, url;
	public int year;
	public long id;
	
	public Map<Long, Integer> userRatings = new HashMap<>();
	
	public Movie(long id, String title, int year, String url)
	{
		this.id = id;
		this.title = title;
		this.year = year;
		this.url = url;
	}
	
	
}
