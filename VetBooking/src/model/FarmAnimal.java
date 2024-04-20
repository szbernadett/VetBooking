
package model;



/**
 * FarmAnimal class: Represents an animal that is kept on a farm. The location value of the animal is
 *                   held by the Address object's LocationType field, which in this case would be
 *                  LocationType.FARM.
 * 
 */
public class FarmAnimal extends Animal {

    public FarmAnimal() {
    }

    public FarmAnimal(String identifier, Caretaker caretaker, Address address, DateOfBirth dateOfBirth, AnimalType animalType, Gender gender) {
        super(identifier, caretaker, address, dateOfBirth, animalType, gender);
    }

   
    
}
