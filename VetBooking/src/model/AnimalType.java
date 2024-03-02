/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author igbin
 */
public class AnimalType {
    
    private String typeName; 
    private int maxAge;
    private HashSet<SpecialistCategory> specialistCategories;

    public AnimalType() {
    }

    public AnimalType(String typeName, int maxAge, 
                      HashSet<SpecialistCategory> specialistCategories) {
        this.typeName = typeName;
        this.maxAge = maxAge;
        this.specialistCategories = specialistCategories;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public HashSet<SpecialistCategory> getSpecialistCategories() {
        return specialistCategories;
    }

    public void setSpecialistCategories(HashSet<SpecialistCategory> specialistCategories) {
        this.specialistCategories = specialistCategories;
    }
    
    


public enum SpecialistCategory{
    LARGE("large"), 
    EXOTIC("exotic"), 
    VENOMOUS("venomous"), 
    AQUATIC("aquatic");
    
    private final String stringValue;
    
    SpecialistCategory(String stringValue){
        this.stringValue = stringValue;
    }

        @Override
        public String toString() {
            return stringValue;
        }
    
        
}
}