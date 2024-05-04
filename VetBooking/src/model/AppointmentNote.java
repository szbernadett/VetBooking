
package model;

import java.io.Serializable;
import java.time.LocalDate;
import model.Appointment.AppointmentFee;

/**
 * AppointmentNote class: Represents a note for an appointment in the veterinary administration system.
 *  - appointment: Appointment The appointment that the note is for.
 * - notes: String The notes for the appointment.
 * - lastEdited: LocalDate The date the notes were last edited.
 * - lastEditedBy: Person The person who last edited the notes.
 * - AppointmentFee: AppointmentFee The fee for the appointment.
 * 
 * @see AppoinementFee
 * @see Appointment
 * 
 */
public class AppointmentNote implements Serializable {
    
    private Appointment appointment;
    private String notes;
    private LocalDate lastEdited;
    private Person lastEditedBy;
    private AppointmentFee AppointmentFee;

    public AppointmentNote() {
    }

    public AppointmentNote(Appointment appointment, String notes, 
                           LocalDate lastModified, Person lastEdited, 
                           AppointmentFee AppointmentFeed) {
        this.appointment = appointment;
        this.notes = notes;
        this.lastEdited = lastModified;
        this.lastEditedBy = lastEdited;
        this.AppointmentFee = AppointmentFee;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDate lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Person getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(Person lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    

    public AppointmentFee getAppointmentFee() {
        return AppointmentFee;
    }

    public void setAppointmentFee(AppointmentFee AppointmentFee) {
        this.AppointmentFee = AppointmentFee;
    }

    /**
     * Sets the text content, the date edited and the person editing the appointment note.
     * @param notes The new notes for the appointment.
     * @param person The person who is editing the notes.
     */
    
    public void editNotes(String notes, Person person){
        setNotes(notes);
        setLastEditedBy(person);
        setLastEdited(LocalDate.now());
    }
           
    
}
