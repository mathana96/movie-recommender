package controllers;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import models.Movie;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main
{
	RecommenderAPI recommenderAPI;
	User loggedInUser;
	boolean authenticated = false;
		Scanner input = new Scanner(System.in);


	public Main() throws Exception
	{
		File datastore = new File("datastore.xml");

		if (datastore.isFile())
		{
			StdOut.println("File present");
			Serializer serializer = new XMLSerializer(datastore);
			recommenderAPI = new RecommenderAPI(serializer); 
			recommenderAPI.load();
		}
		else
		{
			StdOut.println("File not present");
			Serializer newSerializer = new XMLSerializer(new File("datastore.xml"));
			recommenderAPI = new RecommenderAPI(newSerializer); 
			recommenderAPI.loadRawData(); //Load and store raw data into datastore.xml
			recommenderAPI.load();
		}
	}

	public static void main(String[] args) throws Exception
	{
		Main main = new Main();
		main.loginRun();
	}

	public int loginMenu()
	{
		boolean errorFree = false;
		int option = 0;

		while (!errorFree) 
		{
			option = 0;

			try
			{
				StdOut.println("\n======Login Options======\n");

				StdOut.println("Please select the numerical options below\n");
				StdOut.println("1) Login (for users with an account)");
				StdOut.println("2) Signup");
				StdOut.println("\n0) Exit system");


				option = StdIn.readInt();

				errorFree = true;
			}
			catch (Exception e)
			{
				StdIn.readLine();
				StdOut.println("Your selection is incorrect or not available, please try again");	
			}
		}
		return option;
	}


	public int mainMenu()
	{
		boolean errorFree = false;
		int option = 0;
		while (!errorFree) 
		{
			option = 0;

			try
			{
				StdOut.println("\n======Main Menu======\n");

				StdOut.println("Welcome " + loggedInUser.firstName + "!");
				StdOut.println("You have currently rated " + loggedInUser.ratedMovies.size() + " movies\n");
				StdOut.println("1) Add a new movie");
				StdOut.println("2) Rate a movie");
				StdOut.println("3) List of movies");
				StdOut.println("4) Top 10 movies of all time");
				StdOut.println("5) Get personalised movie suggestions");
				StdOut.println("\n99) Delete account");

				StdOut.println("\n0) Log out");


				option = StdIn.readInt();
				errorFree = true;
			}
			catch (Exception e)
			{
				StdIn.readLine();
				StdOut.println("Your selection is incorrect or not available, please try again");	
			}
		}
		return option;

	}

	public void loginRun() throws Exception
	{
		StdOut.println("\nWelcome to Movie-MattCher - a movie recommender just for you!"
				+ "\nPress enter to continue");
		StdIn.readLine();

		int loginOption = loginMenu();

		while (loginOption != 0)
		{
			switch(loginOption)
			{
			case 1:
				authenticate();
				break;

			case 2:
				addUser();
				addRating();
				break;

			default:
				StdOut.println("Your selection is incorrect or not available, please try again");
			}
			if (!authenticated)
			{
				loginOption = loginMenu();
			}
			else
			{
				loginOption = 0;
				menuRun();
				break;
			}
		}
		StdOut.println("Made it here. Get currentUser and store globally");		
	}

	public void menuRun() throws Exception
	{
		int mainMenuOption = mainMenu();

		while (mainMenuOption != 0)
		{
			switch(mainMenuOption)
			{
			case 1:
				addMovie();
				break;

			case 2:
				addUser();
				addRating();
				break;

			case 3:
				authenticate();
				break;

			case 4:
				addUser();
				addRating();
				break;

			case 5:
				authenticate();
				break;

			case 99:
				removeUser();
				break;
			default:
				StdOut.println("Your selection is incorrect or not available, please try again");
			}
			if (loggedInUser != null)
			{
				mainMenuOption = mainMenu();
			}
			else
			{
				mainMenuOption = 0;
				break;
			}
		}
	}
	public void addUser() throws Exception
	{

		StdOut.println("Please key in your details as prompted\n");

		StdOut.println("Your first name? ");
		String firstName = StdIn.readString();

		StdOut.println("Enter your last name? ");
		String lastName = StdIn.readString();

		boolean logicalAge = false;
		int age = 0;
		while (!logicalAge) 
		{
			try 
			{
				StdOut.println("Your age? (It's confidential ;) )");
				age = StdIn.readInt();
				if (age >=0 && age <=99)
				{
					logicalAge = true;
				}
				else
				{
					StdOut.println("You cannot possibly be negative aged!");
				}
			} 
			catch (Exception e) 
			{
				StdIn.readString();
				StdOut.println("Positive numerical inputs only!");
			}
		}

		StdOut.println("Your gender? (M/F/N)");
		char gender = StdIn.readChar();
		StdIn.readString();



		StdOut.println("Your occupation? ");
		String occupation = StdIn.readString();

		boolean unique = false;
		String username = null;
		while (!unique) 
		{
			StdOut.println("Enter a username: ");
			username = StdIn.readString();
			if (!recommenderAPI.usersLogin.containsKey(username))
			{
				unique = true;
			}
			else
			{
				StdOut.println("Username chosen is not unique");
			}
		}

		StdOut.println("Enter a password: ");
		String password = StdIn.readString();

		recommenderAPI.addUser(firstName, lastName, age, gender, occupation, username, password);
		//		recommenderAPI.store();
		StdOut.println("Your details have been logged!");
	}

	public void authenticate()
	{
		String username;
		String password;
		StdOut.println("Authentication process");
		StdOut.println("Enter your username: ");
		username = StdIn.readString();
		StdOut.println("Enter your password: ");
		password = StdIn.readString();

		if (recommenderAPI.authenticate(username, password))
		{
			StdOut.println("Logged in successfully!");
			loggedInUser = recommenderAPI.getUserByUsername(username);
			authenticated = true;
		}
		else
		{
			StdOut.println("Log in failed");
		}
	}

	public void addRating()
	{

	}

	public void removeUser()
	{
		StdOut.println("Are you sure you want to delete your account? (y/n)");
		String toDelete = StdIn.readString();
		if (toDelete.equalsIgnoreCase("y"))
		{
			Optional<User> user = Optional.fromNullable(recommenderAPI.getUserById(loggedInUser.userId));
			if (user.isPresent()) 
			{
				recommenderAPI.removeUser(loggedInUser.userId);
				loggedInUser = null;
				StdOut.println("Account deleted");
			}
		}
	}
	
	public void addMovie() throws Exception
	{
		StdOut.println("Adding a movie");
		
		boolean unique = false;
		String title = "";
		String theTitle = "";
		int year = 0;
		while (!unique) 
		{
			StdOut.println("Enter movie title: ");
			title = input.nextLine();
			
			StdIn.readLine();
			StdOut.println("Enter the year the movie was released: (eg. 2016)");
			year = StdIn.readInt();
			
//			System.out.println(title);
			theTitle = title.concat(" " + "(" + Integer.toString(year) + ")"); //Making user inputs match the standard format of raw data
//			System.out.println(theTitle);
			if (uniqueMovieCheck(theTitle, year) == true)
			{
				unique = true;
			}
			else
			{
				StdOut.println("The movie " + theTitle + " is already in our database");
			}
		}
		
		StdOut.println("Enter IMDb url of the movie if available");
		String url = StdIn.readString();
		if (url.isEmpty())
		{
			url = "No URL";
		}
		
		recommenderAPI.addMovie(theTitle, year, url);
		StdOut.println("Movie added successfully!");
//		recommenderAPI.store();
	}
	
	public boolean uniqueMovieCheck(String title, int year)
	{		
		for (Movie movie: recommenderAPI.movies.values())
		{			
			if (title.toLowerCase().equals(movie.title.toLowerCase()) && (movie.year == year))
			{
				return false;
			}
		}
		return true;
	}

}
