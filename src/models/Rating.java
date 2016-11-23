package models;

public class Rating
{
	public long userId, movieId, timestamp;
	public int rating;
	
	public Rating(long userId, long movieId, int rating, long timestamp)
	{
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
		this.timestamp = timestamp;
	}

}
