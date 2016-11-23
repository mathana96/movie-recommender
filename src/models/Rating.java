package models;

public class Rating
{
	public long userId, movieId;
	public int rating;
	
	public Rating(long userId, long movieId, int rating)
	{
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}

}
