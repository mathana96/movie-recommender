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

				option = input.nextInt();

				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
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
			loginOption = loginMenu();
		}
	}

	public void addUser() throws Exception
	{
		input.nextLine(); //Bug fix

		StdOut.println("Please key in your details as prompted\n");

		StdOut.println("Your first name? ");
		String firstName = input.nextLine();

		StdOut.println("Enter your last name? ");
		String lastName = input.nextLine();

		StdOut.println("Your age? (It's confidential ;) )");
		int age = input.nextInt();
		input.nextLine(); //Consume newline leftover

		StdOut.println("Your gender? (M/F/N)");
		char gender = input.next().charAt(0);
		input.nextLine(); //Consume new line


		StdOut.println("Your occupation? ");
		String occupation = input.nextLine();

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
		recommenderAPI.store();
		StdOut.println("Your details have been logged!");
	}

	public void authenticate()
	{

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
