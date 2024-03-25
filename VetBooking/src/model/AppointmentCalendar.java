/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import model.Appointment.TimeSlots;

/**
 *
 * @author igbin
 */
public class AppointmentCalendar {

    HashMap<Vet, TreeMap<LocalDate, TreeSet<String>>> appointmentsByVetsMap;

    public AppointmentCalendar(List<Appointment> appointments) {
        appointmentsByVetsMap = new HashMap<>();
        populateMap(appointments);
    }

    private void populateMap(List<Appointment> appointments) {
        for (Appointment appt : appointments) {
            LocalDate date = appt.getDate();
            String time = appt.getTime();
            Vet vet = appt.getVet();

            appointmentsByVetsMap.putIfAbsent(vet, new TreeMap<>());
            Map<LocalDate, TreeSet<String>> bookedAppointments = appointmentsByVetsMap.get(vet);
            bookedAppointments.putIfAbsent(date, new TreeSet<>());
            Set<String> times = bookedAppointments.get(date);
            times.add(time);

        }
    }

    public List<LocalDate> getWeekdaysInMonth() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(1);
        List<LocalDate> weekdays = new ArrayList<>();
        LocalDate current = startDate;

        while (!current.isAfter(endDate)) {
            DayOfWeek dayOfWeek = current.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                weekdays.add(current);
            }
            current = current.plusDays(1);
        }

        return weekdays;
    }

    

    public Set<String> getFreeTimeSlots(Vet vet, LocalDate date) {
        Set<String> allTimes = TimeSlots.ALL.getTimes();

        TreeMap<LocalDate, TreeSet<String>> vetAppointments = appointmentsByVetsMap.get(vet);
        if (vetAppointments != null) {
            TreeSet<String> bookedSlots = vetAppointments.get(date);
            if (bookedSlots != null) {
                allTimes.removeAll(bookedSlots);
            }
        }

        return allTimes;
    }

}
