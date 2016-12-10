package controllers;

import java.io.File;
import java.time.Year;

import java.util.Random;
import java.util.Scanner;

import com.google.common.base.Optional;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import models.Movie;
import models.Rating;
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
				StdOut.println("2) Rate movies");
				StdOut.println("3) Search movies");
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
				addRatings();
				break;

			case 3:
				searchMovies();
				break;

			case 4:
				getTop10();
				break;

			case 5:
				personalRec();
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

	public void searchMovies()
	{
		StdOut.println("Enter your search: ");
		String prefix = StdIn.readString();
		System.out.println(recommenderAPI.searchMovies(prefix));
	}
	public void personalRec()
	{
		if (recommenderAPI.getUserRecommendations(loggedInUser.userId).size() > 0)
		{
			StdOut.println(recommenderAPI.getUserRecommendations(loggedInUser.userId));
		}
		else
		{
			StdOut.println("No recommendations for you at the moment. We suggest you to rate more movies");
		}

	}

	public void getTop10()
	{
		System.out.println(recommenderAPI.getTopTenMovies());
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

		User addedUser = recommenderAPI.addUser(firstName, lastName, age, gender, occupation, username, password);
		//		recommenderAPI.store();
		StdOut.println("Your details have been logged!");
		addLoginRating(addedUser);
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

	public void addLoginRating(User addedUser)
	{
		int max = recommenderAPI.movies.size();
		int rating = 0;
		StdOut.println("\nYou are required to rate 20 movies before using this service. Enter 100 to exit when prompted for rating\n");
		while (addedUser.ratedMovies.size() < 20 && rating != 100)
		{
			Random random = new Random();
			long randomId = random.nextInt(max - 0);

			if (!addedUser.ratedMovies.containsKey(randomId))
			{
				boolean number = false;
				while (!number) 
				{
					try 
					{
						StdOut.println(recommenderAPI.getMovieById(randomId));
						StdOut.println("Rate this movie:  (-5 to 5 in increments of 1) Enter 100 to exit");
						rating = StdIn.readInt();
						if(rating >= -5 && rating <= 5)
						{
							number = true;
						}
						else if (rating == 100)
						{
							number = true;
							break;
						}
					}
					catch (Exception e) 
					{
						StdIn.readString();
						StdOut.println("Numerical values only");					
					}
				}		
				if (rating != 100)
				{
					recommenderAPI.addRating(addedUser.userId, randomId, rating);
				}
			}

		}
	}

	public void addRatings()
	{
		int max = recommenderAPI.movies.size();
		int rating = 0;
		StdOut.println("\nMovie Rating Process. You are shown random movies you haven't rated before. Enter 100 to exit when prompted for rating\n");
		while (loggedInUser.ratedMovies.size() < max && rating != 100)
		{
			Random random = new Random();
			long randomId = random.nextInt(max - 0);

			if (!loggedInUser.ratedMovies.containsKey(randomId))
			{
				boolean number = false;
				while (!number) 
				{
					try 
					{
						StdOut.println(recommenderAPI.getMovieById(randomId));
						StdOut.println("Rate this movie:  (-5 to 5 in increments of 1) Enter 100 to exit");
						rating = StdIn.readInt();
						if(rating >= -5 && rating <= 5)
						{
							number = true;
						}
						else if (rating == 100)
						{
							number = true;
							break;
						}
					}
					catch (Exception e) 
					{
						StdIn.readString();
						StdOut.println("Numerical values only");					
					}
				}		
				if (rating != 100)
				{
					recommenderAPI.addRating(loggedInUser.userId, randomId, rating);
				}
			}

		}
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

			boolean number = false;
			while (!number) 
			{
				try 
				{
					StdOut.println("Enter the year the movie was released: (eg. 2016)");
					year = StdIn.readInt();
					if (year >= 1500 && year <= Year.now().getValue())
					{
						number = true;
					}
					else
					{
						StdOut.println("Movies from the 1500s onwards until the present year only. Time travel not allowed!");
					}
				} 
				catch (Exception e) 
				{
					StdIn.readString();
					StdOut.println("Numerical inputs only");
				}
			}
						
			if (recommenderAPI.uniqueMovieCheck(title, year) == true)
			{
				unique = true;
			}
			else
			{
				StdOut.println("The movie " + title + " is already in our database");
			}
		}
		StdIn.readLine();
		StdOut.println("Enter IMDb url of the movie if available");
		String url = StdIn.readString();
		if (url.isEmpty())
		{
			url = "No URL";
		}

		int rating = 0;
		boolean number = false;
		while (!number) 
		{
			try 
			{
				StdOut.println("Enter rating for this movie: (-5 to 5 at increments of 1)");
				rating = StdIn.readInt();
				if (rating >=-5 && rating <= 5)
				{
					number = true;
				}
				else
				{
					StdOut.println("Numerical values between -5 to 5 only");
				}
			} 
			catch (Exception e) 
			{
				StdIn.readString();
				StdOut.println("Numerical values between -5 to 5 only");
			}
		}
		Movie movie = recommenderAPI.addMovie(theTitle, year, url);
		recommenderAPI.addRating(loggedInUser.userId, movie.movieId, rating);

		StdOut.println("Movie added successfully!");
		//		recommenderAPI.store();
	}

}
