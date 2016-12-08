package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		
		f.userRatingsFixtures();
		f.ratedMoviesFixtures();
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
	public void testRemoveUser()
	{
		assertEquals(usersFixtures.length, recommender.getUsers().size());
		User user = recommender.getUserById(4L);
		recommender.removeUser(user.userId);
		assertEquals(usersFixtures.length - 1, recommender.getUsers().size());
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
		User user = usersFixtures[0];
		Movie movie = moviesFixtures[0];
		recommender.addRating(user.userId, movie.movieId, 5);
		assertEquals(ratingsFixtures.length + 1, recommender.getRatings().size());
		assertEquals(ratingsFixtures[0].movieId, recommender.getMovieById(ratingsFixtures[0].movieId).movieId);		
	}

	@Test
	public void testRatings()
	{
		for (Rating r: ratingsFixtures)
		{
			User user = usersFixtures[(int) r.userId - 1];
			Integer userRating = user.ratedMovies.get(r.movieId).rating;		
			assertEquals(r.rating, userRating);

		}
	}

	@Test
	public void testGetUserRatings()
	{
		User user = usersFixtures[0];
		assertEquals(user.ratedMovies.size(), recommender.getUserById(user.userId).ratedMovies.size());
		assertEquals(user.ratedMovies.get(1), recommender.getUserById(user.userId).ratedMovies.get(1));
	}

	@Test
	public void testGetTopTen()
	{
		recommender.addMovie("The Godfather", 1972, "http://www.imdb.com/title/tt0068646/");
		assertEquals(moviesFixtures.length + 1, recommender.getMovies().size()); //Adding another movie to make it 11
		
		List<Rating> topTen = recommender.getTopTenMovies();
		assertEquals(10, topTen.size()); //Assure only 10 movies printed
		
		assertEquals(topTen.get(0).movieId, moviesFixtures[0].movieId);
		assertEquals(topTen.get(0).averageRating, 3.4, 0.001);
		assertEquals(topTen.get(1).movieId, moviesFixtures[7].movieId);
		assertEquals(topTen.get(1).averageRating, 3.0, 0.001);
		assertEquals(topTen.get(2).movieId, moviesFixtures[2].movieId);
		assertEquals(topTen.get(2).averageRating, 1.8, 0.001);
		assertEquals(topTen.get(3).movieId, moviesFixtures[9].movieId);
		assertEquals(topTen.get(3).averageRating, 1.5, 0.001);
		
	}
}
