package utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Movie;
import models.User;

public class ParserTest
{

	Parser parser;
	
	@Before
	public void setUp() throws Exception 
	{
		parser = new Parser();
	}

	@After
	public void tearDown() throws Exception
	{
		parser = null;
	}

	@Test
	public void testParseUserData() throws Exception
	{
		assertEquals(5, parser.getUsers().size());
		
		User user =  parser.getUser(1);
		assertEquals("Leonard", user.firstName);
		assertEquals("Hernandez", user.lastName);
		assertEquals(24, user.age);
		assertEquals('M', user.gender);
		assertEquals("technician", user.occupation);	
	}
	
	@Test
	public void testParseMovieData() throws Exception
	{
		assertEquals(10, parser.getMovies().size());
		
		Movie movie =  parser.getMovie(1);
		assertEquals("Toy Story (1995)", movie.title);
		assertEquals(1995, movie.year);
		assertEquals("http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)", movie.url);	
	}
	
	@Test
	public void testRatingData()
	{
		//7
		User user = parser.getUser(1);
		//Map overwrites later values if same key found. Sort ratings by time stamp
		assertEquals(7, user.ratedMovies.size());
	
		Movie movie = parser.getMovie(7);
		assertEquals(movie.movieId, 7);
		assertEquals(movie.title, "Twelve Monkeys (1995)");
		assertEquals(movie.year, 1995);
		assertEquals(movie.url, "http://us.imdb.com/M/title-exact?Twelve%20Monkeys%20(1995)");
		
		//System.out.println(user.ratedMovies.toString());
		int userRating = user.ratedMovies.get(movie.movieId);
		int movieRating = movie.userRatings.get(user.userId);
		assertEquals(userRating, movieRating);
	}
}
