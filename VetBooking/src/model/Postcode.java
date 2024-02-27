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

    public Postcode() {
    }

    public Postcode(String postCode) {
        this.postcode = postCode;
    }

    public String getPostCode() {
        return postcode;
    }

    public void setPostCode(String postCode) {
        this.postcode = postCode;
    }

    @Override
    public String toString() {
        return postcode;
    }
    
    
    
    
    
    
}
