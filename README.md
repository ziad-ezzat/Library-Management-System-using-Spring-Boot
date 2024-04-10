# Library-Management-System
 - A simple library management system using spring boot and MySQL.
## introduction
 - A Library Management System designed to see the books available in a library and to issue and return books.
 - The system is designed using spring boot and MySQL.
 - The backend is designed as a Monolithic Architecture with various nuances as discussed below.
 - The system is designed to be simple and easy to use.
## Technologies and Dependencies Used
 - Spring Boot
 - Spring Data JPA
 - Spring Web
 - MySQL
 - Lombok
 - Maven
 - spring validation
 - exception handling
## Using Library Management System
 
    git clone 
    cd Library-Management-System
    mvn clean install
    mvn spring-boot:run
## Backend Design
● Create entities for:

● Book: Includes attributes like ID, title, author, publication year, ISBN, etc.

● Patron: Contains details like ID, name, contact information, etc.

● Borrowing Record: Tracks the association between books and patrons,
including borrowing and return dates.
## Relationships Between Entities and ER diagram
    - The Book entity has a one-to-many relationship with the Borrowing Record entity.
    - The Patron entity has a one-to-many relationship with the Borrowing Record entity.
    - The Borrowing Record entity has a many-to-one relationship with both the Book and Patron entities.
## Functionalities Exposed
API Endpoints:  
● Implement RESTful endpoints to handle the following operations:  
#### Book management endpoints:   
● GET /api/books: Retrieve a list of all books.

● GET /api/books/{id}: Retrieve details of a specific book by ID.

● POST /api/books: Add a new book to the library.

● PUT /api/books/{id}: Update an existing book's information.

● DELETE /api/books/{id}: Remove a book from the library.

#### Patron management endpoints:
● GET /api/patrons: Retrieve a list of all patrons.

● GET /api/patrons/{id}: Retrieve details of a specific patron by ID.

● POST /api/patrons: Add a new patron to the system.

● PUT /api/patrons/{id}: Update an existing patron's information.

● DELETE /api/patrons/{id}: Remove a patron from the system.

#### Borrowing endpoints:
● POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to
borrow a book.

● PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.

## Aspects of the System
● LoggingAspect: Implement a logging aspect to log method calls and execution times.
● The aspect logs the method name, arguments, and execution time for each method call.
● The aspect should be applied to all service classes in the system.
# Author and Developed by
- [ziad ezzat elmaghawry](https://github.com/ziad-ezzat)