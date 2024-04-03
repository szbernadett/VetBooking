/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author igbin
 */
public class AppointmentCalendar {

    private static AppointmentCalendar instance;
    private final  Map<Vet, Map<LocalDate, Map<TimeSlot, List<String>>>> bookedVetAppointmentsMap; 
    public static final LocalDate startDate = LocalDate.now();
    public static final LocalDate endDate = startDate.plusMonths(1);

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

    public  List<String> getFreeTimeSlots(Vet vet, LocalDate date, AppointmentType apptType) {
        List<String> allTimesForSlot;
        if (vet != null && date != null && apptType != null) {
            TimeSlot timeSlotType = Appointment.timeSlotByApptType(apptType);
            allTimesForSlot = new ArrayList<>(timeSlotType.getTimes());
            Map<LocalDate, Map<TimeSlot, List<String>>> apptsByDate = bookedVetAppointmentsMap.get(vet);
            if (apptsByDate != null) {
                Map<TimeSlot, List<String>> apptsByTimeSlot = apptsByDate.get(date);
                if (apptsByTimeSlot != null) {
                    List<String> bookedTimes = apptsByTimeSlot.get(timeSlotType);
                    allTimesForSlot.removeAll(bookedTimes);
                }
            }
        } else {
            allTimesForSlot = new ArrayList<>();
        }
        if(!allTimesForSlot.isEmpty()){
            Collections.sort(allTimesForSlot);
        }
        return allTimesForSlot;
    }
}
