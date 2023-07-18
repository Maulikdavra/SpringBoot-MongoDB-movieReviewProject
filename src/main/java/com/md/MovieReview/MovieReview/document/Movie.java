package com.md.MovieReview.MovieReview.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

/**
 * Below class "Movie" present each document in the movie collections.
 *
 * @author Maulik Davra
 * @version 1.0
 * @since 2023-06-24
 */
@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;

    /**
     * Below "DocumentReference" annotation references another document "Review," as it's making a relationship (one to many)
     *
     * @DocumentReference by default, uses the referenced entity's id value for storage and retrieval.
     *
     * <p>More information can be found below</p>
     *
     * <p>
     * The @DocumentReference annotation in Spring Data MongoDB is used to reference entities in MongoDB using a flexible schema.
     * The goal is the same as when using DBRef, but the stored representation is different.
     * The reference can be anything that can be stored in MongoDB: a single value, an entire Document, etc.
     * By default, the mapping layer will use the referenced entity's id value for storage and retrieval.
     *
     * <p>
     * The lookup() method allows defining a query filter that is independent of the _id field and, in combination with writing converters,
     * offers a flexible way of defining references between entities.
     * </p>
     *
     * <p>
     * The @DocumentReference annotation provides the following optional elements:
     * collection: Specifies the collection where the referenced entity resides. Defaults to the collection of the referenced entity type.
     * db: Specifies the database where the referenced entity resides. It uses the default database provided by MongoDatabaseFactory if left empty.
     * lazy: Controls whether the referenced entity should be loaded lazily (default is false).
     * lookup: Defines the single document lookup query. In case of a Collection or Map property, the individual lookups are combined via an $or operator. target points to the source value (or document) stored at the reference property. Properties of target can be used to define the reference query. It returns an _id based lookup by default​1​.
     * sort: Specifies a specific sort order.
     * </p>
     */
    @DocumentReference
    private List<Review> reviewIds;
}
