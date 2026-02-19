
public class Book {
    public String title;
    public double price;
    public String ISBN;
    public String genre;
    public String author;

    public Book() {
        title = "Some title";
        price = 100.00; 
        ISBN = "1234567890";
        genre = "Some genre";
        author = "XYZ";
    }

    //one default constructor 
    public Book(String title, double price, String ISBN, String genre, String author) {
        this.title = title;
        this.price = price;
        this.ISBN = ISBN;
        this.genre = genre;
        this.author = author;
    }

    //one parameterized constructor
    public Book(String title, double price) throws InvalidPriceException {
    this.title = title;

    if (price < 0)
        throw new InvalidPriceException("Price cannot be negative");

    this.price = price;
    this.ISBN = "1234567890";
    this.genre = "Some genre";
    this.author = "XYZ";
    }

    //copy constructor
    public Book(Book other) {
        this.title = other.title;
        this.price = other.price;
        this.ISBN = other.ISBN;
        this.genre = other.genre;
        this.author = other.author;
    }

    

    @Override
    public String toString() {
    return title + " | " + author + " | " + genre + " | " + ISBN + " | " + price;
}



}