/**
 * Fixture data obtained from small data set provided
 */
package models;

public class Fixtures
{
  public static User[] usersFixtures =
  {
    new User (1L, "Leonard", "Hernandez", 24, 'M', "technician", "", ""),
    new User (2L, "Melody", "Roberson", 53, 'F', "other", "", ""),
    new User (3L, "Gregory", "Newton", 23, 'M', "writer", "", ""),
    new User (4L, "Oliver", "George", 24, 'M', "technician", "", ""),
    new User (5L, "Jenna", "Parker", 33, 'F', "other", "", "")
  };

  public static Movie[] moviesFixtures =
  {
  	new Movie(1, "Toy Story (1995)", 1995, "http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)"),
  	new Movie(2, "GoldenEye (1995)", 1995, "http://us.imdb.com/M/title-exact?GoldenEye%20(1995)"),
  	new Movie(3, "Four Rooms (1995)", 1995, "http://us.imdb.com/M/title-exact?Four%20Rooms%20(1995)"),
  	new Movie(4, "Get Shorty (1995)", 1995, "http://us.imdb.com/M/title-exact?Get%20Shorty%20(1995)"),
  	new Movie(5, "Copycat (1995)", 1995, "http://us.imdb.com/M/title-exact?Copycat%20(1995)"),
  	new Movie(6, "Shanghai Triad (Yao a yao yao dao waipo qiao) (1995)", 1995, "http://us.imdb.com/Title?Yao+a+yao+yao+dao+waipo+qiao+(1995)"),
  	new Movie(7, "Twelve Monkeys (1995)", 1995, "http://us.imdb.com/M/title-exact?Twelve%20Monkeys%20(1995)"),
  	new Movie(8, "Babe (1995)", 1995, "http://us.imdb.com/M/title-exact?Babe%20(1995)"),
  	new Movie(9, "Dead Man Walking (1995)", 1995, "http://us.imdb.com/M/title-exact?Dead%20Man%20Walking%20(1995)"),
  	new Movie(10, "Richard III (1995)", 1996, "http://us.imdb.com/M/title-exact?Richard%20III%20(1995)")
  };
 
