package OnlineBookLibary.UserManager;

import OnlineBookLibary.Entities.Address;
import OnlineBookLibary.Entities.Enums.FormOfEmployment;
import java.time.LocalDate;

/**
 * This is an abstract class that stores data about an employee.
 * The class is designed to be inherited from.
 *
 * Inherits from Person
 *
 * @see Person
 */
public abstract class Employee extends Person {
    private LocalDate employmentDate;
    private int baseSalary;
    private FormOfEmployment formOfEmployment;

    /**
     * Employee constructor
     *
     * @param name             Employee's first name
     * @param surname          Employee's last name
     * @param address          Employee's address
     * @param employmentDate   Date when the employee was hired
     * @param baseSalary       Employee's base salary
     * @param formOfEmployment Form of employment
     * @throws Exception If the form of employment is invalid
     */
    public Employee(String name, String surname, Address address,
                    LocalDate employmentDate, int baseSalary, FormOfEmployment formOfEmployment) throws Exception {
        super(name, surname, address);
        this.employmentDate = employmentDate;
        this.baseSalary = baseSalary;
        setFormOfEmployment(formOfEmployment);
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public FormOfEmployment getFormOfEmployment() {
        return formOfEmployment;
    }

    /**
     * Sets the form of employment from available forms of employment.
     *
     * @param formOfEmployment Form of employment
     * @throws Exception If the form of employment is invalid
     */
    public void setFormOfEmployment(FormOfEmployment formOfEmployment) throws Exception {
        if (formOfEmployment.equals(FormOfEmployment.CONTRACT) || formOfEmployment.equals(FormOfEmployment.FULL_TIME)) {
            this.formOfEmployment = formOfEmployment;
        } else {
            throw new Exception("There is no such form of employment!");
        }
    }

    @Override
    public String toString() {
        String personToString = super.toString();
        return personToString + "\n"
                + "Hired in: " + getEmploymentDate() + ", Salary: " + getBaseSalary() + ", Form of employment: " + getFormOfEmployment();
    }

    /**
     * This method calculates the employee's final salary.
     *
     * @return salary
     */
    public int calculateSalary() {
        return getBaseSalary();
    }
}
