# Spring Boot Backend CRUD Application

## Description

This backend application is a simple CRUD system with authentication and authorization using Spring Boot. The application allows users to register, log in, and perform CRUD operations on users and products.

## Design Assumptions

- **Users**: Users can register and log in. Admin users have permissions to delete.
- **Products**: CRUD operations are available for products.
- **Authentication & Authorization**: Implemented using Spring Security with roles (`USER` and `ADMIN`).

## Classes and Services

### Controllers
- **AuthController**: Handles registration and login endpoints.
- **UserController**: Manages user-related operations (list, get, update, delete).
- **ProductController**: Manages product-related operations (list, get, create, update, delete).

### Services
- **UserService**: Business logic for user operations and implements `UserDetailsService` for authentication.
- **ProductService**: Business logic for product operations.

### Repositories
- **UserRepository**: Data access layer for users.
- **ProductRepository**: Data access layer for products.

### DTOs
- **UserDTO**: Data Transfer Object for user data.
- **ProductDTO**: Data Transfer Object for product data.

### Models
- **User**: Entity representing a user.
- **Product**: Entity representing a product.

## How to Run the Application

### Prerequisites
- Java 17
- Maven

### Steps to Run

Navigate to the Spring Boot project directory and then build and run the application:
```
./mvnw spring-boot:run
```


Access the H2 Console (for debugging):
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## Simple Tests (Postman)

Only ADMIN users can make changes to other users. 
As this is just for demonstration purposes, the creation of admin accounts is unrestricted.

### Register a User

- **Method**: POST
- **URL**: `http://localhost:8080/api/auth/register`
- **Body**:
  ```json
  {
    "username": "user1",
    "password": "password",
    "role": "USER"
  }
  ```

### Register an Admin User

- **Method**: POST
- **URL**: `http://localhost:8080/api/auth/register`
- **Body**:
  ```json
  {
    "username": "admin",
    "password": "admin",
    "role": "ADMIN"
  }
  ```
### Login a User

- **Method**: POST
- **URL**: `http://localhost:8080/api/auth/login`
- **Body**:
  ```json
  {
    "username": "user1",
    "password": "password"
  }
  ```

### Get All Users

- **Method**: GET
- **URL**: `http://localhost:8080/api/users`
- **Headers**:
  - Authorization: Basic base64encoded(username:password)

Admin user should be allowed to see the list of users, but not the regular user.

### Get User by ID

- **Method**: GET
- **URL**: http://localhost:8080/api/users/1
- **Headers**:
   -Authorization: Basic base64encoded(username:password)


### Update User
- **Method**: PUT
- **URL**: http://localhost:8080/api/users/1
- **Headers**:
   -Authorization: Basic base64encoded(username:password)
- **Body**:
  ```json
  {
    "username": "user1_updated",
    "password": "password_updated",
    "role": "USER"
  }
  ```

### Delete User
- **Method**: DELETE
- **URL**: http://localhost:8080/api/users/1
- **Headers**:
  - Authorization: Basic base64encoded(username:password)

Admin user should be allowed to delete, but not regular user.

### Create a Product
- **Method**: POST
- **URL**: http://localhost:8080/api/products
- **Headers**:
  - Authorization: Basic base64encoded(username:password)
- **Body**:
  ```json
  {
    "name": "Product1",
    "price": 100
  }
  ```

### Get All Products
- **Method**: GET
- **URL**: http://localhost:8080/api/products
- **Headers**:
- **Authorization**: Basic base64encoded(username:password)


### Get Product by ID
- **Method**: GET
- **URL**: http://localhost:8080/api/products/1
- **Headers**:
  - Authorization: Basic base64encoded(username:password)

### Update Product
- **Method**: PUT
- **URL**: http://localhost:8080/api/products/1
- **Headers**:
  - Authorization: Basic base64encoded(username:password)
- **Body**:
 ```json
  {
    "name": "Product1_updated",
    "price": 150
  }
  ```

### Delete Product
- **Method**: DELETE
- **URL**: http://localhost:8080/api/products/1
- **Headers**:
- **Authorization**: Basic base64encoded(username:password)

  







