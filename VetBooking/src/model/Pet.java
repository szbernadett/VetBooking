
package model;


/**
 *
 * Pet class: Represents an animal that is kept as a pet at a domestic address. The location value of the animal is
 *            held by the Address object's LocationType field, which in this case would be
 *            LocationType.DOMESTIC.
 * 
 * @see Animal
 */
public class Pet extends Animal {

    public Pet() {
    }

    public Pet(String identifier, Caretaker caretaker, Address address, DateOfBirth dateOfBirth, AnimalType animalType, Gender gender) {
        super(identifier, caretaker, address, dateOfBirth, animalType, gender);
    }

    
            
    
}