  public static Rating[]ratingsFixtures =
  {
  	new Rating(1, 7, 1, 874965739), 
  	new Rating(1, 2, 5, 875072442), 
  	new Rating(5, 3, 1, 875635225), 
  	new Rating(5, 5, -3, 875635723), 
  	new Rating(5, 1, 3, 875635748), 
  	new Rating(5, 2, 1, 875636053), 
  	new Rating(5, 7, 3, 875636198), 
  	new Rating(5, 10, -5, 875636493), 
  	new Rating(5, 8, 1, 875720691), 
  	new Rating(5, 4, -5, 875721432), 
  	new Rating(1, 5, 3, 878542441), 
  	new Rating(1, 3, 3, 878542699), 
  	new Rating(5, 9, -5, 878844423), 
  	new Rating(1, 10, 3, 887431883), 
  	new Rating(2, 2, 3, 888550774), 
  	new Rating(2, 7, 3, 888550871), 
  	new Rating(2, 3, 3, 888551922), 
  	new Rating(2, 10, 5, 888552084), 
  	new Rating(1, 9, 1, 888732928), 
  	new Rating(2, 4, -5, 888980085), 
  	new Rating(2, 1, 1, 888980240), 
  	new Rating(3, 7, -5, 889236983), 
  	new Rating(3, 4, -3, 889237224), 
  	new Rating(3, 2, -5, 889237224), 
  	new Rating(3, 5, -5, 889237269), 
  	new Rating(3, 3, -3, 889237269), 
  	new Rating(3, 8, 5, 889237455), 
  	new Rating(3, 1, 3, 889237455), 
  	new Rating(1, 1, 5, 889751711), 
  	new Rating(4, 8, 3, 892001445), 
  	new Rating(4, 3, 5, 892002352), 
  	new Rating(4, 1, 5, 892002353), 
  	new Rating(4, 6, 1, 892003459), 
  	new Rating(4, 7, 3, 892003525), 
  	new Rating(4, 10, 3, 892004275), 
  	new Rating(4, 4, 5, 892004409)
	
  };
  
  
  public void ratedMoviesFixtures()
  {
  	
  	usersFixtures[0].ratedMovies.put(1L, new Rating(1L, 1L, 5));
  	usersFixtures[0].ratedMovies.put(2L, new Rating(1L, 2L, 5));
  	usersFixtures[0].ratedMovies.put(3L, new Rating(1L, 3L, 3));
  	usersFixtures[0].ratedMovies.put(5L, new Rating(1L, 5L, 3));
  	usersFixtures[0].ratedMovies.put(7L, new Rating(1L, 7L, 1));
  	usersFixtures[0].ratedMovies.put(9L, new Rating(1L, 9L, 1));
  	usersFixtures[0].ratedMovies.put(10L, new Rating(1L, 10L, 3));
  	
  	usersFixtures[1].ratedMovies.put(1L, new Rating(2L, 1L, 1));
  	usersFixtures[1].ratedMovies.put(2L, new Rating(2L, 2L, 3));
  	usersFixtures[1].ratedMovies.put(3L, new Rating(2L, 3L, 3));
  	usersFixtures[1].ratedMovies.put(4L, new Rating(2L, 4L, -5));
  	usersFixtures[1].ratedMovies.put(7L, new Rating(2L, 7L, 3));
  	usersFixtures[1].ratedMovies.put(10L, new Rating(2L, 10L, 5));
  	
  	usersFixtures[2].ratedMovies.put(1L, new Rating(3L, 1L, 3));
  	usersFixtures[2].ratedMovies.put(2L, new Rating(3L, 2L, -5));
  	usersFixtures[2].ratedMovies.put(3L, new Rating(3L, 3L, -3));
  	usersFixtures[2].ratedMovies.put(4L, new Rating(3L, 4L, -3));
  	usersFixtures[2].ratedMovies.put(5L, new Rating(3L, 5L, -5));
  	usersFixtures[2].ratedMovies.put(7L, new Rating(3L, 7L, -5));
  	usersFixtures[2].ratedMovies.put(8L, new Rating(3L, 8L, 5));
  	
  	usersFixtures[3].ratedMovies.put(1L, new Rating(4L, 1L, 5));
  	usersFixtures[3].ratedMovies.put(3L, new Rating(4L, 3L, 5));
  	usersFixtures[3].ratedMovies.put(4L, new Rating(4L, 4L, 5));
  	usersFixtures[3].ratedMovies.put(6L, new Rating(4L, 6L, 1));
  	usersFixtures[3].ratedMovies.put(7L, new Rating(4L, 7L, 3));
  	usersFixtures[3].ratedMovies.put(8L, new Rating(4L, 8L, 3));
  	usersFixtures[3].ratedMovies.put(10L, new Rating(4L, 10L, 3));
  	
  	usersFixtures[4].ratedMovies.put(1L, new Rating(5L, 1L, 3));
  	usersFixtures[4].ratedMovies.put(2L, new Rating(5L, 2L, 1));
  	usersFixtures[4].ratedMovies.put(3L, new Rating(5L, 3L, 1));
  	usersFixtures[4].ratedMovies.put(4L, new Rating(5L, 4L, -5));
  	usersFixtures[4].ratedMovies.put(5L, new Rating(5L, 5L, -3));
  	usersFixtures[4].ratedMovies.put(7L, new Rating(5L, 7L, 3));
  	usersFixtures[4].ratedMovies.put(8L, new Rating(5L, 8L, 1));
  	usersFixtures[4].ratedMovies.put(9L, new Rating(5L, 9L, -5));
  	usersFixtures[4].ratedMovies.put(10L, new Rating(5L, 10L, -5)); 	
  }

