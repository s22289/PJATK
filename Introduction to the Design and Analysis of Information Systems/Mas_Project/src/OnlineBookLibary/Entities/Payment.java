package OnlineBookLibary.Entities;

import OnlineBookLibary.UserManager.Customer;
import utils.ObjectPlusPlus;

import java.time.LocalDate;

/**
 * This class stores data about a payment.
 *
 * Linked with:
 * 1. Customer with cardinality * - 1
 * 2. Order with cardinality * - 1
 *
 * @see Customer
 * @see Order
 */
public class Payment extends ObjectPlusPlus {
    private int price;
    private LocalDate paymentDate;

    /**
     * Payment constructor
     *
     * @param price the price of the payment
     * @param paymentDate the date of the payment
     */
    public Payment(int price, LocalDate paymentDate) {
        this.price = price;
        this.paymentDate = paymentDate;
    }

    // Getters and setters
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Price: " + getPrice() + ", Payment Date: " + getPaymentDate();
    }
}
