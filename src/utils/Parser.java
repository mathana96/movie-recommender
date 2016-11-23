package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.introcs.In;
import models.Movie;
import models.Rating;
import models.User;

public class Parser
{
	Map<Long, User> users = new HashMap<>();
	Map<Long, Movie> movies = new HashMap<>();
	List<Rating> unsortedRatings = new ArrayList<>();
	RatingByTimeComparator comparator = new RatingByTimeComparator();
	
	public Parser() throws Exception
	{
		parseUserData("././data/moviedata_small/users5.dat");
		parseMovieData("././data/moviedata_small/items5.dat");
		parseRatingData("././data/moviedata_small/ratings5.dat");
	}

	public void parseUserData(String path) throws Exception
	{
		File usersFile = new File(path);
		In inUsers = new In(usersFile);
		String delims = "[|]";
		
		while (!inUsers.isEmpty())
		{
			String userDetails = inUsers.readLine();
			String[] userTokens = userDetails.split(delims);
			
		  if (userTokens.length == 7) 
		  {
		  	long userId = Long.parseLong(userTokens[0]);
		  	String firstName = userTokens[1];
		  	String lastName = userTokens[2];
		  	int age = Integer.parseInt(userTokens[3]);
		  	char gender = userTokens[4].charAt(0);
		  	String occupation = userTokens[5];
		  	
        User user = new User(userId, firstName, lastName, age, gender, occupation);
        users.put(userId, user);

		  }
		  else
		  {
		  	throw new Exception("Invalid member length: "+ userTokens.length);
		  }
		}
	}
	
	public void parseMovieData(String path) throws Exception 
	{
		File moviesFile = new File(path);
		In inMovies = new In(moviesFile);
		String delims = "[|]";
		
		while (!inMovies.isEmpty())
		{
			String movieDetails = inMovies.readLine();
			String[] movieTokens = movieDetails.split(delims);
			
		  if (movieTokens.length == 23) 
		  {
		  	long movieId = Long.parseLong(movieTokens[0]);
		  	String title = movieTokens[1];
		  	int year = Integer.parseInt(movieTokens[2].substring(movieTokens[2].length()-4, movieTokens[2].length()));
		  	String url = movieTokens[3];
		  	
        Movie movie = new Movie(movieId, title, year, url);
        movies.put(movieId, movie);

		  }
		  else
		  {
		  	throw new Exception("Invalid member length: "+ movieTokens.length);
		  }
		}
	}
	
	public void parseRatingData(String path) throws Exception
	{
		File ratingsFile = new File(path);
		In inRatings = new In(ratingsFile);
		String delims = "[|]";
		
		while (!inRatings.isEmpty())
		{
			String ratingDetails = inRatings.readLine();
			String[] ratingTokens = ratingDetails.split(delims);
			
		  if (ratingTokens.length == 4) 
		  {
		  	long userId = Long.parseLong(ratingTokens[0]);
		  	long movieId = Long.parseLong(ratingTokens[1]);
		  	int rating = Integer.parseInt(ratingTokens[2]);
		  	long timestamp = Long.parseLong(ratingTokens[3]);
        Rating r = new Rating(userId, movieId, rating, timestamp);
        unsortedRatings.add(r);
         
      }
		  else
		  {
		  	throw new Exception("Invalid member length: "+ ratingTokens.length);
		  }
		}
		Collections.sort(unsortedRatings, comparator);
		
		for (int i=0; i<unsortedRatings.size(); i++)
		{
			Rating rating = unsortedRatings.get(i);
      User user = getUser(rating.userId);
      Movie movie = getMovie(rating.movieId);
      
      user.ratedMovies.put(rating.movieId, rating.rating);
      movie.userRatings.put(rating.userId, rating.rating);		
		}
	}
	public Map<Long, User> getUsers()
	{
		return users;
	}
	
	public User getUser(long l)
	{
		return users.get(l);
	}
	
	public Map<Long, Movie> getMovies()
	{
		return movies;
	}
	
	public Movie getMovie(long i)
	{
		return movies.get(i);
	}

}
