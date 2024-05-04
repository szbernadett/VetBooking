
package model;

import java.io.Serializable;
import java.util.Set;

/**
 * AnimalType class: Represents the type of animal that can be registered in the veterinary administration system.
 *  - typeName: String The name of the animal type.
 * - maxAge: int The maximum age of the animal type.
 * - specialistCategories: Set<SpecialistCategory> The set of SpecialistCategory enums that the animal type belongs to.
 * 
 *@see SpecialistCategory
 */
public class AnimalType implements Serializable {

    private String typeName;
    private int maxAge;
    private Set<SpecialistCategory> specialistCategories;

    public AnimalType() {
    }

    public AnimalType(String typeName, int maxAge,
                      Set<SpecialistCategory> specialistCategories) {
        this.typeName = typeName;
        this.maxAge = maxAge;
        this.specialistCategories = specialistCategories;
  
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Set<SpecialistCategory> getSpecialistCategories() {
        return specialistCategories;
    }

    public void setSpecialistCategories(Set<SpecialistCategory> specialistCategories) {
        this.specialistCategories = specialistCategories;
    }

 /**
  * SpecialistCategory enum: Represents special characteristicts of animals that define their 
                             care. Also used to denote the type of specialist care that a vet 
                             can provide.
  */
    public enum SpecialistCategory {
        LARGE("large"),
        EXOTIC("exotic"),
        VENOMOUS("venomous"),
        AQUATIC("aquatic");

        private final String stringValue;

        SpecialistCategory(String stringValue) {
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }
        
        
        public static SpecialistCategory fromStringValue(String stringValue){
            for(SpecialistCategory type : SpecialistCategory.values()){
                if(type.stringValue.equalsIgnoreCase(stringValue)){
                    return type;
                }
            }
            
            throw new IllegalArgumentException("No AppointmentType with this stringValue");
        }

    }

    @Override
    public String toString() {
        return typeName;
    }

    public enum AgeConstraint {
        MAX_AGE_ALL(50),
        MIN_AGE_ALL(0);

        private final Integer value;

        private AgeConstraint(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

    }
}
