package OnlineBookLibary.UserManager;

import OnlineBookLibary.Entities.Address;
import OnlineBookLibary.Entities.Enums.FormOfEmployment;
import OnlineBookLibary.Entities.Enums.NotificationState;
import OnlineBookLibary.Entities.Notification;

import java.time.LocalDate;

/**
 * This class stores data about a support worker.
 * Support workers can support notifications created by customers.
 *
 * Inherits from Employee
 *
 * Linked with:
 * 1. Notification with cardinality 1 - 0..*
 *
 * @see Employee
 * @see Notification
 */
public class Support extends Employee {
    /**
     * Support worker constructor
     *
     * @param name             Support worker's first name
     * @param surname          Support worker's last name
     * @param address          Support worker's address
     * @param employmentDate   Date when the support worker was hired
     * @param baseSalary       Support worker's base salary
     * @param formOfEmployment Form of employment
     * @throws Exception If the form of employment is invalid
     */
    public Support(String name, String surname, Address address,
                   LocalDate employmentDate, int baseSalary, FormOfEmployment formOfEmployment) throws Exception {
        super(name, surname, address, employmentDate, baseSalary, formOfEmployment);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * This method proceeds with the selected notification
     *
     * @param notification Notification to proceed with
     * @throws Exception If the notification has already been closed
     */
    public void proceedNotification(Notification notification) throws Exception {
        if (notification.getNotificationState().equals(NotificationState.OPEN)) {
            // TODO: notification proceed functionality

            System.out.println("Notification: " + notification.getTitle() + " has been proceeded");
            notification.setNotificationState(NotificationState.CLOSED);
        } else {
            throw new Exception("This notification has already been closed!");
        }
    }
}
