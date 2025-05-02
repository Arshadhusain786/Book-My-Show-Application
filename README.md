# 🎟️ Ticket Booking Application (Backend) – Spring Boot

This is a Java-based **Ticket Booking Application** built using **Spring Boot**, inspired by BookMyShow. It provides backend REST APIs for booking movie tickets, managing users, theatres, shows, movies, and tickets. The application follows a layered architecture (Controller, Service, Repository) and uses **MySQL** as the database.

---

## 📌 Key Features

- 🎬 Add & manage **movies**.
- 🏢 Add & manage **theatres** and **seats**.
- 📽️ Create and list **shows** for movies at theatres.
- 👤 Add and manage **users**.
- 🎟️ Book **tickets** by selecting available seats for a specific show.
- 💰 Calculate **total ticket price** automatically.
- 📅 Records **booking timestamp** using `@CreationTimestamp`.
- ❌ Prevents double booking by checking seat availability.
- 📚 Handles multiple entity relationships using JPA.

---

## 🧩 Tech Stack

| Layer            | Technology Used                  |
|------------------|----------------------------------|
| Language         | Java                             |
| Framework        | Spring Boot                      |
| ORM              | JPA + Hibernate                  |
| Database         | MySQL                            |
| Build Tool       | Maven                            |
| Utilities        | Lombok, Postman, Swagger         |
| Architecture     | MVC (Model - View - Controller)  |

---

## 🗂️ Project Structure

book_my_show
├── controller/ → REST controllers
├── service/ → Business logic
├── repository/ → Spring Data JPA interfaces
├── model/ → Entity classes (User, Show, Ticket, etc.)
├── dto/ → Request and Response DTOs
├── enums/ → Enum types for status & roles
└── BookMyShowApplication.java


---

## 🧠 How the Application Works

1. **Admin Actions**:
   - Adds movies, theatres, and defines available seats.
   - Schedules shows for movies in specific theatres.

2. **User Actions**:
   - Registers themselves.
   - Views movies and available shows.
   - Selects a show, chooses seats, and books tickets.

3. **Booking Flow**:
   - Validates seat availability.
   - Calculates total ticket price.
   - Stores booking date/time and selected seat info.
   - Updates seat availability.

---

## 💡 Highlighted Entities & Relationships

- `User`: Can book multiple tickets.
- `Movie`: Can have multiple shows.
- `Theatre`: Can host multiple shows and has many seats.
- `Show`: Linked to a movie and theatre. Each show has a list of seats.
- `Ticket`: Contains selected seats, total price, and booking timestamp.

> All relationships are implemented using `@OneToMany`, `@ManyToOne`, and `@JoinColumn` annotations with proper cascade behavior.

---

## 🔐 Exception Handling

- Global exception handling for common issues (e.g., user not found, seat already booked).
- Custom error messages returned using `@ControllerAdvice`.

---

## 📦 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven
- MySQL Server
- Postman (for API testing)



