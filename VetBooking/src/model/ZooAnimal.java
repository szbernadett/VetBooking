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
public class ZooAnimal extends Animal {

    public ZooAnimal() {
    }

    public ZooAnimal(String identifier, Caretaker caretaker, Address address, Date dateOfBirth, AnimalType animalType) {
        super(identifier, caretaker, address, dateOfBirth, animalType);
    }
    
}
