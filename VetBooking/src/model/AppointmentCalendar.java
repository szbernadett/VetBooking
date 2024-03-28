/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import model.Appointment.AppointmentType;
import model.Appointment.TimeSlot;

/**
 *
 * @author igbin
 */
public class AppointmentCalendar {

    private Map<Vet, Map<LocalDate, Map<TimeSlot, Set<String>>>> bookedVetAppointmentsMap;
    public static final LocalDate startDate = LocalDate.now();
    public static final LocalDate endDate = startDate.plusMonths(1);

    public AppointmentCalendar(List<Appointment> appointments) {
        bookedVetAppointmentsMap = new HashMap<>();
        populateMap(appointments);
    }

    public Map<Vet, Map<LocalDate, Map<TimeSlot, Set<String>>>> getBookedVetAppointmentsMap() {
        return bookedVetAppointmentsMap;
    }

    public void setBookedVetAppointmentsMap(Map<Vet, Map<LocalDate, Map<TimeSlot, Set<String>>>> bookedVetAppointmentsMap) {
        this.bookedVetAppointmentsMap = bookedVetAppointmentsMap;
    }

    private void populateMap(List<Appointment> appointments) {
        for (Appointment appt : appointments) {
            LocalDate date = appt.getDate();
            String time = appt.getTime();
            Vet vet = appt.getVet();
            TimeSlot timeSlotType = Appointment.timeSlotByApptType(appt.getAppointmentType());

            bookedVetAppointmentsMap.putIfAbsent(vet, new HashMap<>());
            Map<LocalDate, Map<TimeSlot, Set<String>>> datesMap = bookedVetAppointmentsMap.get(vet);
            datesMap.putIfAbsent(date, new HashMap<>());
            Map<TimeSlot, Set<String>> timeSlotMap = datesMap.get(date);
            timeSlotMap.putIfAbsent(timeSlotType, new TreeSet<>());
            Set<String> times = timeSlotMap.get(timeSlotType);
            times.add(time);
        }
    }

    public Set<String> getFreeTimeSlots(Vet vet, LocalDate date, AppointmentType apptType) {
        Set<String> allTimesForSlot;
        if (vet != null && date != null && apptType != null) {
            TimeSlot timeSlotType = Appointment.timeSlotByApptType(apptType);
            allTimesForSlot = timeSlotType.getTimes();
            Map<LocalDate, Map<TimeSlot, Set<String>>> apptsByDate = bookedVetAppointmentsMap.get(vet);
            if (apptsByDate != null) {
                Map<TimeSlot, Set<String>> apptsByTimeSlot = apptsByDate.get(date);
                if (apptsByTimeSlot != null) {
                    Set<String> bookedTimes = apptsByTimeSlot.get(timeSlotType);
                    allTimesForSlot.removeAll(bookedTimes);
                }
            }
        } else {
            allTimesForSlot = new HashSet<>();
        }
        return allTimesForSlot;
    }
}
