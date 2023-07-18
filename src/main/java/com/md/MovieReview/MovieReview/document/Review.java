package com.md.MovieReview.MovieReview.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This is the Review document class which maps to the "reviews" collection in the MongoDB.
 * It's an entity model class that Hibernate uses to map the application's object-oriented domain model to MongoDB's document-based data model.
 * The Review class includes properties that map to fields in the collection's documents.
 * Each instance of this class represents a document that can be saved to and retrieved from the database.
 *
 * @author Maulik Davra
 * @version 1.0
 * @since 2023-06-24
 */
@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @JsonProperty("reviewId")
    private ObjectId id;

    @JsonProperty("body")
    private String body;

    /**
     * This is a constructor to instantiate a Review object with a review body.
     *
     * @param body is the text body of the review.
     */
    public Review(String body) {
        this.body = body;
    }
}
