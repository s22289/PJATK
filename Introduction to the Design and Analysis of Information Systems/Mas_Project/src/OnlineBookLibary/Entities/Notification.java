package OnlineBookLibary.Entities;

import OnlineBookLibary.UserManager.Customer;
import OnlineBookLibary.UserManager.Support;
import utils.ObjectPlusPlus;
import OnlineBookLibary.Entities.Enums.NotificationState;
import java.time.LocalDate;

/**
 * This class stores data about a notification.
 *
 * Linked with:
 * 1. Customer with cardinality 0..* - 1
 * 2. Support_staff with cardinality 0..* - 1
 *
 * @see Customer
 * @see Support
 */
public class Notification extends ObjectPlusPlus {
    private String title;
    private String description;
    private LocalDate creationDate;
    private NotificationState notificationState;

    /**
     * Notification constructor
     *
     * @param title             the title of the notification
     * @param description       the description of the notification
     * @param creationDate      the creation date of the notification
     * @param notificationState the status of the notification
     */
    public Notification(String title, String description, LocalDate creationDate, NotificationState notificationState) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.notificationState = notificationState;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public NotificationState getNotificationState() {
        return notificationState;
    }

    public void setNotificationState(NotificationState notificationState) {
        this.notificationState = notificationState;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + ", Created at: " + getCreationDate() + ", Status: " + getNotificationState() + "\n"
                + "Description: " + getDescription();
    }
}
