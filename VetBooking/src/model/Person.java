
package model;

import java.io.Serializable;

/**
 * Person class: An abstract class to be extended by classes representing human entities of
 *               the veterinary adiminstration system.
 * - title: Title An enum representing the title of the person.
 * - firstName: String The first name of the person.
 * - lastName: String The last name of the person.
 * - email: String The email address of the person.
 * - phoneNumber: String The phone number of the person.
 * 
 * @see Title
 */
public abstract class Person implements Serializable {
    
    protected Title title;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        title=Title.NONE;
    }

    public Person(Title title, String firstName, String lastName, String email, String phoneNumber) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(title != null){
            sb.append(title);
            sb.append(" ");
        }
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        return sb.toString();
    }

 
    /**
     * Title enum: Represents the title of a person.
     * 
     * @see Person
     */
   
    public enum Title{
        MR("Mr"),
        MS("Ms"),
        MRS("Mrs"),
        MISS("Miss"),
        DR("Dr."),
        NONE("");
        
        private final String stringValue;
        
        private Title(String stringValue){
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }
        
        
    }

}
