This Java program demonstrates object-oriented programming, constructor overloading, user-defined exceptions, and ArrayList usage. It creates book objects, validates price inputs, stores valid books, and performs basic operations like printing and averaging.

Files
Book.java
InvalidPriceException.java
ArrayListOfBooks.java

Description

Book Class
Represents a book with attributes: title, author, genre, ISBN, and price.
Includes multiple constructors:

Default constructor

Fully parameterized constructor

Constructor with price validation (throws InvalidPriceException)

Copy constructor

InvalidPriceException Class
Custom exception thrown when a negative price is provided.

ArrayListOfBooks Class
Contains the main method.
Responsibilities:

Creates book objects

Handles invalid prices using try–catch

Stores valid books in ArrayList<Book>

Prints book details

Calculates average price

Filters books by genre using forEach()

Compilation and Execution

Compile:

javac Book.java InvalidPriceException.java ArrayListOfBooks.java


Run:

java ArrayListOfBooks

Concepts Demonstrated

Object-Oriented Programming

Constructor Overloading

User-Defined Exceptions

Exception Handling

Java ArrayList

forEach and Lambda Expressions
