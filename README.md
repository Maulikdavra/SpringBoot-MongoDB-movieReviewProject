# SpringBoot-MongoDB-movieReviewProject

**`SpringBoot-MongoDB-movieReviewProject`**
This project is a Spring Boot application that uses MongoDB as its database. It's a movie review system where users can add, update, and delete movie reviews.

**`Project Description`**
The aim of this project is to create a RESTful API that provides an interface for users to interact with a movie database. The application allows users to perform various operations related to movies and their reviews.

**`Features`**
The application provides the following APIs:

**`GET`** /api/v1/movies: Fetches all the movies from the database.

**`GET`** /api/v1/movies/{objectId}: Fetches a movie by its objectId.

**`GET`** /api/v1/movies/{imdbId}: Fetches a movie by its ImdbId.

**`POST`** /api/v1/reviews/addReview: Creates a review for a movie based on the ImdbId passed in the JSON body. Example request body:

json body (Example)
Copy code
{
    "reviewBody": "what a amazing movie, I loved it!",
    "imdbId": "tt8760708" 
}

**`POST`** /api/v1/movies/addMovie: Adds a new movie to the database.
json body (Example)
{
    "imdbId": "tt10298840",
    "title": "Strange World",
    "releaseDate": "2022-11-23",
    "trailerLink": "https://www.youtube.com/watch?v=bKh2G73gCCs",
    "genres": [
      "Family",
      "Adventure",
      "Science Fiction",
      "Animation"
    ],
    "poster": "https://image.tmdb.org/t/p/w500/kgJ8bX3zDQDM2Idkleis28XSVnu.jpg",
    "backdrops": [
      "https://image.tmdb.org/t/p/original/5wDBVictj4wUYZ31gR5WzCM9dLD.jpg",
      "https://image.tmdb.org/t/p/original/zNIlXd7CAz0hHAInbs2nsFRc0xQ.jpg",
      "https://image.tmdb.org/t/p/original/1rukJHAP5p6DNHe75Oo1D0m3SnR.jpg",
      "https://image.tmdb.org/t/p/original/aKbe411WyjTZy1OZUVIdNDYVf21.jpg",
      "https://image.tmdb.org/t/p/original/9RKvxz0IryD2ofLYyGpnE7HeWlR.jpg",
      "https://image.tmdb.org/t/p/original/kFURsDklj7QGMMkGJVwDBaJJn05.jpg",
      "https://image.tmdb.org/t/p/original/v6oBDkd7ogXzTQxIU0H5SXq0hOL.jpg",
      "https://image.tmdb.org/t/p/original/fBshLiEJcjdfrU3qQBIINcePSsm.jpg",
      "https://image.tmdb.org/t/p/original/3oie0kID8SNCjkqN6Raweg5dJa.jpg",
      "https://image.tmdb.org/t/p/original/zgFldVKON1Nxp8ui7HVABGKDQKM.jpg"
    ],
    "reviewIds": []
  }

**`PUT`** /api/v1/reviews/updateReview/{objectId}: Updates a review of a movie by passing the objectId of that movie. Example request json body: (Example)
{
    "id": "64975ee5ba5bc62639a14a1e",
    "reviewMessage": "Never seen a movie like this before!"
}

**`DELETE`** /api/v1/movies/{objectId}: Deletes a movie by its objectId.

**`DELETE`** /api/v1/reviews/{objectId}: Deletes a review of a movie by its objectId.

**`Database`**
-> The application uses MongoDB as its database. The necessary credentials to communicate with MongoDB are listed in the .env file in the project root directory. An example of this file, env.example, is included in the project.

**`Data`**
-> The project includes a movies.json file under the data folder (which lies under resources). This file contains data about movies and their reviews.

**`Dependencies`**
-> The project uses Java 17 and Spring Boot 3.1.1. Other dependencies include:

Spring Boot Starter Data MongoDB
Spring Boot Starter Web
Spring Boot DevTools
Lombok
Spring Boot Starter Test
Spring Dotenv 2.5.4
These dependencies can be found in the pom.xml file in the project root directory.

**`How to Run`**
-> You can either use command: mvn spring-boot:run or run the application using the main spring-boot-application file.
-> I tested the API's using POSTMAN, but you can test API's with any tools you may like.
