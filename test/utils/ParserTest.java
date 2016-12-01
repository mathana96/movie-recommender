package utils;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Movie;
import models.Rating;
import models.User;
import static models.Fixtures.*;

public class ParserTest
{

	Parser parser;
	String userDataPath = ("././data/moviedata_small/users5.dat");
	String movieDataPath = ("././data/moviedata_small/items5.dat");
	String ratingDataPath = ("././data/moviedata_small/ratings5.dat");
	
	@Before
	public void setUp()
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
		Map<Long, User> users = parser.parseUserData(userDataPath);
		
		assertEquals(usersFixtures.length, users.size());
		
		User user = usersFixtures[0];
		assertEquals(user.firstName, users.get(user.userId).firstName);
		assertEquals(usersFixtures[1], users.get(2L)); //They content the same stuff
		assertNotSame(usersFixtures[1], users.get(2L)); //But do not point at the same reference in memory
	}
	
	@Test
	public void testParseMovieData() throws Exception
	{
		Map<Long, Movie> movies = parser.parseMovieData(movieDataPath);
		
		assertEquals(moviesFixtures.length, movies.size());
		
		Movie movie = moviesFixtures[0];
		assertEquals(movie.title, movies.get(movie.movieId).title);
		assertEquals(moviesFixtures[1], movies.get(2L));
		assertNotSame(moviesFixtures[1], movies.get(2L));
	}
	
	@Test
	public void testRatingData() throws Exception
	{
		//Have to load Users and Movies because Ratings are added to users and movies
		parser.parseMovieData(movieDataPath);
		parser.parseUserData(userDataPath);
		List<Rating> ratings = parser.parseRatingData(ratingDataPath);
		
		assertEquals(ratingsFixtures.length, ratings.size());
		
		Rating rating = ratingsFixtures[0];
		assertEquals(rating.rating, ratings.get(0).rating);
		
		Movie movie = parser.getMovie(rating.movieId);
		User user = parser.getUser(rating.userId);
		
		int userRating = user.ratedMovies.get(movie.movieId);
		int movieRating = movie.userRatings.get(user.userId);
		
		assertEquals(userRating, movieRating);
	}
}
