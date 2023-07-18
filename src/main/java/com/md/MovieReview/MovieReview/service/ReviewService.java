package com.md.MovieReview.MovieReview.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.md.MovieReview.MovieReview.document.Movie;
import com.md.MovieReview.MovieReview.document.Review;
import com.md.MovieReview.MovieReview.repository.MovieRepository;
import com.md.MovieReview.MovieReview.repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * A service class that handles operations related to movie reviews.
 * This class interacts with the database through the ReviewRepository and MongoTemplate objects,
 * providing methods for creating and managing movie reviews.
 *
 * @author Maulik
 * @version 1.0
 * @since 2023-06-24
 */
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;
    private final MovieRepository movieRepository;

    /**
     * Initializes a new instance of the ReviewService class.
     *
     * @param reviewRepository The repository that will be used for managing review data.
     * @param mongoTemplate    The template that will be used for performing MongoDB operations.
     * @param movieRepository
     */
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.mongoTemplate = mongoTemplate;
        this.movieRepository = movieRepository;
    }

    /**
     * Creates a new review and associates it with a movie.
     * This method creates a new Review object with the provided review body, saves it to the database,
     * and then associates the review with the movie that has the specified IMDB ID.
     *
     * @param reviewBody The text of the review.
     * @param imdbId     The IMDB ID of the movie that the review is for.
     * @return The newly created Review object.
     */
    public Review createReview(String reviewBody, String imdbId) {
        Review review = new Review(reviewBody);
        reviewRepository.insert(review);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return review;
    }

    public Optional<Review> deleteReview(ObjectId objectId) {
        Optional<Review> review = reviewRepository.findById(objectId);
        review.ifPresent(value -> reviewRepository.deleteById(objectId));
        return review;
    }

    /**
     * This method updates an existing review with new content provided as a JSON string.
     *
     * <p>It accepts an ObjectId and a JSON string representing the review content.
     * The JSON string is parsed to extract the review message, which is then used to update the existing review.
     * If a review with the specified ObjectId is not found, the method simply returns an empty Optional. If a review is found,
     * it is updated with the new message and saved to the repository, then returned in an Optional.
     *
     * @param id          The ObjectId of the review to be updated.
     * @param reviewJson  The JSON string containing the new review message.
     *                    Expected format: {"reviewMessage":"[new message]"}.
     * @return            An Optional containing the updated Review object if found,
     *                    otherwise an empty Optional.
     * @throws            JsonProcessingException if there is an issue parsing the reviewJson.
     */
    public Optional<Review> updateReview(ObjectId id, String reviewJson) {
        // Parse the JSON to get the review message
        JsonNode reviewNode = null;
        try {
            reviewNode = new ObjectMapper().readTree(reviewJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String reviewMessage = reviewNode.get("reviewMessage").asText();

        Optional<Review> existingReview = reviewRepository.findById(id);
        if (existingReview.isPresent()) {
            Review review = existingReview.get();
            review.setBody(reviewMessage);
            reviewRepository.save(review);
        }
        return existingReview;
    }

}
