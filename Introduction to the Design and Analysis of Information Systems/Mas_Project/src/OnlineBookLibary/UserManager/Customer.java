package OnlineBookLibary.UserManager;

import OnlineBookLibary.Entities.Address;
import OnlineBookLibary.Entities.Enums.NotificationState;
import OnlineBookLibary.Entities.Enums.OrderStatus;
import OnlineBookLibary.Entities.Notification;
import OnlineBookLibary.Entities.Order;
import OnlineBookLibary.Entities.Payment;
import utils.ObjectPlusPlus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class stores data about a customer.
 *
 * Inherits from Person
 *
 * Linked with:
 * 1. Notification with cardinality 1 - 0..*
 * 2. Credit Card with cardinality 1 - 0..1
 * 3. Payment with cardinality 1 - *
 *
 * @see Person
 * @see CreditCard
 * @see Notification
 * @see Payment
 */
public class Customer extends Person {
    private String phoneNumber;
    private String emailAddress;
    private static Map<String, CreditCard> creditCardMap = new HashMap<>(); // map for storing credit cards with unique card numbers

    /**
     * Customer constructor
     *
     * @param name          Customer's first name
     * @param surname       Customer's last name
     * @param address       Customer's address
     * @param phoneNumber   Customer's phone number
     * @param emailAddress  Customer's email address
     */
    public Customer(String name, String surname, Address address,
                    String phoneNumber, String emailAddress) {
        super(name, surname, address);
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        String personToString = super.toString();
        return personToString + "\n" + "Phone number: " + getPhoneNumber() + ", Email address: " + getEmailAddress();
    }

    /**
     * This method creates a new order.
     *
     * @param orderToCreate Order to be created
     * @param localDate     Date of the order
     * @param orderStatus   Status of the order
     * @return newly created order
     * @throws Exception
     */
    public Order createOrder(Order orderToCreate, LocalDate localDate, OrderStatus orderStatus) throws Exception {
        orderToCreate = new Order(localDate, orderStatus);
        addLinkOrder(orderToCreate);
        return orderToCreate;
    }

    /**
     * This method creates a new notification.
     *
     * @param title       Title of the notification
     * @param description Description of the notification
     * @return newly created notification
     */
    public Notification createNotification(String title, String description) {
        Notification notification = new Notification(title, description, LocalDate.now(), NotificationState.OPEN);
        addLinkNotification(notification);
        return notification;
    }

    /**
     * This method links the customer with the selected order.
     *
     * @param order Order to be linked
     */
    public void addLinkOrder(Order order) {
        this.addLink("places", "is placed by", order);
    }

    /**
     * This method links the customer with the selected payment.
     *
     * @param payment Payment to be linked
     */
    public void addLinkPayment(Payment payment) {
        this.addLink("pays", "is paid by", payment);
    }

    /**
     * This method links the customer with the selected notification.
     *
     * @param notification Notification to be linked
     */
    public void addLinkNotification(Notification notification) {
        this.addLink("creates", "is created by", notification);
    }

    /**
     * This method adds a credit card.
     *
     * @param cardNumber     Credit card number
     * @param codeCVC        CVC code of the credit card
     * @param expirationDate Expiration date of the credit card
     * @throws Exception
     */
    public void addCreditCard(String cardNumber, int codeCVC, String expirationDate) throws Exception {
        CreditCard creditCard = new CreditCard(cardNumber, codeCVC, expirationDate);
        this.addPart("owns", "is owned by", creditCard);
    }

    /**
     * This method checks if the customer has a credit card.
     *
     * @return true if the customer has a credit card, false otherwise
     */
    public boolean hasCreditCard() {
        return this.isLink("owns");
    }

    /**
     * This method returns the credit card's number.
     *
     * @return credit card number
     * @throws Exception
     */
    public String getCreditCardNumber() throws Exception {
        ArrayList<CreditCard> creditCards = returnLinks("owns");
        return creditCards.get(0).getCardNumber();
    }

    /**
     * This method returns the credit card's CVC code.
     *
     * @return credit card CVC code
     * @throws Exception
     */
    public int getCreditCardCodeCVC() throws Exception {
        ArrayList<CreditCard> creditCards = returnLinks("owns");
        return creditCards.get(0).getCodeCVC();
    }

    /**
     * This method returns the credit card's expiration date.
     *
     * @return credit card expiration date
     * @throws Exception
     */
    public String getCreditCardExpirationDate() throws Exception {
        ArrayList<CreditCard> creditCards = returnLinks("owns");
        return creditCards.get(0).getExpirationDate();
    }

    /**
     * This class stores data about a credit card.
     * It is an inner class of Customer.
     *
     * Linked with:
     * 1. Customer with cardinality 0..1 - 1
     */
    class CreditCard extends ObjectPlusPlus {
        private String cardNumber; // unique
        private int codeCVC;
        private String expirationDate;

        private CreditCard(String cardNumber, int codeCVC, String expirationDate) throws Exception {
            setCardNumber(cardNumber);
            this.codeCVC = codeCVC;
            this.expirationDate = expirationDate;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        /**
         * Sets a unique value for the credit card number.
         *
         * @param cardNumber Credit card number
         * @throws Exception If the card number is invalid or already exists
         */
       public void setCardNumber(String cardNumber) throws Exception {
            if (!creditCardMap.containsKey(cardNumber)) {
                if (cardNumber.length() == 16) {
                    creditCardMap.put(cardNumber, this);
                    this.cardNumber = cardNumber;
                } else {
                    throw new Exception("Card number must have 16 digits!");
                }
            } else {
                throw new Exception("Card with this number already exists!");
            }
        }

        public int getCodeCVC() {
            return codeCVC;
        }

        public void setCodeCVC(int codeCVC) {
            this.codeCVC = codeCVC;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }

        @Override
        public String toString() {
            return cardNumber + ", CVC: " + codeCVC + ", Expiration date: " + expirationDate;
        }
    }
}
