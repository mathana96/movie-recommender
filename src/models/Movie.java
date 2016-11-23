package models;

public class Movie
{
	public String title, url;
	public int year;
	public long id;
	//static long counter = 0L; Based on all movies from DB
	
	//HashSet of userId and ratings
	
	public Movie(long id, String title, int year, String url)
	{
		this.id = id;
		this.title = title;
		this.year = year;
		this.url = url;
	}
	
	
}
