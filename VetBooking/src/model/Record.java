/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author igbin
 */
public class Record {
    
    private Date dateRegistered;
    private Animal animal;
    private String medicalHistory;
    private ArrayList<AppointmentNote> appointmentNotes;

    public Record() {
    }

    public Record(Date dateRegistered, Animal animal, String medicalHistory, 
                  ArrayList<AppointmentNote> appointmentNotes) {
        this.dateRegistered = dateRegistered;
        this.animal = animal;
        this.medicalHistory = medicalHistory;
        this.appointmentNotes = appointmentNotes;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public ArrayList<AppointmentNote> getAppointmentNotes() {
        return appointmentNotes;
    }

    public void setAppointmentNotes(ArrayList<AppointmentNote> appointmentNotes) {
        this.appointmentNotes = appointmentNotes;
    }
    
    
    
    
    
}
