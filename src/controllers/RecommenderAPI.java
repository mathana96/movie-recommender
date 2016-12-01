package controllers;

import java.util.ArrayList;
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
	List<Rating> ratings = new ArrayList<>();
	Parser parser;
	
	public RecommenderAPI()
	{}
	
	
	
	public RecommenderAPI(Serializer serializer) throws Exception
	{
		this.serializer = serializer;
	}

	
	public void loadRawData() throws Exception
	{
		String userDataPath = ("././data/moviedata_small/users5.dat");
		String movieDataPath = ("././data/moviedata_small/items5.dat");
		String ratingDataPath = ("././data/moviedata_small/ratings5.dat");
		
		users = parser.parseUserData(userDataPath);
		movies = parser.parseMovieData(movieDataPath);
		ratings = parser.parseRatingData(ratingDataPath);
		store();
	}
	
  @SuppressWarnings("unchecked")
  public void load() throws Exception
  {
    serializer.read();
    ratings = (List<Rating>) serializer.pop();
    movies = (Map<Long, Movie>) serializer.pop();
    users = (Map<Long, User>) serializer.pop();
  }
  
  public void store() throws Exception
  {
    serializer.push(users);
    serializer.push(movies);
    serializer.push(ratings);
    serializer.write(); 
  }
  
	public User addUser(String firstName, String lastName, int age, char gender, String occupation, String username, String password)
	{
		long userId = users.size() + 1;
		User user = new User(userId, firstName, lastName, age, gender, occupation, username, password);
		//user.username = username;
		//user.password = password;
		users.put(user.userId, user);
		return user;
	}
	
	public Map<Long, User> getUsers()
	{
		return users;
	}
	
	public User getUserById(Long id)
	{
		return users.get(id);
	}
	
//	public User getUserByUsername(String username)
//	{
//		return 
//	}
	public Long getUsersSize()
	{
		return (long) users.size();
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
