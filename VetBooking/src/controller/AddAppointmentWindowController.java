/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import model.Address.LocationType;
import model.Animal;
import model.Appointment;
import model.Appointment.AppointmentType;
import model.Vet;
import view.AddAppointmentWindow;
import model.AppointmentCalendar;
import model.DAO;

/**
 *
 * @author igbin
 */
public class AddAppointmentWindowController extends Controller<AddAppointmentWindow> {

    private AppointmentCalendar calendar;
    private Set<String> animalNames;
    private List<Animal> animals;
    private List<Animal> listViewAnimals;
    private Map<String, Animal> animalNamesMap;
    private Animal selectedAnimal;
    private List<Vet> vets;
    private List<Vet> comboBoxVets;
    private Vet selectedVet;
    private String location;
    private AppointmentType appointmentType;
    private LocalDate selectedDate;
    private String selectedTime;

    public AddAppointmentWindowController(AddAppointmentWindow view, DAO model, AppointmentCalendar calendar) {
        super(view, model);
        this.calendar = calendar;
        initLists();
        setupView();
        setupNameSearch();
        setupEventHandlers();
        addListeners();
    }

    private void initLists() {
        try{
        animals = model.getAllAnimals();
        listViewAnimals = FXCollections.observableArrayList(animals);
        vets = model.getAllVets();
        comboBoxVets = FXCollections.observableArrayList(vets);
        }catch(ClassNotFoundException | IOException ex){
            System.out.println(ex);
        }
    }

    private void setupNameSearch() {
        if (animals != null) {
            animalNames = animals.stream().map(Animal::getIdentifier).collect(Collectors.toSet());
            animalNamesMap = new HashMap<>();
            for (Animal animal : animals) {
                animalNamesMap.put(animal.getIdentifier(), animal);
            }
        }
    }

    private void setupView() {
        if (listViewAnimals != null) {
            view.getFilteredAnimalsListView().setItems((ObservableList) listViewAnimals);
            view.getFilteredAnimalsListView().setEditable(false); // test if this is needed
        }

        view.getApptDatePicker().setDayCellFactory(getCustomDayCellFactory
        (AppointmentCalendar.startDate, AppointmentCalendar.endDate));
    }

    private Callback<DatePicker, DateCell> getCustomDayCellFactory(LocalDate startDate, LocalDate endDate) {
        return (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(startDate) || item.isAfter(endDate)) {
                    setDisable(true);
                }
            }
        };
    }

    private void filterAnimals(KeyEvent event) {

        if (event.getCode() == KeyCode.BACK_SPACE) {
            listViewAnimals.clear();
            listViewAnimals.addAll(animals);
        } else {
            String text = view.getAnimalValueTextField().getText();

            for (String name : animalNames) {
                if (!name.contains(text)) {
                    listViewAnimals.remove(animalNamesMap.get(name));
                }
            }
        }
    }

    @Override
    protected final void setupEventHandlers() {
        view.setEventHandler(view.getAnimalValueTextField(), KeyEvent.KEY_RELEASED, this::filterAnimals);
        view.setEventHandler(view.getVetCBox(), ActionEvent.ACTION, this::vetSelected);
        view.setEventHandler(view.getTimeCbox(), ActionEvent.ACTION, this::timeSelected);

    }

    private void addListeners() {
        view.getFilteredAnimalsListView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedAnimal = animals.get(animals.indexOf((Animal) newValue));
            filterVets();
            if(selectedAnimal.getAddress().getLocationType() == LocationType.DOMESTIC){
                location=LocationType.VET_OFFICE.toString();
            } else {
                location = selectedAnimal.getAddress().toString();
            }
        });

        view.getApptTypeToggleGroup().selectedToggleProperty()
                .addListener((observable, olValue, newValue) -> {
                    if (newValue != null) {
                        Set<String> times;
                        RadioButton rb = (RadioButton) newValue;
                        String buttonText = rb.getText();
                        appointmentType = AppointmentType.fromStringValue(buttonText);
                        switch (appointmentType) {
                            case AppointmentType.SURGERY -> {
                                times = calendar.getFreeTimeSlots(selectedVet, selectedDate, appointmentType);
                                view.getTimeCbox().setItems(FXCollections.observableArrayList(times));
                            }
                            case AppointmentType.EMERGENCY -> {
                                times = calendar.getFreeTimeSlots(selectedVet, selectedDate, appointmentType);
                                view.getTimeCbox().setItems(FXCollections.observableArrayList(times));
                            }
                            default -> {
                                times = calendar.getFreeTimeSlots(selectedVet, selectedDate, appointmentType);
                                view.getTimeCbox().setItems(FXCollections.observableArrayList(times));
                            }
                        }
                    }
                }
                );

        view.getApptDatePicker().valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedDate = newValue;
        });

    }

    private void filterVets() {
        comboBoxVets.clear();
        for (Vet vet : comboBoxVets) {
            if (vet.getSpecialistCategories().containsAll(selectedAnimal
                    .getAnimalType()
                    .getSpecialistCategories())) {
                comboBoxVets.add(vet);
            }
        }
    }

    private void vetSelected(ActionEvent event) {
        selectedVet = (Vet) view.getVetCBox().getValue();
    }

    private void timeSelected(ActionEvent event) {
        selectedTime = (String) view.getTimeCbox().getValue();
    }

    private void saveAppointment(ActionEvent event) {

        if (selectedAnimal == null
                || selectedVet == null
                || selectedDate == null
                || selectedTime == null) {

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please select an animal, vet, date, appointment type and time");
            alert.showAndWait();
        } else {
            Appointment appointment = new Appointment(selectedDate, 
                                                      selectedTime, 
                                                      selectedAnimal, 
                                                      location, 
                                                      selectedVet,
                                                      appointmentType,
                                                      false);
            model.saveAppointment(appointment);

        }
    }
    
    private void resetView(){
        view.getAnimalValueTextField().setText("");
        listViewAnimals.clear();
        listViewAnimals.addAll(animals);
        comboBoxVets.clear();
        
    }
}
