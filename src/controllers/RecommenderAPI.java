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
		users.put(user.userId, user);
		return user;
	}
	
	public Movie addMovie(String title, int year, String url)
	{
		long movieId = movies.size() + 1;
		Movie movie = new Movie(movieId, title, year, url);
		movies.put(movieId, movie);
		return movie;
	}
	
	public Rating addRating(long userId, long movieId, int rating)
	{
		Rating r = new Rating(userId, movieId, rating);
		ratings.add(r);
		return r;
	}
	
	public List<Rating> getRatings()
	{
		return ratings;
	}
	
	public Map<Long, Movie> getMovies()
	{
		return movies;
	}
	
	public Movie getMovieById(Long id)
	{
		return movies.get(id);
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

//	public void removeUser(userId)
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
