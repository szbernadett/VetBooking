
package model;

import java.io.Serializable;

/**
 *  City class: Represents a city within an address.
 *  - name: String The name of the city.
 * 
 * @see Postcode
 * @see Address
 */
public class City implements Serializable {
    
    private String name;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
       
    
}
