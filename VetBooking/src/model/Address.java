
package model;

import java.io.Serial;
import java.io.Serializable;


/**
 *  Address class: Represents the address of an animal in the veterinary administration system.
 *  - addressLine1: String The first line of the address.
 *  - addressLine2: String The second line of the address.
 *  - postcode: Postcode The postcode of the address.
 *  - locationType: LocationType An enum representing the location type: zoo, farm, vet surgery, domestic.
 *
 *@see Postcode
 *@see LocationType
 */
public class Address implements Serializable {
    
    private String addressLine1;
    private String addressLine2;
    private Postcode postcode;
    private LocationType locationType;

    public Address() {
    }

    public Address(String addressLine1, String addressLine2, Postcode postcode, LocationType locationType) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postcode = postcode;
        this.locationType = locationType;
    }
    
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public Postcode getPostcode() {
        return postcode;
    }

    public void setPostcode(Postcode postcode) {
        this.postcode = postcode;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    /**
     * LocationType enum: Represents the location type of the address. Used to determine the location
     *                    of vet appointments and display location information in the view.
     */
    public enum LocationType{
    ZOO("zoo"), 
    FARM("farm"), 
    VET_SURGERY("vet surgery"), 
    DOMESTIC("domestic");
    
        private final String stringValue;

        private LocationType(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }
        
        
        
    }

    @Override
    public String toString() {
        return  addressLine1 + " " + postcode;
    }
    
   
    
    
    
}
