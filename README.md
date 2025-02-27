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
| Key          |                                             Sample value                                             |        Description |
|:-------------|:----------------------------------------------------------------------------------------------------:|-------------------:|
| DB_DRIVER    |                                       com.mysql.cj.jdbc.Driver                                       |    Database driver |
| DB_USERNAME  |                                             library_user                                             |      Database user |
| DB_PASSWORD  |                                                111222                                                |  Database password |
| DB_URL       | jdbc:mysql://127.0.0.1:3306/library_db?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true  |       Database URL | 

#### Database migration

Using liquibase:
1. We can generate the tables/columns equivalent of our domain classes
2. Create our db environment
3. Set up initial tables/data

Goals to take note:

`dbmGenerateGormChangelog` - to generate the changelog file based on our domain classes
`dbmGormDiff` - to regenerate the changelog if there are changes in our domain classes
`dbmClearChecksums` - to fix the checksum validation error(without any changes)