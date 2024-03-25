/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;
import java.util.Set;
import model.AnimalType.SpecialistCategory;

/**
 *
 * @author igbin
 */
public class Vet extends Person {

    private Set<SpecialistCategory> specialistCategories;

    public Vet() {
    }

    public Vet(String firstName, String lastName, String email, String phoneNumber,
            Set<SpecialistCategory> specialistCategories) {

        super(firstName, lastName, email, phoneNumber);
        title = Title.DR;
        this.specialistCategories = specialistCategories;
    }

    public Set<SpecialistCategory> getSpecialistCategories() {
        return specialistCategories;
    }

    public void setSpecialistCategories(Set<SpecialistCategory> specialistCategories) {
        this.specialistCategories = specialistCategories;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vet vet = (Vet) obj;
        return Objects.equals(firstName, vet.firstName)
                && Objects.equals(lastName, vet.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
                
    }
    
    
    

}
