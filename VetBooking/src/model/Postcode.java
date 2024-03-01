/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author igbin
 */
public class Postcode {
    
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
