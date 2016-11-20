package models;

public class Rating
{
	public int userId, movieId, rating;
	
	public Rating(int userId, int movieId, int rating)
	{
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}

}
