
package model;



/**
 * ZooAnimal class: Represents an animal that is kept in a zoo. The location value of the animal is
 *                  held by the Address object's LocationType field, which in this case would be
 *                  LocationType.ZOO.
 * 
 * @see Person
 */
public class ZooAnimal extends Animal {

    public ZooAnimal() {
    }

    public ZooAnimal(String identifier, Caretaker caretaker, Address address, DateOfBirth dateOfBirth, AnimalType animalType, Gender gender) {
        super(identifier, caretaker, address, dateOfBirth, animalType, gender);
    }

}
