package models;

import java.util.HashMap;
import java.util.Map;

public class User
{
	public String firstName, lastName, occupation, username, password;
	public int age;
	public long userId, ratedMoviesId;
	public char gender;
	
	public static long counter = 1l;
	//Map of movie's and their rating. Using movie`id
	public Map<Long, Integer> ratedMovies = new HashMap<>();
	
	public User(Long userId, String firstName, String lastName, int age, char gender, String occupation)
	{
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
	}

	public void addRatedMovies(Long movieId, Integer rating)
	{
		//ratedMoviesId = counter++;
		ratedMovies.put(movieId, rating);
	}
}
