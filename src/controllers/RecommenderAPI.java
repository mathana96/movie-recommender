package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Movie;
import models.Rating;
import models.User;
import utils.Parser;

public class RecommenderAPI
{
	
	Map<Long, User> users = new HashMap<>();
	Map<Long, Movie> movies = new HashMap<>();
	Parser parser;
	
	
	public RecommenderAPI() throws Exception
	{
		parser = new Parser();
		users = parser.getUsers();
		movies = parser.getMovies();
	}

	public User addUser(Long userId, String firstName, String lastName, int age, char gender, String occupation)
	{
		User user = new User(userId, firstName, lastName, age, gender, occupation);
		
		//users.put(key, value)
		return user;
	}

//	public void removeUser(userId)
//	{
//		
//	}
//	
//	public Movie addMovie(title, year, url)
//	{
//		return movie;
//	}
//	
//	public Rating addRating(userId, movieId, rating)
//	{
//		
//	}
//	
//	public Movie getMovie(movieId)
//	{
//		
//	}
//	
//	public Rating getUserRatings(userId)
//	{
//		
//	}
//	
//	public List<String> getUserRecommendations(userId)
//	{
//		
//	}
//	
//	public List<String> getTopTenMovies(userId)
//	{
//		
//	}
}
