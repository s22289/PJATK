package OnlineBookLibary.UserManager;


import OnlineBookLibary.Entities.Address;
import utils.ObjectPlusPlus;

/**
 * This abstract class stores data about a person.
 * It is designed to be inherited by other classes to reuse person-related data.
 *
 * Linked with:
 * 1. Address with cardinality 1 - 1
 *
 * @see Address
 */

public abstract class Person extends ObjectPlusPlus
{
    private String name;
    private String surname;
    private Address address;

    public Person(String name, String surname, Address address)
    {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return getName() + " " + getSurname() + "\n"
                + "Address: " + getAddress().toString();
    }

    /**
     * This method changes person's current address
     *
     * @param city
     * @param street
     * @param buildingNumber
     * @param zipCode
     */
    public void changeAddress(String city, String street, String buildingNumber, String zipCode)
    {
        this.address.changeAddress(city, street, buildingNumber, zipCode );
    }

}
