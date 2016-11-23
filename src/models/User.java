package models;

import java.util.HashMap;
import java.util.Map;

public class User
{
	public String firstName, lastName, occupation, username, password;
	public int age;
	public long id;
	public char gender;
	
	//Map of movie's and their rating. Using movie`id
	public Map<Long, Integer> ratedMovies = new HashMap<>();
	
	public User(Long id, String firstName, String lastName, int age, char gender, String occupation)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
	}
	

}
