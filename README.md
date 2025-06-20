# Pokémon Card Tracker - Backend

A RESTful backend server for tracking and managing Pokémon cards, built with Java and Spring Boot. This server provides a complete API to support a frontend client, allowing users to create, update, view, and delete Pokémon cards. 

Pokémon card data is managed using an H2 in-memory database.

## Frontend Client (Angular)
[https://github.com/lamdevin/pokemon-card-client](https://github.com/lamdevin/pokemon-card-client)


## Features

- View, Add, Edit, Delete Pokemon Cards
- RESTful API built with Spring Boot
- Uses H2 database for storing cards

## Methods
### Get all cards
GET /api/pokemon/all

### Get card by ID
GET /api/pokemon/{id}

### Update card
POST /api/pokemon/add

### Edit card
PUT /api/pokemon/edit/{id}

### Delete card
DELETE /api/pokemon/{id}