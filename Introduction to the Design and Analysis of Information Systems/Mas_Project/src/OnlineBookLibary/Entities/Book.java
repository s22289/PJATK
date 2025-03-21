package OnlineBookLibary.Entities;

import OnlineBookLibary.Entities.Enums.Genre;
import OnlineBookLibary.UserManager.WarehouseStaff;
import utils.ObjectPlusPlus;
/**
 * This class stores data about a book.
 *
 * Linked with:
 * 1. WarehouseStaff with cardinality 0..* - 1
 * 2. BookCopy with cardinality 1 - 1..*
 *
 * @see WarehouseStaff
 * @see BookCopy
 *
 */
public class Book extends ObjectPlusPlus {
    private String title;
    private String author;
    private int quantity = 0; // quantity of book copies
    private int price;
    private Genre genre;

    /**
     * Book constructor
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @param price  the price of the book
     * @param genre  the genre of the book
     * @throws Exception if the genre is invalid
     */
    public Book(String title, String author, int price, Genre genre) throws Exception {
        this.title = title;
        this.author = author;
        this.price = price;
        setGenre(genre);
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    // Set genre from available genres

        public void setGenre(Genre genre) throws Exception
        {
            if (genre.equals(Genre.ROMANCE) || genre.equals(Genre.THRILLER) || genre.equals(Genre.HORROR) || genre.equals(Genre.FANTASY))
                this.genre = genre;
            else
                throw new Exception("There is no such genre!");
        }

    @Override
    public String toString() {
        return String.format("%s by %s\nQuantity: %d, Price: %d, Genre: %s",
                getTitle(), getAuthor(), getQuantity(), getPrice(), getGenre());
    }

    /**
     * This method links the book with a book copy.
     *
     * @param bookCopy the book copy to be linked
     */
    public void addLinkBookCopy(BookCopy bookCopy) {
        this.addLink("has", "contained in", bookCopy);
        quantity += 1;
    }
}
