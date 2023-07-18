package com.md.MovieReview.MovieReview.controller;

import com.md.MovieReview.MovieReview.document.Movie;
import com.md.MovieReview.MovieReview.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This is a controller class for managing Movie objects.
 * It provides RESTful API endpoints for retrieving, adding and deleting movies.
 * Each method maps to a different endpoint and HTTP verb, performing the associated database operation via the MovieService.
 *
 * @author Maulik Davra
 * @version 1.0
 * @since 2023-06-24
 */
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Below is the method used for fetching all the movies from the database.
     * Leveraging the getAll() methods from Hibernate JPA repository.
     *
     * @return a 200k response upon successfully fetching movie.
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
    }

    /*
      Below method is an implementation of getting movie by ObjectID.
      Note that ObjectId here refers as default id by hibernate JPA to search for any object/record in a database.

      @param id is the ObjectId coming from a Movie Document, stored in a database.
     * @return a 200k response upon successfully fetching movie.

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getMovie(@PathVariable ObjectId id){
        return new ResponseEntity<>(movieService.singleMovie(id), HttpStatus.OK);
    }
     */

    /**
     * Below method is a manual implementation of getting movie by imdbId.
     * Note that imdbId here does not refer to default id which hibernates uses to search for any object/record in a database.
     * Therefore, we have defined a specific method in Movie repository which fetches data with imdbId.
     *
     * @param imdbId is one of the unique identifiers in a database linked to specific books
     * @return a 200k response upon successfully fetching movie.
     */
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getMovieByImdbId(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.singleMovieByImdbId(imdbId), HttpStatus.OK);
    }

    /**
     * This method deletes a movie from the database by its ObjectId.
     * Note: ObjectId is the default id used by Hibernate JPA for database records.
     *
     * @param id is the ObjectId of the movie to be deleted.
     * @return a ResponseEntity with the HTTP status code and the deleted movie, if found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Movie>> deleteMovieByImdbId(@PathVariable ObjectId id){
        Optional<Movie> deletedReview = movieService.deleteMovie(id);
        return new ResponseEntity<>(deletedReview, HttpStatus.OK);
    }

    /**
     * This method adds a new movie to the database.
     *
     * @param movie is the Movie object to be added.
     * @return a ResponseEntity with the HTTP status code and the newly added movie.
     */
    @PostMapping("/addMovie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        Movie film = movieService.addMovie(movie);
        return new ResponseEntity<>(film, HttpStatus.CREATED);
    }
}
