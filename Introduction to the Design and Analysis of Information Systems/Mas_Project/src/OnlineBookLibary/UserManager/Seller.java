package OnlineBookLibary.UserManager;

import OnlineBookLibary.Entities.Address;
import OnlineBookLibary.Entities.Delivery;
import OnlineBookLibary.Entities.Enums.FormOfEmployment;
import OnlineBookLibary.Entities.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * This class stores data about a seller.
 *
 * Inherits from Employee
 *
 * Linked with:
 * 1. Delivery with cardinality 1 - *
 *
 * @see Employee
 * @see Delivery
 */
public class Seller extends Employee {
    private List<String> certificates;

    /**
     * Seller constructor
     *
     * @param name             Seller's first name
     * @param surname          Seller's last name
     * @param address          Seller's address
     * @param employmentDate   Date when the seller was hired
     * @param baseSalary       Seller's base salary
     * @param formOfEmployment Form of employment
     * @param certificates     List of seller's certificates
     * @throws Exception If the form of employment is invalid
     */
    public Seller(String name, String surname, Address address,
                  LocalDate employmentDate, int baseSalary, FormOfEmployment formOfEmployment,
                  List<String> certificates) throws Exception {
        super(name, surname, address, employmentDate, baseSalary, formOfEmployment);
        this.certificates = certificates;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    @Override
    public String toString() {
        String employeeToString = super.toString();
        return employeeToString + "\n"
                + "Certificates: " + getCertificates();
    }

    /**
     * This method adds a certificate to the certificates list
     *
     * @param newCertificate New certificate to add
     * @throws Exception If the certificate already exists
     */
    public void addCertificate(String newCertificate) throws Exception {
        if (!certificates.contains(newCertificate)) {
            certificates.add(newCertificate);
        } else {
            throw new Exception("This Seller already has this certificate!");
        }
    }

    /**
     * This method proceeds with the selected order
     *
     * @param order Order to proceed with
     */
    public void proceedOrder(Order order) {
        // Implement order processing logic here
    }

    /**
     * This method calculates the employee's final salary
     *
     * @return Salary with all bonuses
     */
    @Override
    public int calculateSalary() {
        int bonusToSalary = getCertificates().size() * 500;
        return getBaseSalary() + bonusToSalary;
    }
}
