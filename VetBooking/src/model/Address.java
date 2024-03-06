/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;


/**
 *
 * @author igbin
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

    public enum LocationType{
    ZOO, FARM, VET_OFFICE, DOMESTIC
    }
   
    
    
    
}