  public void userRatingsFixtures()
  {
  	moviesFixtures[0].userRatings.put(1L, new Rating(1, moviesFixtures[0].movieId, 5));
  	moviesFixtures[0].userRatings.put(2L, new Rating(2, moviesFixtures[0].movieId, 1));
  	moviesFixtures[0].userRatings.put(3L, new Rating(3, moviesFixtures[0].movieId, 3));
  	moviesFixtures[0].userRatings.put(4L, new Rating(4, moviesFixtures[0].movieId, 5));
  	moviesFixtures[0].userRatings.put(5L, new Rating(5, moviesFixtures[0].movieId, 3));
  	
  	moviesFixtures[1].userRatings.put(1L, new Rating(1, moviesFixtures[1].movieId, 5));
  	moviesFixtures[1].userRatings.put(2L, new Rating(2, moviesFixtures[1].movieId, 3));
  	moviesFixtures[1].userRatings.put(3L, new Rating(3, moviesFixtures[1].movieId, -5));
  	moviesFixtures[1].userRatings.put(5L, new Rating(5, moviesFixtures[1].movieId, 1));
  	
  	moviesFixtures[2].userRatings.put(1L, new Rating(1, moviesFixtures[2].movieId, 3));
  	moviesFixtures[2].userRatings.put(2L, new Rating(2, moviesFixtures[2].movieId, 3));
  	moviesFixtures[2].userRatings.put(3L, new Rating(3, moviesFixtures[2].movieId, -3));
  	moviesFixtures[2].userRatings.put(4L, new Rating(4, moviesFixtures[2].movieId, 5));
  	moviesFixtures[2].userRatings.put(5L, new Rating(5, moviesFixtures[2].movieId, 1));
  	
  	moviesFixtures[3].userRatings.put(2L, new Rating(2, moviesFixtures[3].movieId, -5));
  	moviesFixtures[3].userRatings.put(3L, new Rating(3, moviesFixtures[3].movieId, -3));
  	moviesFixtures[3].userRatings.put(4L, new Rating(4, moviesFixtures[3].movieId, 5));
  	moviesFixtures[3].userRatings.put(5L, new Rating(5, moviesFixtures[3].movieId, -5));
  	
  	moviesFixtures[4].userRatings.put(1L, new Rating(1, moviesFixtures[4].movieId, 3));
  	moviesFixtures[4].userRatings.put(3L, new Rating(3, moviesFixtures[4].movieId, -5));
  	moviesFixtures[4].userRatings.put(5L, new Rating(5, moviesFixtures[4].movieId, -3));
  	
  	moviesFixtures[5].userRatings.put(4L, new Rating(4, moviesFixtures[5].movieId, 1));
  	
  	moviesFixtures[6].userRatings.put(1L, new Rating(1, moviesFixtures[6].movieId, 1));
  	moviesFixtures[6].userRatings.put(2L, new Rating(2, moviesFixtures[6].movieId, 3));
  	moviesFixtures[6].userRatings.put(3L, new Rating(3, moviesFixtures[6].movieId, -5));
  	moviesFixtures[6].userRatings.put(4L, new Rating(4, moviesFixtures[6].movieId, 3));
  	moviesFixtures[6].userRatings.put(5L, new Rating(5, moviesFixtures[6].movieId, 3));
  	
  	moviesFixtures[7].userRatings.put(3L, new Rating(3, moviesFixtures[7].movieId, 5));
  	moviesFixtures[7].userRatings.put(4L, new Rating(4, moviesFixtures[7].movieId, 3));
  	moviesFixtures[7].userRatings.put(5L, new Rating(5, moviesFixtures[7].movieId, 1));
 
  	moviesFixtures[8].userRatings.put(1L, new Rating(1, moviesFixtures[8].movieId, 1));
  	moviesFixtures[8].userRatings.put(5L, new Rating(5, moviesFixtures[8].movieId, -5));

  	moviesFixtures[9].userRatings.put(1L, new Rating(1, moviesFixtures[9].movieId, 3));
  	moviesFixtures[9].userRatings.put(2L, new Rating(2, moviesFixtures[9].movieId, 5));
  	moviesFixtures[9].userRatings.put(4L, new Rating(4, moviesFixtures[9].movieId, 3));
  	moviesFixtures[9].userRatings.put(5L, new Rating(5, moviesFixtures[9].movieId, -5));
  }
  
}