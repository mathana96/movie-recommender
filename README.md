# Movie Matt-Cher #

Movie Matt-Cher is a console-based movie recommender system based on a user's previous movie ratings. Users must create an account before using this service. Raw data from MovieLens was used and XML is used for persistence. Libraries used include Guava, Princeton's Stdlib ans xstream.  

### Run through of the program ###

Upon clicking run, users are prompted with a login menu which has two options (Login and Signup). Users are required to signup before they are provided access to features of this program. Each user signup requires a username and password. The program ensures that each username is unique. After signup is successful, users are displayed with 10 random movies from the database to rate. They are allowed to skip this and rate movies later. Users are then taken back to the login page where they can login.

When the login option is selected, the user's username and password are prompted. If authentication is successful, the main menu is displayed. Otherwise, the login page is displayed. 

The main menu offers the following options;

* Add a new movie –– The user is prompted for Movie(title, release year, IMDb url). Users are not allowed to add movies already found in the database. User is also required to rate every movie they add. 

* Rate a movie –– User is prompted for a movie ID. When a ID found in the movie database is entered, the user may rate the movie within the allowed range of -5 to 5 in increments of 1. 

* Rate random movies –– Users are shown random movies (limited by number of movies in database) which they have not rated and prompts them for a rating. 

* Search movies –– Users are allowed to search the movie database. Results are filtered based on their input/prefix. They may use this feature to otain a movie ID before rating it. 

* Top 10 movies of all time –– A list of the 10 highest rated movies is displayed. They are ranked based on average user ratings. 

* Get personalised movie suggestions –– Displays a list of recommended movies based on how the user rated movies. The displayed movies are ones the user has not rated before. It is calculated based on the similarity of the user against other users who have rated some or all of the movies the user has rated. The movie suggestions are generated based on the rated movies of the user with the highest similarity. 

* Delete account –– Users can delete their account

* Log out –– Log out from the system. Persistence is in play for when the user runs the program again at a later time

### Improvements for the future ###

* More efficient parsing of raw data
* Ability to store large amounts of data (>100k objects)
* The use of a proper database system for persistence 
* Experimenting with JSON as a replacement for XML
* Experimenting with a GUI
* Make the system a web app with a GUI and better recommender