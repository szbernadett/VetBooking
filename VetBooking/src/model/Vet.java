
package model;

import java.util.Objects;
import java.util.Set;
import model.AnimalType.SpecialistCategory;

/**
 * Vet class: Represents a vet in the veterinary administration system.
 * - specialistCategories: Set<SpecialistCategory> The set of SpecialistCategory enums that define 
 *                                                 which animals matching these needs the vet is
 *                                                 able to treat. Example: if a vet has SpecialistCategory.LARGE_ANIMAL
 *                                                 and SpecialistCategory.EXOTIC in their set, they can
 *                                                 treat animals that have either, both or none of these
 *                                                 specialist categories assigned to them. 
 * @see Person
 * @see SpecialistCategory
 * @see AninmalType
 *                                                         
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
