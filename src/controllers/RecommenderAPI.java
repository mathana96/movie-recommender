package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import models.Movie;
import models.Rating;
import models.User;
import utils.MovieAverageRatingComparator;
import utils.Parser;
import utils.RatingByRatingComparator;
import utils.Serializer;

public class RecommenderAPI
{
  private Serializer serializer;
  
	Map<Long, User> users = new HashMap<>();
	Map<Long, Movie> movies = new HashMap<>();
	List<Rating> ratings = new ArrayList<>();
	Parser parser;
	RatingByRatingComparator ratingComparator;
	MovieAverageRatingComparator movieAvgComparator;

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
	
	public void removeUser(long userId)
	{
		users.remove(userId);
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
		User user = getUserById(r.userId);
		Movie movie = getMovieById(r.movieId);

		user.addRatedMovies(movie.movieId, r);
		movie.addUserRatings(user.userId, r);
//		movie.addAverageRating(r.rating);
		ratings.add(r);
		
		return r;
	}
	
	public List<Movie> getTopTenMovies()
	{
		List<Movie> topTen = new ArrayList<>(movies.values());
		movieAvgComparator = new MovieAverageRatingComparator();
		Collections.sort(topTen, movieAvgComparator);
		if (topTen.size() > 10)
		{
			return topTen.subList(0, 10);
		}
		else
		{
			return topTen;
		}
	}
	public List<Movie> getUserRecommendations(long userId)
	{
		ratingComparator = new RatingByRatingComparator(); //Sorts highest to lowest rating
		movieAvgComparator = new MovieAverageRatingComparator(); //Sorts highest to lowest rating average of a movie
		HashSet<User> targetUsers = new HashSet<>();
		Set<Movie> recommendedMoviesSet = new HashSet<>();
		
		User currentUser = getUserById(userId);
		List<Rating> currentUserRatings = new ArrayList<>(currentUser.ratedMovies.values());
		
		Collections.sort(currentUserRatings, ratingComparator);
		
		for (Rating currentUserRating: currentUserRatings)
		{
			if(currentUserRating.rating >= 3)
			{
				Movie movie = getMovieById(currentUserRating.movieId);
				List<Rating> topUserRatings = new ArrayList<>(movie.userRatings.values());
				Collections.sort(topUserRatings, ratingComparator);
				for (Rating otherUserRating: topUserRatings)
				{
					if (otherUserRating.rating >= currentUserRating.rating)
					{
						targetUsers.add(getUserById(otherUserRating.userId));
					}
				}
			}
	
		}
		//For each of the narrowed down target users, each with their own list/map of rated movies
		for (User target: targetUsers)
		{
			List<Rating> targetRatedMovies = new ArrayList<>(target.ratedMovies.values());
			Collections.sort(targetRatedMovies, ratingComparator);
			
			for (Rating targetRating: targetRatedMovies)
			{
				//If above 3 and the movie has not been rated before
				if (targetRating.rating >= 3 && !currentUser.ratedMovies.containsKey(targetRating.movieId))
				{
					Movie movie = getMovieById(targetRating.movieId);
					recommendedMoviesSet.add(movie); //Using a set to ensure no duplicates
					
				}
			}
		}
		List<Movie> recommendedMoviesList = new ArrayList<>(recommendedMoviesSet);
		Collections.sort(recommendedMoviesList, movieAvgComparator); //Sort final results with best first
		System.out.println(recommendedMoviesList);
		return recommendedMoviesList;
	}
	
	public List<Rating> getRatings()
	{
		return ratings;
	}
	
	public Map<Long, Movie> getMovies()
	{
		return movies;
	}
	
	public Movie getMovieById(long id)
	{
		return movies.get(id);
	}
	
	public Map<Long, User> getUsers()
	{
		return users;
	}
	
	public User getUserById(long id)
	{
		return users.get(id);
	}

	public Map<Long, Rating> getUserRatings(long userId)
	{
		User user = getUserById(userId);
		return user.ratedMovies;
	}


	
//public User getUserByUsername(String username)
//{
//	return 
//}
}
