# 🎴 PokemonCardServer

A RESTful backend server for tracking and managing Pokémon cards, built with Java and Spring Boot. This server provides a complete API to support a frontend client (Angular in development), allowing users to create, update, view, and delete Pokémon cards. Card data is persisted in a JSON file.

---

## 🚀 Features

- 🧩 View all Pokémon cards
- ➕ Add new cards
- 📝 Edit existing cards
- ❌ Delete cards
- 💾 Data saved in a local JSON file
- ⚙️ RESTful API built with Spring Boot

---

## 🛠️ Tech Stack

| Layer       | Tech                  |
|-------------|-----------------------|
| Language    | Java 17+              |
| Backend     | Spring Boot           |
| Persistence | JSON file (custom I/O)|
| Build Tool  | Maven                 |
| API Testing | Postman / curl        |

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