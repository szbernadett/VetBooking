
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author igbin
 */
public class Appointment implements Serializable {

    private LocalDate date;
    private String time;
    private Animal animal;
    private String location;
    private Vet vet;
    private AppointmentType appointmentType;
    private boolean paid;
    private static transient final Map<AppointmentType, TimeSlot> apptTypeToTimeSlotMap;
    
    static{
        apptTypeToTimeSlotMap = new HashMap<>();
        apptTypeToTimeSlotMap.put(AppointmentType.STANDARD, TimeSlot.STANDARD);
        apptTypeToTimeSlotMap.put(AppointmentType.SURGERY, TimeSlot.SURGERY);
        apptTypeToTimeSlotMap.put(AppointmentType.EMERGENCY, TimeSlot.EMERGENCY);
        apptTypeToTimeSlotMap.put(AppointmentType.CHECKUP, TimeSlot.STANDARD);
        apptTypeToTimeSlotMap.put(AppointmentType.PRESCRIPTION, TimeSlot.STANDARD);
        apptTypeToTimeSlotMap.put(AppointmentType.VACCINATION, TimeSlot.STANDARD);     
    }

    public Appointment() {
    }

    public Appointment(LocalDate date, String time, Animal animal, String location, Vet vet, AppointmentType appointmentType, boolean paid) {
        this.date = date;
        this.time = time;
        this.animal = animal;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
   

    public static TimeSlot timeSlotByApptType(AppointmentType apptType){
        return apptTypeToTimeSlotMap.get(apptType);
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
        
        public static AppointmentType fromStringValue(String stringValue){
            for(AppointmentType type : AppointmentType.values()){
                if(type.stringValue.equalsIgnoreCase(stringValue)){
                    return type;
                }
            }
            
            throw new IllegalArgumentException("No AppointmentType with this stringValue");
        }

        @Override
        public String toString() {
            return stringValue;

        }

    }
    
    public enum TimeSlot{
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

        private TimeSlot(Set<String> times) {
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
