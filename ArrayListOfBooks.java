import java.util.ArrayList;

public class ArrayListOfBooks {
    public static void main(String[] args) {

        ArrayList<Book> books = new ArrayList<>();

        // Default constructor
        Book b1 = new Book();
        books.add(b1);

        // Full constructor
        Book b2 = new Book(
                "Atomic Habits",
                499.0,
                "9780735211292",
                "Self-help",
                "James Clear"
        );
        books.add(b2);

        // Constructor with exception
        try {
            Book b3 = new Book("Clean Code", -100.0);
            books.add(b3);
        } catch (InvalidPriceException e) {
            System.out.println("Invalid Price: " + e.getMessage());
        }

        // Copy constructor
        Book original = new Book("One Piece", 300.0, "1234567890", "Fiction", "Eiichiro Oda");
        Book b4 = new Book(original);
        books.add(b4);

        System.out.println("\nBook Inventory:\n");

        double totalPrice = 0.0;

        for (Book b : books) {
            System.out.println(b);
            totalPrice += b.price;
        }

        if (!books.isEmpty()) {
            double averagePrice = totalPrice / books.size();
            System.out.println("\nAverage Price of Books: $" + averagePrice);
        }

        System.out.println("\nFiction Books:");

        books.forEach(book -> {
            if (book.genre.equalsIgnoreCase("Fiction")) {
                System.out.println(book);
            }
        });
    }
}

