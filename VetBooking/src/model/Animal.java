
package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * Animal class: An abstract class that defines what mutual fields classes extending this class should have
 * - identifier: String An alphanumeric value that uniquely identifies the animal. Can be a name (e.g. Spot) or numeric string.
 * - caretaker: Caretaker The Caretaker object that is responsible for the animal
 * - address: Address The Address object that the animal is located at
 * - dateOfBirth: DateOfBirth The DateOfBirth object that represents the animal's date of birth. Can have a LocalDate value 
 *                or N/A if this field is not applicable, not known or not relevant to the animal's care.
 * - animalType: AnimalType Represents a group of animals. Can be very specific (e.g. Siamese cat) or general (e.g spicer). 
 *               Holds further information about the specific group of animals.
 * - gender: Gender An enum representing the gender of the animal. Can be male, female or N/A if this
 *           field is not applicable, not known or not relevant to the animal's care. 
 */
public abstract class Animal implements Serializable {

    protected String identifier;
    protected Caretaker caretaker;
    protected Address address;
    protected DateOfBirth dateOfBirth;
    protected AnimalType animalType;
    protected Gender gender;

    public Animal() {
    }

    public Animal(String identifier, Caretaker caretaker, Address address,
            DateOfBirth dateOfBirth, AnimalType animalType, Gender gender) {
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

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
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
        return identifier + ", " + getAnimalType().getTypeName();
    }

    public enum Gender {
        MALE("male"),
        FEMALE("female"),
        NA("n/a");

        private final String stringValue;

        private Gender(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        public static Gender fromStringValue(String value) {
            for(Gender gender : Gender.values()){
                if(gender.getStringValue().equals(value)){
                    return gender;
                }
            }
            throw new IllegalArgumentException("No Gender with this stringValue");
        }

        @Override
        public String toString() {
            return stringValue;
        }

    }

    public static class DateOfBirth implements Serializable {

        private LocalDate date;
        private boolean isNotApplicable;

        public static DateOfBirth of(LocalDate date) {
            DateOfBirth dob = new DateOfBirth();
            dob.date = date;
            dob.isNotApplicable = false;
            return dob;
        }

        public static DateOfBirth notApplicable() {
            DateOfBirth dob = new DateOfBirth();
            dob.date = null;
            dob.isNotApplicable = true;
            return dob;
        }

        public LocalDate getDate() {
            return date;
        }

        public boolean isNotApplicable() {
            return isNotApplicable;
        }

        @Override
        public String toString() {
            return isNotApplicable ? "N/A" : date.toString();
        }
    }

}
