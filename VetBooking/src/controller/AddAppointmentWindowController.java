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
 * 
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

    /**
     * Method to set the event handlers for the view components
     */
    @Override
    protected final void setDataChangeHandlers() {
        view.getAnimalSearchTextField().addEventHandler(KeyEvent.KEY_RELEASED, this::filterAnimals);
        view.getTimeCbox().addEventHandler(ActionEvent.ACTION, this::timeSelected);
        view.getClearAllBtn().addEventHandler(ActionEvent.ACTION, this::resetView);
        view.getCancelBtn().addEventHandler(ActionEvent.ACTION, this::closeWithoutSave);
        view.getSaveBtn().addEventHandler(ActionEvent.ACTION, this::saveAppointment);
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

    @Override
    protected final void dataToView() {
        if (listViewAnimals != null) {
            view.getFilteredAnimalsListView().setItems((ObservableList) listViewAnimals);
            view.getFilteredAnimalsListView().setEditable(false);
            view.getVetListView().setItems((ObservableList) listViewVets);
        }

        view.getApptDatePicker().setDayCellFactory(getCustomDayCellFactory(AppointmentCalendar.startDate, AppointmentCalendar.endDate));
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

    /**
     * Initialise the lists of animals, vets and time slots. Only the listview holding the animals is
     * populated in this method, the other elements are initialised with empty observable arraylists
     * to be populated based on selections made by the user.
     */
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

    /**
     * Collect all animal names from the animals list previously fetched from the data source.
     * Create and populate a map whose keys are the animal identifier (name or numerical value)
     * and values are the object references of the animals themselves.
     * When the user selects an animal name from the listview, the corresponding object reference
     * from the map will be assigned to the selectedAnimal field.
     * 
     */
    private void setupNameSearch() {
        if (animals != null) {
            animalNames = animals.stream().map(Animal::getIdentifier).collect(Collectors.toSet());
            animalNamesMap = new HashMap<>();
            for (Animal animal : animals) {
                animalNamesMap.put(animal.getIdentifier(), animal);
            }
        }
    }

    /**
     * Create a custom day cell factory for the date picker. The factory creates a DateCell which is disabled
     * if it is outside the range of the start and end dates passed as arguments or if it falls on a weekend.
     * @param startDate The start of the allowed date range
     * @param endDate The end of the allowed date range
     * @return Callback<DatePicker, DateCell> A callback that creates a DateCell 
     */

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

    /**
     * Filter the list of animal names based on the text entered in the search textfield. 
     * Animal names that do not match the search text are removed from the observable list of the listview.
     * If the backspace key is pressed, the list is cleared and all animals are added back to the
     * observable list of the listview.
     * @param event The key event that triggers the filtering
     */
    private void filterAnimals(KeyEvent event) {

        if (event.getCode() == KeyCode.BACK_SPACE) {
            listViewAnimals.clear();
            listViewAnimals.addAll(animals);
        } else {
            String text = view.getAnimalSearchTextField().getText().toLowerCase(); // prepare string

            for (String name : animalNames) {
                if (!name.toLowerCase().contains(text)) {
                    listViewAnimals.remove(animalNamesMap.get(name));
                }
            }
        }
    }

    /**
     * Filter the list of vets based on the selected animal's specialist categories. Only vets who
     * specialise in all the categories of the selected animal are added to the observable list of the
     * vet listview.
     *
     */
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

    /**
     * Find available time slots and populate the time slots combobox with them. 
     * Available time slots are identified based on the selected vet, date and appointment type.
     */
    private void filterTimeSlots() {
        view.getTimeCbox().setValue(null);
        cBoxTimeSlots = FXCollections
                .observableArrayList(calendar.getFreeTimeSlots(selectedVet, selectedDate, selectedAppointmentType));
        view.getTimeCbox().setItems((ObservableList) cBoxTimeSlots);
    }

    /**
     * Assign the selected animal to the selectedAnimal field, display the selected animal name and appointment location in the view.
     * The location of the appointment is determined by the selected animal's address. If the LocationType
     * of the address LocationType.DOMESTIC, the location of the appointment will be the vet surgery. Otherwise,
     * the location will be the address of the selected animal.
     * @param observable the object that wraps the currently selected animal through which other objects can listen to changes
     * @param oldValue the previously selected animal
     * @param newValue the currently selected animal 
     */

    private void animalSelected(ObservableValue<? extends Animal> observable,
            Object oldValue,
            Object newValue) {
        if (newValue != null) {
            selectedAnimal = (Animal) newValue;
            view.getSelectedAnimalNameLbl().setText(selectedAnimal.toString());
            filterVets();
            if (selectedAnimal.getAddress().getLocationType() == LocationType.DOMESTIC) {
                location = LocationType.VET_SURGERY.toString();
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
        RadioButton rb = (RadioButton) newValue;
        if (rb != null) {
            String buttonText = rb.getText();
            selectedAppointmentType = AppointmentType.fromStringValue(buttonText);
            filterTimeSlots();
        }
    }

    private void dateSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {
        selectedDate = (LocalDate) newValue;
        if (selectedDate != null) {
            filterTimeSlots();
        }
    }

    private void timeSelected(ActionEvent event) {
        selectedTime = (String) view.getTimeCbox().getValue();
    }

    private Validate checkUnsetValues() {
        Validate result = Validate.OK;
        if (selectedAnimal == null
                || selectedVet == null
                || selectedDate == null
                || selectedTime == null) {

            result = Validate.FAIL;
        }
        return result;
    }

    private void saveAppointment(ActionEvent event) {

        if (checkUnsetValues() == Validate.OK) {
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

        } else {

            Alert alert = missingInfoAlert("Please select an animal, vet, date, appointment type and time");
            alert.show();
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
