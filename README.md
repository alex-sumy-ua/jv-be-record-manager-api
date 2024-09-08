# jv-be-record-manager-api

# Northcoders Record Shop API Mini Project
 
## Tasks

Designing and building the backend for the record shop inventory system which will allow the Northcoders Record Shop to:
- Store information about the records they have in stock.
- Query this data in various ways.
- Update it accordingly.

## User Stories

For the Northcoders Record Shop inventory system, I want the backend API endpoints to be able to return and interact with the data in the following way:
- list all albums in stock
- get album by id
- list all albums by a given artist
- list all albums by a given release year
- list all albums by a given genre
- get album information by album name
- add new albums into the database
- update album details
- delete albums from the database

## Used technologies (briefly)
- H2 in-memory DB
- PostgreSQL
- Spring Data JPA (Java Persistence API)
- Spring Web
- Spring Actuator

### The data model has been designed online at https://dbdiagram.io/

https://dbdiagram.io/d/RecordShop-66d9ac7beef7e08f0ecc6885

![Data-Model](https://github.com/user-attachments/assets/239549d8-5589-4db2-a5bb-37e661e24a03)




## List of endpoints with instructions on how to use



### Artist Endpoints


#### 1. Get All Artists

Endpoint: /api/v1/albums/artists
Usage: GET http://localhost:8082/api/v1/albums/artists



#### 2. Get Artist by ID

Endpoint: /api/v1/albums/artist/{id}

Usage: GET http://localhost:8082/api/v1/albums/artist/2



#### 3. Add Artist

Endpoint: /api/v1/albums/artist

Usage: POST http://localhost:8082/api/v1/albums/artist

Request Body:

{
  "name": "Artist Name",
  "role": "Artist Role"
}



#### 4. Update Artist by ID

Endpoint: /api/v1/albums/artists/{id}

Usage: PUT http://localhost:8082/api/v1/albums/artists/2

Request Body:

{
  "name": "Updated Artist Name",
  "role": "Updated Artist Role"
}



#### 5. Delete Artist by ID

Endpoint: /api/v1/albums/artists/{id}

Usage: DELETE http://localhost:8082/api/v1/albums/artists/2




### Album Endpoints



#### 6. Get All Albums

Endpoint: /api/v1/albums

Usage: GET http://localhost:8082/api/v1/albums



#### 7. Get Album by ID

Endpoint: /api/v1/album/{id}

Usage: GET http://localhost:8082/api/v1/album/2



#### 8. Add Album

Endpoint: /api/v1/album

Usage: POST http://localhost:8082/api/v1/album

Request Body:

{
  "title": "Album Title",
  "description": "Album Description",
  "released": 2024,
  "artist_id": 1,
  "genre": "ROCK",
  "price": 19.99,
  "in_stock": 100
}



#### 9. Update Album by ID

Endpoint: /api/v1/albums/{id}

Usage: PUT http://localhost:8082/api/v1/albums/2

Request Body:

{
  "title": "Updated Album Title",
  "description": "Updated Album Description",
  "released": 2024,
  "artist_id": 1,
  "genre": "ROCK",
  "price": 19.99,
  "in_stock": 100
}



#### 10. Delete Album by ID

Endpoint: /api/v1/albums/{id}

Usage: DELETE http://localhost:8082/api/v1/albums/2


Additional Endpoints



#### 11. Get Albums by Artist

Endpoint: /api/v1/albums/artist

Usage: GET http://localhost:8082/api/v1/albums/artist?artistId=2

Query Parameter: artistId



#### 12. Get Albums by Year

Endpoint: /api/v1/albums/year

Usage: GET http://localhost:8082/api/v1/albums/year?released=1982

Query Parameter: released



#### 13. Get Albums by Genre

Endpoint: /api/v1/albums/genre

Usage: GET http://localhost:8082/api/v1/albums/genre?genre=ROCK

Query Parameter: genre



#### 14. Get Album by Title

Endpoint: /api/v1/albums/title

Usage: GET http://localhost:8082/api/v1/albums/title?title=The%20Wall

Query Parameter: title




## Used technologies


Here's a brief overview of the technologies used in this project:


### 1. Spring Boot

Description: A framework for building production-ready applications with Java. It simplifies the development of Spring applications by providing a set of conventions and pre-configured settings.

Usage: Provides the core infrastructure for your RESTful API, including dependency injection, configuration management, and more.


### 2. Spring Data JPA

Description: A part of the Spring Data project that provides a simplified way to interact with databases using Java Persistence API (JPA). It offers a repository abstraction to handle CRUD operations.

Usage: Handles database operations for Album and Artist entities through CrudRepository.


### 3. Hibernate

Description: An object-relational mapping (ORM) framework that maps Java objects to database tables. It provides a way to manage database interactions through entity objects.
Usage: Used for the ORM implementation, allowing you to map Album and Artist entities to the database.


### 4. PostgreSQL

Description: An open-source relational database management system known for its robustness and advanced features.

Usage: Acts as the database where your Album and Artist data is stored.


### 5. Jackson

Description: A library for JSON processing in Java. It provides functionality for serializing Java objects to JSON and deserializing JSON back to Java objects.

Usage: Used to convert Java objects (Album, Artist) to and from JSON in HTTP requests and responses.


### 6. Lombok

Description: A Java library that helps reduce boilerplate code by generating common methods like getters, setters, and constructors at compile-time.

Usage: Used to simplify the Album and Artist classes by automatically generating getters, setters, and constructors.


### 7. RESTful API

Description: An architectural style for designing networked applications. It uses HTTP requests to perform CRUD operations on resources.

Usage: The project follows REST principles to expose endpoints for managing Album and Artist resources.


### 8. Maven

Description: A build automation tool used primarily for Java projects. It handles project dependencies, builds, and documentation.

Usage: Manages project dependencies and builds the application.


#### These technologies work together to provide a robust platform for managing albums and artists, allowing for easy database interactions, JSON processing, and RESTful communication.


Enjoy using!
