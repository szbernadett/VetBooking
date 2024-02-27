/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import model.Appointment.AppointmentFee;

/**
 *
 * @author igbin
 */
public class AppointmentNote {
    
    private Appointment appointment;
    private String notes;
    private Date lastEdited;
    private Person lastEditedBy;
    private AppointmentFee AppointmentFee;
    private boolean feePaid;

    public AppointmentNote() {
    }

    public AppointmentNote(Appointment appointment, String notes, 
                           Date lastModified, Person lastEdited, 
                           AppointmentFee AppointmentFee, boolean feePaid) {
        this.appointment = appointment;
        this.notes = notes;
        this.lastEdited = lastModified;
        this.lastEditedBy = lastEdited;
        this.AppointmentFee = AppointmentFee;
        this.feePaid = feePaid;
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

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
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

    public boolean isFeePaid() {
        return feePaid;
    }

    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }
    
    public void editNotes(String notes, Person person){
        setNotes(notes);
        setLastEditedBy(person);
        setLastEdited(new Date());
    }
           
    
}
