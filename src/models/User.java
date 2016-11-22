package models;

public class User
{
	public String firstName, lastName, occupation, username, password;
	public int age;
	public long id;
	public char gender;
	//public static long counter = 0L; ID given based on userIndex size
	
	//HashSet of movieId and rating
	
	public User(String firstName, String lastName, int age, char gender, String occupation)
	{
		//this.id = ++counter;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
	}
	

}
