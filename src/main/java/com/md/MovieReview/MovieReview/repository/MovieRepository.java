package com.md.MovieReview.MovieReview.repository;

import com.md.MovieReview.MovieReview.document.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface extends Spring Data's MongoRepository to manage Movie entities.
 * This provides a set of methods to interact with the database for CRUD operations.
 * Spring Data MongoDB uses the MongoTemplate to execute the queries behind your find, save, delete, etc. operations
 * Additional custom methods for retrieving Movie entities can be added here.
 * In this case, a method to find a movie by its imdbId has been added.
 *
 * @author Maulik Davra
 * @version 1.0
 * @since 2023-06-24
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findMovieByImdbId(String imdbId);
}
