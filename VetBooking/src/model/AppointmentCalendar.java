
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Appointment.AppointmentType;
import model.Appointment.TimeSlot;

/**
 * AppointmentCalendar class: Represents the calendar of appointments in the veterinary administration system.
 * - bookedVetAppointmentsMap: Map<Vet, Map<LocalDate, Map<TimeSlot, List<String>>>> 
 *   A nested map structure that organises all booked appointments first by vet, then by date and 
 *   finally by time slot type. The time slot type key maps to a list of booked appointment times 
 *   (time slot string values).
 */
public class AppointmentCalendar {

    private static AppointmentCalendar instance;
    private final  Map<Vet, Map<LocalDate, Map<TimeSlot, List<String>>>> bookedVetAppointmentsMap; 
    public static final LocalDate startDate = LocalDate.now();
    public static final LocalDate endDate = startDate.plusMonths(TimeConstraint.MAX_MONTHS_AHEAD.getValue());

    private AppointmentCalendar(List<Appointment> appointments) {
        bookedVetAppointmentsMap = new HashMap<>();
        populateMap(appointments);
    }

    public static AppointmentCalendar getInstance(List<Appointment> appointments){
        if(instance == null){
            instance = new AppointmentCalendar(appointments);
        }
        return instance;
    }

    public Map<Vet, Map<LocalDate, Map<TimeSlot, List<String>>>> getBookedVetAppointmentsMap() {
        return bookedVetAppointmentsMap;
    } 

    /**
     *  Populates the bookedVetAppointmentsMap with the appointments in the list. 
     *  The map is organised first by vet, then by date and finally by time slot type.
     *  The time slot type key maps to a list of booked appointment times (time slot string values).
     *  
     * 
     *  @param appointments List<Appointment> The list of appointments to populate the map with. 
     *  @return void                              
     *  @see Appointment
     *  @see Vet
     *  @see LocalDate
     *  @see TimeSlot
     *  @see AppointmentType
     *  @see AppointmentCalendar
     *  
     */

    private  void populateMap(List<Appointment> appointments) {
        for (Appointment appt : appointments) {
            LocalDate date = appt.getDate();
            String time = appt.getTime();
            Vet vet = appt.getVet();
            TimeSlot timeSlotType = Appointment.timeSlotByApptType(appt.getAppointmentType());

            bookedVetAppointmentsMap.putIfAbsent(vet, new HashMap<>());
            Map<LocalDate, Map<TimeSlot, List<String>>> datesMap = bookedVetAppointmentsMap.get(vet);
            datesMap.putIfAbsent(date, new HashMap<>());
            Map<TimeSlot, List<String>> timeSlotMap = datesMap.get(date);
            timeSlotMap.putIfAbsent(timeSlotType, new ArrayList<>());
            List<String> times = timeSlotMap.get(timeSlotType);
            times.add(time);
        }
    }

    /**
     * Adds an appointment to the bookedVetAppointmentsMap.
     * @param appointment Appointment The appointment to add to the map.
     * @return void
     * @see Appointment
     * @see Vet
     * @see LocalDate
     * @see TimeSlot
     * @see AppointmentType
     * @see AppointmentCalendar
     */
    
    public void addAppointmentToMap(Appointment appointment){
        Vet vet = appointment.getVet();
        LocalDate date = appointment.getDate();
        AppointmentType apptType = appointment.getAppointmentType();
        TimeSlot timeSlot = Appointment.timeSlotByApptType(apptType);
        String time = appointment.getTime();
        
        bookedVetAppointmentsMap.putIfAbsent(vet, new HashMap<>());
        bookedVetAppointmentsMap.get(vet)
                                .putIfAbsent(date, new HashMap<>());
        bookedVetAppointmentsMap.get(vet)
                                .get(date)
                                .putIfAbsent(timeSlot, new ArrayList<>());
        bookedVetAppointmentsMap.get(vet).get(date).get(timeSlot).add(time);
    }
    /**
     * Finds free time slots for a specific vet on a specific date for a specific appointment type.
     * 
     * @param vet The selected vet
     * @param date The selected date
     * @param apptType The selected appointment type
     * @return The sorted list of free time slots for the vet on the date and appointment type.
     * @see Vet
     * @see LocalDate
     * @see AppointmentType
     * @see AppointmentCalendar
     * @see TimeSlot
     * @see Appointment
     * 
     */

    public  List<String> getFreeTimeSlots(Vet vet, LocalDate date, AppointmentType apptType) {
        List<String> allTimes;
        if (vet != null && date != null && apptType != null) {
            TimeSlot timeSlotType = Appointment.timeSlotByApptType(apptType);
            allTimes = new ArrayList<>(timeSlotType.getTimes());
            Map<LocalDate, Map<TimeSlot, List<String>>> apptsByDate = bookedVetAppointmentsMap.get(vet);
            if (apptsByDate != null) {
                Map<TimeSlot, List<String>> apptsByTimeSlot = apptsByDate.get(date);
                if (apptsByTimeSlot != null) {
                    List<String> bookedTimes = apptsByTimeSlot.get(timeSlotType);
                    System.out.println("bookedTimes " + bookedTimes);
                    System.out.println("allTimes " + allTimes);
                    allTimes.removeAll(bookedTimes);
                }
            }
        } else {
            allTimes = new ArrayList<>();
        }
        if(!allTimes.isEmpty()){
            Collections.sort(allTimes);
        } 
        return allTimes;
    }
    
    /**
     * Represents time constraints related to appointment booking. 
     * - MAX_MONTHS_AHEAD: The maximum number of months ahead that an appointment can be booked.
     *                     Appoinments can be booked in a time frame from and including the current 
     *                     date to as many months ahead as this value allows. It is used to set the
     *                     end date for the appointment booking period.
     * 
     * @see AddNewAppointmentController
     * @see EditaAppointmentController
     */
    public enum TimeConstraint{
        MAX_MONTHS_AHEAD(1);
        
        private final int value;

        private TimeConstraint(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        
        
    }
    

}
