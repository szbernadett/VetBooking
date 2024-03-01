/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author igbin
 */
public class Appointment {
    
    private Date date;
    private Animal animal;
    private Address address;
    private Vet vet;
    private AppointmentType appointmentType;

    public Appointment() {
    }

    public Appointment(Date date, Animal animal, Address address, Vet vet, AppointmentType appointmentType) {
        this.date = date;
        this.animal = animal;
        this.address = address;
        this.vet = vet;
        this.appointmentType = appointmentType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }
    
    
    
    
    public enum AppointmentType{
        STANDARD("standard"), 
        CHECKUP("checkup"), 
        PRESCRIPTION("prescription"), 
        EMERGENCY("emergency"), 
        VACCINATION("vaccination"), 
        SURGERY("surgery");
        
        private final String stringValue;

        private AppointmentType(String stringValue) {
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
          
        }
        
        
        
    }
    
    public enum AppointmentFee{
        
        PRESCRIPTION(10),
        CALLOUT(20),
        BASE(50),
        EMERGENCY(100),
        MINOR_SURGERY(150),
        MAJOR_SURGERY(300);
        
        private final int feeAmount;

        private AppointmentFee(int feeAmount) {
            this.feeAmount = feeAmount;
        }

        public int getFeeAmount() {
            return feeAmount;
        }       
        
    }
    
    public enum MedicationFee{
        ANESTH_GENERAL(200),
        ANESTH_LOCAL(100),
        VACCINE_CAT_1(50),
        VACCINE_CAT_2(100),
        ANTIBIOTICS(30);
        
        
        private final int feeAmount;
        
        private MedicationFee(int feeAmount){
            this.feeAmount = feeAmount;
        }

        public int getFeeAmount() {
            return feeAmount;
        }
        
        
    }
    
}
