# Book keeping / Library

## Objectives

1. Allow anonymous users to browse the shelves and check their availability
2. Allow logged in users to borrow books
3. Allow the librarian to add new books to the system
4. Track the book's borrow history

## Technical

### Controllers

Use the controller directory to hold all the application endpoints.

### Domains

Use the domain directory to contain the database entities.

### Services

#### Data Access Objects (DAO) / Repository

Create a DAO/repository service class to manage database transactions with the entities

#### Business service

Create a service class for business logic processing wherein the function does not focus on a single entity and requires data processing for different domains

#### Environment variables
DB_USERNAME default sa
DB_PASSWORD default
DB_NAME 