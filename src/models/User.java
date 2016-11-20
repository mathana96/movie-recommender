package models;

public class User
{
	public String firstName, lastName, occupation;
	public int age;
	public long id;
	public char gender;
	public static long counter = 0L;
	
	//HashSet of movieId and rating
	
	public User(String firstName, String lastName, int age, String occupation)
	{
		this.id = ++counter;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.occupation = occupation;
	}

}
