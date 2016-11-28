package controllers;

import java.io.File;

import utils.Serializer;
import utils.XMLSerializer;

public class Main
{
	
	public Main()
	{
		
	}
	
	public static void main(String[] args) throws Exception
	{
		File datastore = new File("datastore.xml");
		Serializer serializer = new XMLSerializer(datastore);
		RecommenderAPI recommenderAPI = new RecommenderAPI(serializer); 
		
		if (datastore.isFile())
		{
			recommenderAPI.load();
		}
		
		Main main = new Main();
		main.run();
	}
	
	public void run()
	{
		boolean respond = false;
		
		while (!respond)
		{
			
		}
		
	}
	
}
