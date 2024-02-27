/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Set;
import model.AnimalType.SpecialistCategory;

/**
 *
 * @author igbin
 */
public class Vet extends Person{
    
    private Set<SpecialistCategory> specialistCategories;

    public Vet() {
    }

    public Vet(Set<SpecialistCategory> specialistCategories, String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
        this.specialistCategories = specialistCategories;
    }

    public Set<SpecialistCategory> getSpecialistCategories() {
        return specialistCategories;
    }

    public void setSpecialistCategories(Set<SpecialistCategory> specialistCategories) {
        this.specialistCategories = specialistCategories;
    }
    
    
}
