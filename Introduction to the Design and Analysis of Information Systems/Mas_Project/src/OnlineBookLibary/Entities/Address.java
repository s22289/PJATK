package OnlineBookLibary.Entities;

import OnlineBookLibary.UserManager.Person;
import utils.ObjectPlusPlus;

/**
 * This class stores data about an address.
 *
 * Linked with:
 * 1. Person with cardinality 1 - 1
 * 2. Delivery with cardinality 1 - 1
 *
 * @see Person
 * @see Delivery
 */
public class Address extends ObjectPlusPlus {
    private String city;
    private String street;
    private String buildingNumber;
    private String zipCode;

    /**
     * Address constructor
     *
     * @param city the city name
     * @param street the street name
     * @param buildingNumber the building number
     * @param zipCode the zip code
     */
    public Address(String city,String street, String buildingNumber, String zipCode) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.zipCode = zipCode;
    }

    // Getters and Setters

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    @Override
    public String toString() {
        return city + " " + street + " " + buildingNumber + ", " + zipCode;
    }

    /**
     * This method changes the current address.
     *
     * @param city the new city name
     * @param street the new street name
     * @param buildingNumber the new building number
     * @param zipCode the new zip code
     */
    public void changeAddress(String city,String street, String buildingNumber, String zipCode) {
        setCity(city);
        setStreet(street);
        setBuildingNumber(buildingNumber);
        setZipCode(zipCode);
    }
}
