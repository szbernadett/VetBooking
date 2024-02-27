/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author igbin
 */
public abstract class Animal {
    
    protected String identifier;
    protected Person careTaker;
    protected Address address;
    protected Date dateOfBirth;
    protected AnimalType animalType;
    

    public Animal() {
    }

    public Animal(String identifier, Person careTaker, Address address, Date dateOfBirth, AnimalType animalType) {
        
        this.identifier = identifier;
        this.careTaker = careTaker;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.animalType = animalType;
    }

    public Person getCareTaker() {
        return careTaker;
    }

    public void setCareTaker(Person careTaker) {
        this.careTaker = careTaker;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    
    
    
    
}
