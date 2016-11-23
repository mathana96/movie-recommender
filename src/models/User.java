package models;

public class User
{
	public String firstName, lastName, occupation, username, password;
	public int age;
	public long userId;
	public char gender;
	//public static long counter = 0L; ID given based on userIndex size
	
	//HashSet of movieId and rating
	
	public User(Long id, String firstName, String lastName, int age, char gender, String occupation)
	{
		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
	}
	

}
