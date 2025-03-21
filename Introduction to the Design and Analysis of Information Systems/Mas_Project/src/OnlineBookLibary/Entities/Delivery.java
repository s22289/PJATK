package OnlineBookLibary.Entities;

import OnlineBookLibary.UserManager.Seller;
import utils.ObjectPlusPlus;
import OnlineBookLibary.Entities.Enums.DeliveryStatus;
import java.time.LocalDate;

/**
 * This class stores data about delivery.
 *
 * Linked with:
 * 1. Seller with cardinality * - 1
 * 2. Order with cardinality * - 1
 *
 * @see Seller
 * @see Order
 */
public class Delivery extends ObjectPlusPlus {
    private Address deliveryAddress;
    private LocalDate postingDate;
    private LocalDate expectedDeliveryDate;
    private DeliveryStatus deliveryStatus;

    /**
     * Delivery constructor
     *
     * @param deliveryAddress       the address where delivery is to be made
     * @param postingDate           the date when the delivery is posted
     * @param expectedDeliveryDate  the expected delivery date
     * @param deliveryStatus        the status of the delivery
     * @throws Exception            if there is an invalid delivery status
     */
    public Delivery(Address deliveryAddress, LocalDate postingDate, LocalDate expectedDeliveryDate, DeliveryStatus deliveryStatus) throws Exception {
        this.deliveryAddress = deliveryAddress;
        this.postingDate = postingDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        setDeliveryStatus(deliveryStatus);
    }

    // Getters and Setters
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * Set the delivery status from available delivery statuses
     *
     * @param deliveryStatus the status of the delivery
     * @throws Exception if the delivery status is invalid
     */
    public void setDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception {
        if (deliveryStatus.equals(DeliveryStatus.DISPATHCHED) || deliveryStatus.equals(DeliveryStatus.DELIVERED))
            this.deliveryStatus = deliveryStatus;
        else
            throw new Exception("There is no such delivery status!");
    }

    @Override
    public String toString() {
        return "Delivery Address: " + getDeliveryAddress() + "\n"
                + "Posting Date: " + getPostingDate() + ", Expected Delivery Date: " + getExpectedDeliveryDate() + ", Delivery Status: " + getDeliveryStatus();
    }
}
