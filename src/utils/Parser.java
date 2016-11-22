package utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.introcs.In;
import models.User;

public class Parser
{
	Map<Long, User> users = new HashMap<>();
	
	public Parser() throws Exception
	{
		parseUserData("././data/moviedata_small/users5.dat");
	}

	public void parseUserData(String path) throws Exception
	{
		File usersFile = new File(path);
		In inUsers = new In(usersFile);
		String delims = "[|]";
		
		while (!inUsers.isEmpty())
		{
			String userDetails = inUsers.readLine();
			String[] userTokens = userDetails.split(delims);
			
		  if (userTokens.length == 7) 
		  {
		  	long userId = Long.parseLong(userTokens[0]);
		  	String firstName = userTokens[1];
		  	String lastName = userTokens[2];
		  	int age = Integer.parseInt(userTokens[3]);
		  	char gender = userTokens[4].charAt(0);
		  	String occupation = userTokens[5];
		  	
        User user = new User(firstName, lastName, age, gender, occupation);
        users.put(userId, user);

		  }
		  else
		  {
		  	throw new Exception("Invalid member length: "+userTokens.length);
		  }
		}
		
	}
	
	public Map<Long, User> getUsers()
	{
		return users;
	}

}
