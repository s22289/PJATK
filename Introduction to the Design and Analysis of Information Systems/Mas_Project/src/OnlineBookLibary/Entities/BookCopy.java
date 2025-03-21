package OnlineBookLibary.Entities;

import utils.ObjectPlusPlus;
import OnlineBookLibary.Entities.Enums.Language;
import java.util.HashMap;
import java.util.Map;

/**
 * This class stores data about a book copy.
 *
 * Linked with:
 * 1. Book with cardinality 0..* - 1
 * 2. Order with cardinality 1..* - 0..1
 *
 * @see Book
 * @see Order
 */
public class BookCopy extends ObjectPlusPlus {
    private String isbn; // unique value, 13-digit ISBN number
    private Language language;
    private static Map<String, BookCopy> bookCopyMap = new HashMap<>(); // Map to store book copies with unique 13-digit ISBN numbers

    /**
     * Book copy constructor
     *
     * @param isbn     the unique 13-digit ISBN number
     * @param language the language of the book copy
     * @throws Exception if the ISBN number is invalid or already exists
     */
    public BookCopy(String isbn, Language language) throws Exception {
        setISBN(isbn);
        if (language.equals(Language.ENGLISH) || language.equals(Language.POLISH) || language.equals(Language.SPANISH) || language.equals(Language.FRENCH)|| language.equals(Language.GERMAN)|| language.equals(Language.TURKISH)|| language.equals(Language.RUSSIAN)|| language.equals(Language.ITALIAN))
            this.language = language;
        else
            throw new Exception("There is no such Language!");
    }

    public String getISBN() {
        return isbn;
    }

    // Set unique value of ISBN number (13 digits)
    public void setISBN(String isbn) throws Exception {
        if (!bookCopyMap.containsKey(isbn)) {
            if (isbn.matches("^\\d{13}$")) {
                bookCopyMap.put(isbn, this);
                this.isbn = isbn;
            } else
                throw new Exception("ISBN number must be 13 digits!");
        } else
            throw new Exception("Book with this ISBN number already exists!");
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return getISBN() + ", language: " + getLanguage();
    }
}
