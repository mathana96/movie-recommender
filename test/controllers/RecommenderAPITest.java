package controllers;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static models.Fixtures.*;

import models.Fixtures;
import models.Movie;
import models.Rating;
import models.User;
import utils.Parser;
import utils.Serializer;
import utils.XMLSerializer;

public class RecommenderAPITest
{
	private RecommenderAPI recommender;
	private Fixtures f; 
	
	@Before
	public void setUp() throws Exception
	{
		recommender = new RecommenderAPI();
		f = new Fixtures();
		for (User user: usersFixtures)
		{
			recommender.addUser(user.firstName, user.lastName, user.age, user.gender, 
													user.occupation, user.username, user.password);
		}
		for (Movie movie: moviesFixtures)
		{
			recommender.addMovie(movie.title, movie.year, movie.url);
		}
		for (Rating r: ratingsFixtures)
		{
			recommender.addRating(r.userId, r.movieId, r.rating);
		}
	}

	@After
	public void tearDown() throws Exception
	{
		recommender = null;
	}

	@Test
	public void testUser() //Add getUserByUsername
	{
		assertEquals(usersFixtures.length, recommender.getUsers().size());
		recommender.addUser("Joe", "Bloggs", 54, 'M', "Network Administrator", "jbloggs", "secret");
		assertEquals(usersFixtures.length + 1, recommender.getUsers().size());
		assertEquals(usersFixtures[0].ratedMovies.size(), recommender.getUserById(usersFixtures[0].userId).ratedMovies.size());			
		long newUserId = usersFixtures.length + 1;
		assertEquals("Joe", recommender.getUserById(newUserId).firstName);
	}
	
	@Test
	public void testUsers()
	{
		for (User user: usersFixtures)
		{
			assertEquals(user, recommender.getUserById(user.userId));
			assertNotSame(user, recommender.getUserById(user.userId));
		}
	}
	
	@Test
	public void testMovie()
	{
		assertEquals(moviesFixtures.length, recommender.getMovies().size());
		recommender.addMovie("The Godfather", 1972, "http://www.imdb.com/title/tt0068646/");
		assertEquals(moviesFixtures.length + 1, recommender.getMovies().size());
		assertEquals(moviesFixtures[0].userRatings.size(), recommender.getMovieById(moviesFixtures[0].movieId).userRatings.size());		
		long newMovieId = moviesFixtures.length + 1;
		assertEquals("The Godfather", recommender.getMovieById(newMovieId).title);
	}
	
	@Test
	public void testMovies()
	{
		for (Movie movie: moviesFixtures)
		{
			assertEquals(movie, recommender.getMovieById(movie.movieId));
			assertNotSame(movie, recommender.getMovieById(movie.movieId));
		}
	}
	
	@Test
	public void testRating()
	{
		assertEquals(ratingsFixtures.length, recommender.getRatings().size());
		recommender.addRating(20L, 40L, 5);
		assertEquals(ratingsFixtures.length + 1, recommender.getRatings().size());
		assertEquals(ratingsFixtures[0].movieId, recommender.getMovieById(ratingsFixtures[0].movieId).movieId);		
	}
	
	@Test
	public void testRatings()
	{
		f.ratedMoviesFixtures();
		for (Rating r: ratingsFixtures)
		{
			User user = usersFixtures[(int) r.userId - 1];
			Integer userRating = user.ratedMovies.get(r.movieId);		
			assertEquals(r.rating, userRating);

		}
	}
}
