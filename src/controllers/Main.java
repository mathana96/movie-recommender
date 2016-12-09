package controllers;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main
{
	RecommenderAPI recommenderAPI;
	boolean authenticated = false;
	//	Scanner input = new Scanner(System.in);


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
			recommenderAPI.loadRawData();
			recommenderAPI.load();
		}
	}

	public static void main(String[] args) throws Exception
	{
		Main main = new Main();
		main.run();
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


	public void run() throws Exception
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
				break;
			}
		}
		StdOut.println("Made it here. Get currentUser and store globally");
		
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
	//	@Command(description="Delete a User")
	//	public void deleteUser (@Param(name="email") String email)
	//	{
	//		Optional<User> user = Optional.fromNullable(paceApi.getUserByEmail(email));
	//		if (user.isPresent())
	//		{
	//			paceApi.deleteUser(user.get().id);
	//		}
	//	}


}
