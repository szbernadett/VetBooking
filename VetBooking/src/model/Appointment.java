/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author igbin
 */
public class Appointment implements Serializable {

    private LocalDate date;
    private String time;
    private Animal animal;
    private Address address;
    private Vet vet;
    private AppointmentType appointmentType;
    private boolean paid;

    public Appointment() {
    }

    public Appointment(LocalDate date, String time, Animal animal, Address address, Vet vet, AppointmentType appointmentType, boolean paid) {
        this.date = date;
        this.time = time;
        this.animal = animal;
        this.address = address;
        this.vet = vet;
        this.appointmentType = appointmentType;
        this.paid=paid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
   

    public enum AppointmentType {
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
    
    public enum TimeSlots{
        ALL(Set.of("08:30","08:45",
                   "09:00", "09:15", "09:30", "09:45", 
                   "10:00",
                   "11:00", "11:15", "11:30", "11:45",
                   "12:30", "12:45",
                   "13:00", "13:15", "13:30", "13:45",
                   "14:00", "14:15", "14:30", "14:45",
                   "15:00", "15:15", "15:30", "15:45",
                   "18:00", "18:15", "18:30", "18:45")),
        STANDARD(Set.of("09:00", "09:15", "09:30", "09:45", 
                "11:00", "11:15", "11:30", "11:45",
                "12:30", "12:45",
                "13:00", "13:15", "13:30", "13:45",
                "14:00", "14:15", "14:30", "14:45",
                "15:00", "15:15", "15:30", "15:45",
                "18:00", "18:15", "18:30", "18:45")),
        EMERGENCY(Set.of("08:30","08:45", "19:00", "19:15")),
        SURGERY(Set.of("10:00"));
        
        private final Set<String> times;

        private TimeSlots(Set<String> times) {
            this.times = times;
        }

        public Set<String> getTimes() {
            return times;
        }
        
        
    }

    public enum AppointmentFee {

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

    public enum MedicationFee {
        ANESTH_GENERAL(200),
        ANESTH_LOCAL(100),
        VACCINE_CAT_1(50),
        VACCINE_CAT_2(100),
        ANTIBIOTICS(30);

        private final int feeAmount;

        private MedicationFee(int feeAmount) {
            this.feeAmount = feeAmount;
        }

        public int getFeeAmount() {
            return feeAmount;
        }

    }

}
