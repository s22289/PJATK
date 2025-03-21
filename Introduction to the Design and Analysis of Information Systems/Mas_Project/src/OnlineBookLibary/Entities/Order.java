package OnlineBookLibary.Entities;

import OnlineBookLibary.Entities.Enums.OrderStatus;
import OnlineBookLibary.UserManager.Customer;
import utils.ObjectPlusPlus;

import java.time.LocalDate;
import java.util.UUID;

/**
 * This class stores data about an order.
 *
 * Linked with:
 * 1. Customer with cardinality 0..* - 0..1
 * 2. Payment with cardinality 1 - *
 * 3. Book copy with cardinality 0..1 - 1..*
 * 4. Delivery with cardinality 1 - *
 *
 * @see Customer
 * @see Payment
 * @see BookCopy
 * @see Delivery
 */
public class Order extends ObjectPlusPlus {
    private String orderNumber; // unique
    private LocalDate orderCreationDate;
    private int finalPrice;
    private OrderStatus orderStatus;

    /**
     * Order constructor
     *
     * @param orderCreationDate the creation date of the order
     * @param orderStatus       the status of the order
     * @throws IllegalArgumentException if an invalid order status is provided
     */
    public Order(LocalDate orderCreationDate, OrderStatus orderStatus) {
        this.orderNumber = UUID.randomUUID().toString(); // Generate unique order number
        this.orderCreationDate = orderCreationDate;
        setOrderStatus(orderStatus);
    }

    // Getters and setters
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderCreationDate() {
        return orderCreationDate;
    }

    public void setOrderCreationDate(LocalDate orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the order status.
     *
     * @param orderStatus the order status to set
     * @throws IllegalArgumentException if an invalid order status is provided
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus == null) {
            throw new IllegalArgumentException("Order status cannot be null!");
        }
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order Number: " + getOrderNumber() + ", Creation Date: " + getOrderCreationDate() + ", Status: " + getOrderStatus();
    }

    /**
     * Links the order with a book copy.
     *
     * @param bookCopy the book copy to link with the order
     */
    public void addLinkBookCopy(BookCopy bookCopy) {
        this.addLink("includes", "is included in", bookCopy);
    }

    /**
     * Links the order with a payment.
     *
     * @param payment the payment to link with the order
     */
    public void addLinkPayment(Payment payment) {
        this.addLink("related to", "is concluded by", payment);
    }
}
