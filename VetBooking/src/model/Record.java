/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author igbin
 */
public class Record implements Serializable {
    
    private LocalDate dateRegistered;
    private Animal animal;
    private String medicalHistory;
    private ArrayList<AppointmentNote> appointmentNotes;

    public Record() {
    }

    public Record(LocalDate dateRegistered, Animal animal, String medicalHistory, 
                  ArrayList<AppointmentNote> appointmentNotes) {
        this.dateRegistered = dateRegistered;
        this.animal = animal;
        this.medicalHistory = medicalHistory;
        this.appointmentNotes = appointmentNotes;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
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
