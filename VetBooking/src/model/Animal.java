/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author igbin
 */
public abstract class Animal implements Serializable {
    
    protected String identifier;
    protected Caretaker caretaker;
    protected Address address;
    protected LocalDate dateOfBirth;
    protected AnimalType animalType;
    protected Gender gender;
    

    public Animal() {
    }

    public Animal(String identifier, Caretaker caretaker, Address address, 
                  LocalDate dateOfBirth, AnimalType animalType, Gender gender) {
        this.identifier = identifier;
        this.caretaker = caretaker;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.animalType = animalType;
        this.gender = gender;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return identifier +", " +getAnimalType().getTypeName();
    }

   
    
     public enum Gender{
        MALE("male"),
        FEMALE("female"),
        NA("n/a");
        
        private final String stringValue;
        
        private Gender(String stringValue){
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }
        
        
    }
    
}
