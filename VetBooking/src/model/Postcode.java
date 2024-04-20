
package model;

import java.io.Serializable;

/**
 * Postcode class: Represents a postcode within an address
 * - postcode: String The acutal postcode (e.g. M94ED)
 * - city: City The City object that the postcode belongs to
 *      
 * 
 */
public class Postcode implements Serializable {
    
    private String postcode;
    private City city;

    public Postcode() {
    }

    public Postcode(String postCode, City city) {
        this.postcode = postCode;
        this.city = city;
    }

    public String getPostCode() {
        return postcode;
    }

    public void setPostCode(String postCode) {
        this.postcode = postCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
    

    @Override
    public String toString() {
        return postcode;
    }
    
    
    
    
    
    
}
