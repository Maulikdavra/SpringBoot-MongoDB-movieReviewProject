package com.md.MovieReview.MovieReview.service;

import com.md.MovieReview.MovieReview.document.Movie;
import com.md.MovieReview.MovieReview.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * This service class provides operations to interact with Movie objects stored in the database.
 * It leverages the MovieRepository to access the MongoDB via Spring Data MongoDB's MongoTemplate.
 *
 * @author Maulik Davra
 * @version 1.0
 * @since 2023-06-24
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MovieService(MovieRepository movieRepository, MongoTemplate mongoTemplate) {
        this.movieRepository = movieRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * This method retrieves all movies from the database.
     *
     * @return a list of all movies.
     */
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    /**
     * This method retrieves a movie from the database by its ObjectId.
     * Note: ObjectId is the default id used by Hibernate JPA for database records.
     *
     * @param objectId is the ObjectId of the movie to be fetched.
     * @return an Optional containing the movie, if found.
     */
    public Optional<Movie> singleMovie(ObjectId objectId){
        return movieRepository.findById(objectId);
    }

    /**
     * This method retrieves a movie from the database by its imdbId.
     * Note: imdbId is a unique identifier in the database, not the default Hibernate id.
     *
     * @param imdbId is the imdbId of the movie to be fetched.
     * @return an Optional containing the movie, if found.
     */
    public Optional<Movie> singleMovieByImdbId(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId);
    }

    /**
     * This method deletes a movie from the database by its ObjectId.
     * Note: ObjectId is the default id used by Hibernate JPA for database records.
     *
     * @param objectId is the ObjectId of the movie to be deleted.
     * @return an Optional containing the deleted movie, if found.
     */
    public Optional<Movie> deleteMovie(ObjectId objectId){
        Optional<Movie> review = movieRepository.findById(objectId);
        review.ifPresent(value -> movieRepository.deleteById(objectId));
        return review;
    }

    /**
     * This method adds a new movie to the database.
     *
     * @param movie is the Movie object to be added.
     * @return the newly added movie.
     */
    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }
}
