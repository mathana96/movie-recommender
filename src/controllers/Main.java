/**
 * @author mathana
 */
package controllers;

import java.io.File;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.google.common.base.Optional;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

/**
 * The main class provides a console based user interfaced structured over a simple menu system without 
 * the use of a library. The user interface allows users to interact with the program allowing them 
 * to make full use of the features such as adding a new movie, rating a movie from the large database, discovering
 * new movies with the rate a random movie feature and more. A user has to sign up before being able to use the features. 
 *
 */
public class Main
{
	RecommenderAPI recommenderAPI;
	User loggedInUser;								//Stores the current logged in user
	boolean authenticated = false;		//Checks if a user has been authenticated 
	Scanner input = new Scanner(System.in);


	public Main() throws Exception
	{
		File datastore = new File("datastore.xml"); //The main datastore

		if (datastore.isFile()) //If the file exists
		{
			StdOut.println("File present");
			Serializer serializer = new XMLSerializer(datastore);
			recommenderAPI = new RecommenderAPI(serializer); 
			recommenderAPI.load();
		}
		else
		{
			StdOut.println("File not present");
			Serializer newSerializer = new XMLSerializer(new File("datastore.xml")); //Creating a new xml file
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

	/**
	 * The first menu which the user sees. Provides options to login or signup
	 * @return
	 */
	public int loginMenu()
	{
		boolean errorFree = false; //Ensures options entered are correct
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

	/**
	 * The main menu and welcome page of the system
	 */
	public int mainMenu()
	{
		boolean errorFree = false;
		int option = 0; //Users menu selection
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
				StdOut.println("3) Rate random movies");
				StdOut.println("4) Search movies");
				StdOut.println("5) Top 10 movies of all time");
				StdOut.println("6) Get personalised movie suggestions");
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

	/**
	 * Run method for the login menu
	 * @throws Exception
	 */
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
				authenticate(); //Calls method for login
				break;

			case 2:
				addUser(); //Calls method for signup
				break;

			default:
				StdOut.println("Your selection is incorrect or not available, please try again");
			}
			if (!authenticated) //Display menu again if authentication failed
			{
				loginOption = loginMenu();
			}
			else //Display welcome page and main menu if the authetication is successful
			{
				loginOption = 0;
				menuRun();
				break;
			}
		}
		StdOut.println("Made it here. Thanks for stopping by");		//The last message when you log out
	}

	/**
	 * Run method for the welcome page and main menu
	 * @throws Exception
	 */
	public void menuRun() throws Exception
	{
		int mainMenuOption = mainMenu();

		while (mainMenuOption != 0)
		{
			switch(mainMenuOption)
			{
			case 1:
				addMovie(); //Method call for adding a new movie
				break;

			case 2:
				addARating(); //Method call for adding a new rating
				break;

			case 3:
				addRandomRatings(); //Method call to rate random movies
				break;

			case 4:
				searchMovies(); //Method call to search movies
				break;

			case 5:
				getTop10(); //Method call to get all time top 10
				break;

			case 6:
				personalRec(); //Method call to get personalised movie recommendations
				break;

			case 99:
				removeUser(); //Method call to delete account
				break;
			default:
				StdOut.println("Your selection is incorrect or not available, please try again");
			}
			if (loggedInUser != null) //Display menu again if account still exists or not logged out
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

	/**
	 * Method to search all movies for a prefix provided by the user
	 */
	public void searchMovies()
	{
		StdOut.println("Enter your search: ");
		String prefix = StdIn.readString();
		System.out.println(recommenderAPI.searchMovies(prefix));
	}

	/**
	 * Method to get personal movie recommendtions
	 */
	public void personalRec()
	{
		if (loggedInUser.ratedMovies.size() > 0)
		{
			StdOut.println(recommenderAPI.getUserRecommendations(loggedInUser.userId));
		}
		else
		{
			StdOut.println("No recommendations for you at the moment. We suggest you to rate more movies");
		}

	}

	/**
	 * Gets the top 10 movies of all time in the system
	 */
	public void getTop10()
	{
		System.out.println(recommenderAPI.getTopTenMovies());
	}

	/**
	 * Method to add a new user to the system
	 * @throws Exception
	 */
	public void addUser() throws Exception
	{

		StdOut.println("Please key in your details as prompted\n");

		StdOut.println("Your first name? ");
		String firstName = StdIn.readString();

		StdOut.println("Enter your last name? ");
		String lastName = StdIn.readString();

		//Loop if non-numerical or within range
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

		//Loops to check that the username is unique
		boolean unique = false;
		String username = null;
		while (!unique) 
		{
			StdOut.println("Enter a username: ");
			username = StdIn.readString();
			if (!recommenderAPI.usersLogin.containsKey(username)) //If username is unique, break from loop
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
		recommenderAPI.store();
		StdOut.println("Your details have been logged!");
		addLoginRating(addedUser); //Calling the login movie rating system
	}

	/**
	 * Authenticator method to check user's username and password
	 */
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

	/**
	 * Method to add a rating of a specific movie indicated by the movie id
	 */
	public void addARating()
	{
		long mvId = 0L; //The movie id
		int rating = 0;

		//Loop to ensure the id is a number and the movie id exists in the system
		boolean number = false;
		while (!number) 
		{
			try 
			{
				StdOut.println("Enter movie Id of movie you want to add rating: ");
				mvId = StdIn.readInt();
				//				StdIn.readString();

				if(recommenderAPI.movies.containsKey(mvId))
				{
					StdOut.println(recommenderAPI.getMovieById(mvId));
					number = true;
				}
				else 
				{
					StdOut.println("Movie id does not exist");
				}
			}
			catch (Exception e) 
			{
				StdIn.readString();
				StdOut.println("Numerical values only");					
			}
		}

		//Loop to ensure the rating is within the range and is numerical
		boolean number2 = false;
		while (!number2) 
		{
			try 
			{
				StdOut.println("Rate this movie:  (-5 to 5 in increments of 1)");
				rating = StdIn.readInt();
				if(rating >= -5 && rating <= 5)
				{
					number2 = true;
				}
				else 
				{
					StdOut.println("Numerical values only");
				}
			}
			catch (Exception e) 
			{
				StdIn.readString();
				StdOut.println("Numerical values only");					
			}
		}
		StdOut.println("Successfully rated!");
		recommenderAPI.addRating(loggedInUser.userId, mvId, rating);
	}

	/**
	 * Method which generates 10 random movies after a user signs up to be rated
	 * @param addedUser
	 * @throws Exception
	 */
	public void addLoginRating(User addedUser) throws Exception
	{
		int max = recommenderAPI.movies.size();
		int rating = 0;
		StdOut.println("\nYou are required to rate at least 10 movies before using this service. Enter 100 to exit when prompted for rating\n");

		//Loop for 10 ratings and as long as user does not want to exit
		while (addedUser.ratedMovies.size() < 10 && rating != 100)
		{
			Random random = new Random();
			long randomId = random.nextInt(max - 0);

			if (!addedUser.ratedMovies.containsKey(randomId)) //Ensures only movies that user hasn't rated shows up 
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
						else if (rating == 100) //Exit login rating
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
				if (rating != 100) //Add the rating if it's within range
				{
					recommenderAPI.addRating(addedUser.userId, randomId, rating);
				}
			}

		}
		recommenderAPI.store();
	}

	/**
	 * Add ratings to randomly generated movies. Movies shown have not been rated by the user previously 
	 * @throws Exception
	 */
	public void addRandomRatings() throws Exception
	{
		int max = recommenderAPI.movies.size(); //Maximum generated ratings
		int rating = 0;
		StdOut.println("\nMovie Rating Process. You are shown random movies you haven't rated before. Enter 100 to exit when prompted for rating\n");

		//Loop to ensure user still has unrated movies
		while (loggedInUser.ratedMovies.size() < max && rating != 100)
		{
			Random random = new Random();
			long randomId = random.nextInt(max - 0);

			if (!loggedInUser.ratedMovies.containsKey(randomId)) //Display random movies the user has not rated
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
						else if (rating == 100) //Exit system
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
		recommenderAPI.store();
	}

	/**
	 * Method to delete the user account. A
	 * @throws Exception
	 */
	public void removeUser() throws Exception
	{
		StdOut.println("Are you sure you want to delete your account? (y/n)");
		String toDelete = StdIn.readString();

		if (toDelete.equalsIgnoreCase("y"))
		{
			Optional<User> user = Optional.fromNullable(recommenderAPI.getUserById(loggedInUser.userId));
			if (user.isPresent()) 
			{
				Set<Long> ratedMoviesId = loggedInUser.ratedMovies.keySet();
				for (Long movieId: ratedMoviesId) //Delete all the user's rated movies
				{
					Movie movie = recommenderAPI.getMovieById(movieId);
					movie.userRatings.remove(loggedInUser.userId);
				}
				recommenderAPI.removeUser(loggedInUser.userId); //Remove the user entirely

				loggedInUser = null; //Logs the user out of the system
				StdOut.println("Account deleted");
			}
		}
		recommenderAPI.store();
	}

	/**
	 * Method to add a new movie in the system and provide it with a rating upon adding. Movie has to be unique
	 * and not found in the database
	 * @throws Exception
	 */
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

			//Checks if the movie and year are present in the system
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
		recommenderAPI.store();
	}

}
