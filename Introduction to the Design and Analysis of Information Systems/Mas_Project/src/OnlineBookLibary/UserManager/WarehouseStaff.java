package OnlineBookLibary.UserManager;

import OnlineBookLibary.Entities.Address;
import OnlineBookLibary.Entities.Book;
import OnlineBookLibary.Entities.BookCopy;
import OnlineBookLibary.Entities.Enums.FormOfEmployment;
import OnlineBookLibary.Entities.Enums.Genre;
import OnlineBookLibary.Entities.Enums.Language;

import java.time.LocalDate;
import java.util.List;

/**
 * This class stores data about warehouse staff.
 * Warehouse staff can add, edit, and delete books.
 *
 * Inherits from Employee
 *
 * Linked with:
 * 1. Book with cardinality 1 - 1..*
 *
 * @see Employee
 * @see Book
 */
public class WarehouseStaff extends Employee {
    /**
     * WarehouseStaff constructor
     *
     * @param name             Warehouse staff's first name
     * @param surname          Warehouse staff's last name
     * @param address          Warehouse staff's address
     * @param employmentDate   Date when the warehouse staff was hired
     * @param baseSalary       Warehouse staff's base salary
     * @param formOfEmployment Form of employment
     * @throws Exception If the form of employment is invalid
     */
    public WarehouseStaff(String name, String surname, Address address,
                          LocalDate employmentDate, int baseSalary, FormOfEmployment formOfEmployment) throws Exception {
        super(name, surname, address, employmentDate, baseSalary, formOfEmployment);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * This method links warehouse staff with a selected book
     *
     * @param book Book to link
     */
    public void addLinkBook(Book book) {
        this.addLink("edits", "is edited", book);
    }

    /**
     * This method adds a new book
     *
     * @param title  Title of the book
     * @param author Author of the book
     * @param price  Price of the book
     * @param genre  Genre of the book
     * @return Newly created book
     * @throws Exception If an error occurs while creating the book
     */
    public Book addBook(String title, String author, int price, Genre genre) throws Exception {
        Book newBook = new Book(title, author, price, genre);
        this.addLinkBook(newBook);
        return newBook;
    }

    /**
     * This method deletes a selected book from the book list
     *
     * @param bookToDelete Book to delete
     * @param bookList     List of books
     */
    public void deleteBook(Book bookToDelete, List<Book> bookList) {
        bookList.remove(bookToDelete);
    }

    /**
     * This method adds a new book copy and links it with a selected book.
     *
     * @param isbn     ISBN number of the book copy
     * @param language Language of the book copy
     * @param book     Book to link the copy with
     * @return Newly created book copy
     * @throws Exception If an error occurs while creating the book copy
     */
    public BookCopy addBookCopy(String isbn, Language language, Book book) throws Exception {
        BookCopy newBookCopy = new BookCopy(isbn, language);
        book.addLinkBookCopy(newBookCopy);
        return newBookCopy;
    }

}
