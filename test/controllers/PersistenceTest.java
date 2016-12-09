package controllers;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Fixtures;
import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

import static models.Fixtures.*;
public class PersistenceTest
{
	RecommenderAPI recommender;
	Fixtures f = new Fixtures();
	
	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	void populate (RecommenderAPI recommenderAPI)
  {
    for (User user : usersFixtures)
    {
      recommenderAPI.addUser(user.firstName, user.lastName, user.age, user.gender, user.occupation, user.username, user.password);
    }

    for (Movie movie: moviesFixtures)
		{
			recommender.addMovie(movie.title, movie.year, movie.url);
		}
		for (Rating r: ratingsFixtures)
		{
			recommender.addRating(r.userId, r.movieId, r.rating);
		}
		
		f.userRatingsFixtures();
		f.ratedMoviesFixtures();
  }
	
  void deleteFile(String fileName)
  {
    File datastore = new File ("testdatastore.xml");
    if (datastore.exists())
    {
      datastore.delete();
    }
  }
	@Test
	public void testPopulate()
	{
		recommender = new RecommenderAPI();
		assertEquals(0, recommender.getUsers().size());
		populate(recommender);
		
		assertEquals(usersFixtures.length, recommender.getUsers().size());
		assertEquals(usersFixtures[0].ratedMovies.size(), recommender.getUserById(usersFixtures[0].userId).ratedMovies.size());
	}
	
	@Test
  public void testXMLSerializer() throws Exception
  { 
    String datastoreFile = "testdatastore.xml";
    deleteFile(datastoreFile);

    Serializer serializer = new XMLSerializer(new File (datastoreFile));

    recommender = new RecommenderAPI(serializer); 
    populate(recommender);
    recommender.store();

    RecommenderAPI recommender2 =  new RecommenderAPI(serializer);
    recommender2.load();

    assertEquals (recommender.getUsers().size(), recommender2.getUsers().size());
    for (User user : recommender.getUsers().values())
    {
      assertTrue (recommender2.getUsers().containsValue(user));
    }
    deleteFile ("testdatastore.xml");
  }


}
