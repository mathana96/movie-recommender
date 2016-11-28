package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Movie;
import models.Rating;
import models.User;
import utils.Parser;
import utils.Serializer;

public class RecommenderAPI
{
  private Serializer serializer;
  
	Map<Long, User> users = new HashMap<>();
	Map<Long, Movie> movies = new HashMap<>();
	Parser parser;
	
	public RecommenderAPI()
	{}
	
	public RecommenderAPI(Serializer serializer) throws Exception
	{
		this.serializer = serializer;
		parser = new Parser(serializer); //Instantiates Parser which reads in raw movie data. 
	}

  @SuppressWarnings("unchecked")
  public void load() throws Exception
  {
    serializer.read();
    movies = (Map<Long, Movie>) serializer.pop();
    users = (Map<Long, User>) serializer.pop();
  }
  
  void store() throws Exception
  {
    serializer.push(users);
    serializer.push(movies);
    serializer.write(); 
  }
  
	public User addUser(String firstName, String lastName, int age, char gender, String occupation, String username, String password)
	{
		long userId = users.size() + 1;
		User user = new User(userId, firstName, lastName, age, gender, occupation);
		user.username = username;
		user.password = password;
		users.put(user.userId, user);
		return user;
	}

//	public void removeUser(userId)
//	{
//		
//	}
//	
//	public Movie addMovie(title, year, url)
//	{
//		
//		return movie;
//	}
	
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
