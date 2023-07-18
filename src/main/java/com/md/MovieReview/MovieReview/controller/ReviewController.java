package com.md.MovieReview.MovieReview.controller;

import com.md.MovieReview.MovieReview.document.Review;
import com.md.MovieReview.MovieReview.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

/**
 * The ReviewController class handles the RESTFUL API endpoints related to reviews.
 *
 * @author Maulik
 * @version 1.0
 * @since 2023-06-24
 */
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     Constructs a new ReviewController with the specified ReviewService.
     @param reviewService The review service used to handle review-related operations.
     */
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     Creates a new review based on the provided request payload.
     @param payload The request payload containing the review body and IMDb ID.
     @return The ResponseEntity containing the created review and the corresponding HTTP status.
     */
    @PostMapping("/addReview")
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }

    /**
     Deletes a review by its unique identifier (object ID).
     @param id The unique identifier of the review to be deleted.
     @return The ResponseEntity containing the deleted review (if found) and the corresponding HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Review>> deleteReviewByObjectId(@PathVariable ObjectId id) {
        Optional<Review> deletedReview = reviewService.deleteReview(id);
        return new ResponseEntity<>(deletedReview, HttpStatus.OK);
    }

    /**
     Updates an existing review by its unique identifier (object ID) with the provided review message.
     @param id The unique identifier of the review to be updated.
     @param reviewMessage The updated review message.
     @return The ResponseEntity containing the updated review body (if found) and the corresponding HTTP status.
     */
    @PutMapping("/updateReview/{id}")
    public ResponseEntity<String> updateReviewByObjectId(@PathVariable("id") ObjectId id, @RequestBody String reviewMessage) {
        Optional<Review> existingReview = reviewService.updateReview(id, reviewMessage);
        if (existingReview.isPresent()) {
            String responseBody = existingReview.get().getBody();
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
