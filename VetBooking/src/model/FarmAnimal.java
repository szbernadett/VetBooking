/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author igbin
 */
public class FarmAnimal extends Animal {

    public FarmAnimal() {
    }

    public FarmAnimal(String identifier, Caretaker caretaker, Address address, LocalDate dateOfBirth, AnimalType animalType, Gender gender) {
        super(identifier, caretaker, address, dateOfBirth, animalType, gender);
    }

   
    
}
