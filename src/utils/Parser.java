package utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.introcs.In;
import models.Movie;
import models.User;

public class Parser
{
	Map<Long, User> users = new HashMap<>();
	Map<Long, Movie> movies = new HashMap<>();
	
	public Parser() throws Exception
	{
		parseUserData("././data/moviedata_small/users5.dat");
		parseMovieData("././data/moviedata_small/items5.dat");
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
		  	Long userId = Long.parseLong(userTokens[0]);
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
		  	throw new Exception("Invalid member length: "+userTokens.length);
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
		  	Long movieId = Long.parseLong(movieTokens[0]);
		  	String title = movieTokens[1];
		  	int year = Integer.parseInt(movieTokens[2].substring(movieTokens[2].length()-5, movieTokens[2].length()-2));
		  	String url = movieTokens[3];
		  	
        Movie movie = new Movie(title, year, url);
        movies.put(movieId, movie);

		  }
		  else
		  {
		  	throw new Exception("Invalid member length: "+movieTokens.length);
		  }
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

}
