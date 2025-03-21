package GUI.style;

import OnlineBookLibary.Entities.Address;
import OnlineBookLibary.Entities.Book;
import OnlineBookLibary.Entities.BookCopy;
import OnlineBookLibary.Entities.Enums.Genre;
import OnlineBookLibary.Entities.Enums.Language;
import OnlineBookLibary.Entities.Enums.OrderStatus;
import OnlineBookLibary.Entities.Order;
import OnlineBookLibary.UserManager.Customer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.ObjectPlus;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main starting class.
 * The class launches application with graphical interface.
 *
 * @see Application
 */
public class Main extends Application
{
    // public variables, which are transferred between scenes
    public static Customer customer;
    public static Order order;

    public static List<Book> bookList = new ArrayList<>();
    public static List<BookCopy> bookCopyList = new ArrayList<>();


    public final static String extentFile = System.getProperty("user.dir") + "/src/data.bin";


    /**
     * This method opens the main window
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/BookView.fxml")); // load scene from fxml
        primaryStage.setTitle("Online Book Libary");
        Scene scene = new Scene(root);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxWidth(600);
        primaryStage.setMinHeight(438);
        primaryStage.setMaxHeight(438);
        primaryStage.setScene(scene);
        primaryStage.show();

        // end program by closing window
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event)
            {
                Platform.exit();
                try
                {
                    saveExtent();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
                System.exit(0);
            }
        });
    }

    /**
     * This method is the main method
     *
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            readExtent();
            //ObjectPlus.showExtent(Order.class);
            launch(args);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * This method saves extents in file
     *
     * @throws Exception
     */
    public static void saveExtent() throws Exception
    {
        System.out.println("\n" + "SAVE EXTENT" + "\n");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(extentFile));
        ObjectPlus.writeExtents(out);
        out.close();
    }

    /**
     * This method reads extents from file or creates sample data if file does not exist
     *
     * @throws Exception
     */
    public static void readExtent() throws Exception
    {
        File data = new File(extentFile);

        if (data.exists() && data.length() != 0)
        {
            // create sample customer
            customer = new Customer("Furkan", "Unal", new Address("Warszawa", "Dubois 8", "22", "06-174"),
                    "796739962", "furkan@gmail.com");

            // create sample order for customer
            order = customer.createOrder(order, LocalDate.now(), OrderStatus.INITIATED);

            // optional - add credit card to customer
            customer.addCreditCard("1453000145338698", 175, "10/25");

            System.out.println("\n" + "READ EXTENT" + "\n");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(extentFile));
            ObjectPlus.readExtents(in);
            in.close();
        }
        else
        {
            System.out.println("\n" + "SAMPLE DATA HAS BEEN CREATED" + "\n");


            // create sample books
            Book book1 = new Book("The Haunting of Hill House", "Shirley Jackson", 50, Genre.FANTASY);
            Book book2 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 50, Genre.FANTASY);
            Book book3 = new Book("The Haunting of Hill House ", "Shirley Jackson", 50, Genre.HORROR);
            Book book4 = new Book("Wuthering Heights", "  – Emily Bronte", 50, Genre.ROMANCE);
            // add books to book list
            bookList.add(book1);
            bookList.add(book2);
            bookList.add(book3);
            bookList.add(book4);

            // create sample book copies
            BookCopy bookCopy1 = new BookCopy("1453145314538", Language.POLISH);
            BookCopy bookCopy2 = new BookCopy("1453145314537", Language.TURKISH);
            BookCopy bookCopy3 = new BookCopy("1453145314536", Language.FRENCH);
            BookCopy bookCopy4 = new BookCopy("1453145314535", Language.SPANISH);
            BookCopy bookCopy5 = new BookCopy("1453145314534", Language.ENGLISH);
            // link book copies with books
            book1.addLinkBookCopy(bookCopy1);
            book1.addLinkBookCopy(bookCopy2);
            book2.addLinkBookCopy(bookCopy3);
            book3.addLinkBookCopy(bookCopy4);
            book4.addLinkBookCopy(bookCopy5);

            // create sample customer
            customer = new Customer("Furkan", "Unal", new Address("Warszawa", "Dubois 8", "22", "06-174"),
                    "796739962", "furkan@gmail.com");

            // create sample order for customer
            order = customer.createOrder(order, LocalDate.now(), OrderStatus.INITIATED);

            // optional - add credit card to customer
            try {
                customer.addCreditCard("1453000145338698", 175, "07/25");
            } catch (Exception e) {
                System.err.println("Error adding credit card: " + e.getMessage());
            }
        }
    }
}

