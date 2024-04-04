/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
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
import model.AppointmentCalendar;
import model.DAO;
import view.AddAppointmentWindow;

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
    private List<Vet> listViewVets;
    private List<String> cBoxTimeSlots;
    private Vet selectedVet;
    private String location;
    private AppointmentType selectedAppointmentType;
    private LocalDate selectedDate;
    private String selectedTime;

    public AddAppointmentWindowController(AddAppointmentWindow view, DAO model, AppointmentCalendar calendar) {
        super(view, model);
        this.calendar = calendar;
        initLists();
        dataToView();
        setupNameSearch();
        setDataChangeHandlers();
    }

    @Override
    protected final void setDataChangeHandlers() {
        view.getAnimalSearchTextField().addEventHandler(KeyEvent.KEY_RELEASED, this::filterAnimals);
        view.getTimeCbox().addEventHandler(ActionEvent.ACTION, this::timeSelected);
        view.getClearAllBtn().addEventHandler(ActionEvent.ACTION, this::resetView);
        view.getCancelBtn().addEventHandler(ActionEvent.ACTION, this::closeWithoutSave);
        view.getSaveBtn().addEventHandler(ActionEvent.ACTION, this::saveAppointment);
        view.setOnCloseRequest(this::closeWithoutSave);
        view.getFilteredAnimalsListView()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(this::animalSelected);
        view.getVetListView()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(this::vetSelected);
        view.getApptTypeToggleGroup()
                .selectedToggleProperty()
                .addListener(this::apptTypeSelected);
        view.getApptDatePicker()
                .valueProperty()
                .addListener(this::dateSelected);

    }

    public AppointmentCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(AppointmentCalendar calendar) {
        this.calendar = calendar;
    }

    public Set<String> getAnimalNames() {
        return animalNames;
    }

    public void setAnimalNames(Set<String> animalNames) {
        this.animalNames = animalNames;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Animal> getListViewAnimals() {
        return listViewAnimals;
    }

    public void setListViewAnimals(List<Animal> listViewAnimals) {
        this.listViewAnimals = listViewAnimals;
    }

    public Map<String, Animal> getAnimalNamesMap() {
        return animalNamesMap;
    }

    public void setAnimalNamesMap(Map<String, Animal> animalNamesMap) {
        this.animalNamesMap = animalNamesMap;
    }

    public Animal getSelectedAnimal() {
        return selectedAnimal;
    }

    public void setSelectedAnimal(Animal selectedAnimal) {
        this.selectedAnimal = selectedAnimal;
    }

    public List<Vet> getVets() {
        return vets;
    }

    public void setVets(List<Vet> vets) {
        this.vets = vets;
    }

    public List<Vet> getListViewVets() {
        return listViewVets;
    }

    public void setListViewVets(List<Vet> listViewVets) {
        this.listViewVets = listViewVets;
    }

    public List<String> getcBoxTimeSlots() {
        return cBoxTimeSlots;
    }

    public void setcBoxTimeSlots(List<String> cBoxTimeSlots) {
        this.cBoxTimeSlots = cBoxTimeSlots;
    }

    public Vet getSelectedVet() {
        return selectedVet;
    }

    public void setSelectedVet(Vet selectedVet) {
        this.selectedVet = selectedVet;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public AppointmentType getSelectedAppointmentType() {
        return selectedAppointmentType;
    }

    public void setSelectedAppointmentType(AppointmentType selectedAppointmentType) {
        this.selectedAppointmentType = selectedAppointmentType;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }

    private void initLists() {
        try {
            animals = model.getAllAnimals();
            listViewAnimals = FXCollections.observableArrayList(animals);
            vets = model.getAllVets();
            listViewVets = FXCollections.observableArrayList();
            cBoxTimeSlots = FXCollections.observableArrayList();
        } catch (ClassNotFoundException | IOException ex) {
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

    @Override
    protected final void dataToView() {
        if (listViewAnimals != null) {
            view.getFilteredAnimalsListView().setItems((ObservableList) listViewAnimals);
            view.getFilteredAnimalsListView().setEditable(false); // test if this is needed
            view.getVetListView().setItems((ObservableList) listViewVets);
        }

        view.getApptDatePicker().setDayCellFactory(getCustomDayCellFactory(AppointmentCalendar.startDate, AppointmentCalendar.endDate));
    }

    private Callback<DatePicker, DateCell> getCustomDayCellFactory(LocalDate startDate, LocalDate endDate) {
        return (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(startDate) || item.isAfter(endDate)) {
                    setDisable(true);
                } else {
                    if (item.getDayOfWeek() == DayOfWeek.SATURDAY
                            || item.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        setDisable(true);
                    }
                }
            }
        };
    }

    private void filterAnimals(KeyEvent event) {

        if (event.getCode() == KeyCode.BACK_SPACE) {
            listViewAnimals.clear();
            listViewAnimals.addAll(animals);
        } else {
            String text = view.getAnimalSearchTextField().getText().toLowerCase();

            for (String name : animalNames) {
                if (!name.toLowerCase().contains(text)) {
                    listViewAnimals.remove(animalNamesMap.get(name));
                }
            }
        }
    }

    private void filterVets() {
        listViewVets.clear();
        for (Vet vet : vets) {
            if (vet.getSpecialistCategories().containsAll(selectedAnimal
                    .getAnimalType()
                    .getSpecialistCategories())) {
                listViewVets.add(vet);
            }
        }
    }

    private void filterTimeSlots() {
        RadioButton rb = (RadioButton) view.getApptTypeToggleGroup().getSelectedToggle();
        if (rb != null) {
            String buttonText = rb.getText();
            selectedAppointmentType = AppointmentType.fromStringValue(buttonText);
            switch (selectedAppointmentType) {
                case AppointmentType.SURGERY -> {
                    cBoxTimeSlots = FXCollections
                            .observableArrayList(calendar.getFreeTimeSlots(selectedVet, selectedDate, selectedAppointmentType));
                    view.getTimeCbox().setItems((ObservableList) cBoxTimeSlots);
                }
                case AppointmentType.EMERGENCY -> {
                    cBoxTimeSlots = FXCollections
                            .observableArrayList(calendar.getFreeTimeSlots(selectedVet, selectedDate, selectedAppointmentType));
                    view.getTimeCbox().setItems((ObservableList) cBoxTimeSlots);
                }
                default -> {
                    cBoxTimeSlots = FXCollections
                            .observableArrayList(calendar.getFreeTimeSlots(selectedVet, selectedDate, selectedAppointmentType));
                    view.getTimeCbox().setItems((ObservableList) cBoxTimeSlots);
                }

            }
        }
    }

    private void animalSelected(ObservableValue<? extends Object> observable,
            Object oldValue,
            Object newValue) {
        if (newValue != null) {
            selectedAnimal = (Animal) newValue;
            view.getSelectedAnimalNameLbl().setText(selectedAnimal.toString());
            filterVets();
            if (selectedAnimal.getAddress().getLocationType() == LocationType.DOMESTIC) {
                location = LocationType.VET_OFFICE.toString();
            } else {
                location = selectedAnimal.getAddress().toString();
            }
            view.getLocationValueLabel().setText(location);
        }
    }

    private void vetSelected(ObservableValue<? extends Object> observable,
            Object oldValue,
            Object newValue) {
        if (newValue != null) {
            selectedVet = (Vet) newValue;
            view.getApptTypeToggleGroup().selectToggle(null);
            cBoxTimeSlots.clear();
            view.getApptDatePicker().setValue(null);
            view.getSelectedVetNameLbl().setText(selectedVet.toString());
        }
    }

    private void apptTypeSelected(
            ObservableValue<? extends Object> observable,
            Object oldValue,
            Object newValue) {
        if (newValue != null) {
            filterTimeSlots();
        }
    }

    private void dateSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {
        selectedDate = (LocalDate) newValue;
        filterTimeSlots();
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
            alert.show();
        } else {
            Appointment appointment = new Appointment(selectedDate,
                    selectedTime,
                    selectedAnimal,
                    location,
                    selectedVet,
                    selectedAppointmentType,
                    false);
            model.saveAppointment(appointment);
            calendar.addAppointmentToMap(appointment);
            resetView();
            Alert alert = saveSuccessAlert(POJOName.APPOINTMENT);
            alert.show();
            view.close();

        }
    }

    private void resetSelectedValues() {
        selectedAnimal = null;
        selectedDate = null;
        selectedDate = null;
        selectedVet = null;
        selectedAppointmentType = null;
    }

    private void resetView() {
        resetSelectedValues();
        view.getAnimalSearchTextField().setText("");
        listViewAnimals.clear();
        listViewAnimals.addAll(animals);
        listViewVets.clear();
        view.getSelectedAnimalNameLbl().setText("");
        view.getSelectedVetNameLbl().setText("");
        view.getApptTypeToggleGroup().selectToggle(null);
        view.getApptDatePicker().setValue(null);
        cBoxTimeSlots.clear();

    }

    private void resetView(ActionEvent event) {
        resetView();
    }


}
