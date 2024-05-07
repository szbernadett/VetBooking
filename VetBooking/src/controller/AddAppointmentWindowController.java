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
 * Controller class for the AddAppointmentWindow view.
 * 
 * -  view The view component of the AddAppointmentWindow
 * -  model The data access object used to fetch and save data
 * -  calendar The appointment calendar used to manage appointments
 * -  animalNames A set of animal names fetched from the data source
 * -  animals A list of all animals fetched from the data source
 * -  listViewAnimals An observable list of animals to be displayed in the listview
 * -  animalNamesMap A map of animal names and their corresponding animal objects
 * -  selectedAnimal The animal selected by the user
 * -  vets A list of all vets fetched from the data source
 * -  listViewVets An observable list of vets to be displayed in the listview
 * -  cBoxTimeSlots An observable list of time slots to be displayed in the combobox
 * -  selectedVet The vet selected by the user
 * -  location The location of the appointment
 * -  selectedAppointmentType The appointment type selected by the user
 * -  selectedDate The date selected by the user
 * -  selectedTime The time slot selected by the user
 * -  calendar The AppointmentCalendar used to manage appointments
 * 
 * @see Controller
 * @see AddAppointmentWindow
 * @see AppointmentCalendar
 * @see Animal
 * @see Vet
 * @see Appointment
 * @see DAO
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

    /**
     * Constructor for the AddAppointmentWindowController class. Calls the superclass constructor 
     * and initialises the lists of animals, vets and time slots. Sets up the animal name search 
     * functionality and assigns data change handlers to the view components.
     * 
     * @param view The view component of the AddAppointmentWindow
     * @param model The data access object used to fetch and save data
     * @param calendar The appointment calendar used to manage appointments
     */
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
     * @return void
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
     * @return void
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
     * @return void
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
     * @return void
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
     * @return void
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
     * @param observable the object that wraps the currently selected animal through which other 
     *                   objects can listen to changes 
     * @param oldValue the previously selected animal
     * @param newValue the currently selected animal 
     * @return void
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

    /**
     * Assign the selected vet to the selectedVet field and display the selected vet's name in the view.
     * Clear the appointment type selection, the date picker and the time slots combobox.
     *
     * @param observable the object that wraps the currently selected vet through which other objects can listen to changes
     * @param oldValue the previously selected vet
     * @param newValue the currently selected vet
     * @return void
     */
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

    /**
     * Assign the selected appointment type to the selectedAppointmentType field and filter the time slots.
     * 
     * @param observable the object that wraps the currently selected appointment type through which other objects can listen to changes
     * @param oldValue the previously selected appointment type
     * @param newValue the currently selected appointment type
     * @return void
     */
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

    /**
     * Assign the selected date to the selectedDate field and filter the time slots.
     * 
     * @param observable the object that wraps the currently selected date through which other objects can listen to changes
     * @param oldValue the previously selected date
     * @param newValue the currently selected date
     */

    private void dateSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {
        selectedDate = (LocalDate) newValue;
        if (selectedDate != null) {
            filterTimeSlots();
        }
    }

    /**
     * Assign the selected tim slot value to the selectedTime field.
     * 
     * @param event The event of the user selecting an item form the time slots combobox
     * @return void
     */
    private void timeSelected(ActionEvent event) {
        selectedTime = (String) view.getTimeCbox().getValue();
    }

    /**
     * Check if all required fields are set before saving the appointment. 
     * If any of the required fields are not set, an alert is shown to the user.
     * 
     * @return Validate.OK if all required fields are set, Validate.FAIL otherwise
     * @see Validate    
     */
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

    /**
     * Check if all required fields are set before saving the appointment. If all required fields
     * are set, create a new Appoitment object and call the model's appropriate method to save the 
     * appointment. Add the appointment to the calendar's map of appointments and show an alert to 
     * the user to confirm that the appointment has been saved successfully.
     * If not all required fields are set, show an alert to the user to inform them that they need to
     * select an animal, vet, date, appointment type and time to save the create and save the 
     * appointment.
     * 
     * @param event the event created by the user clicking the Save button
     * @return void
     */
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
            Alert alert = saveSuccessAlert(POJOName.APPOINTMENT);
            alert.show();
            view.close();

        } else {

            Alert alert = missingInfoAlert("Please select an animal, vet, date, appointment type and time");
            alert.show();
        }
    }



    /**
     *  Reset view components to their initial state. Clears all user selected values from the
     *  view. Fileds that hold user input whose values are not set by listeners or event handlers 
     *  are explicitly set back to their initial values.
     *  @return void
     */
    private void resetView(ActionEvent event) {
        view.getAnimalSearchTextField().setText("");
        listViewAnimals.clear();
        listViewAnimals.addAll(animals);
        listViewVets.clear();
        view.getSelectedAnimalNameLbl().setText("");
        view.getSelectedVetNameLbl().setText("");
        view.getApptTypeToggleGroup().selectToggle(null);
        view.getApptDatePicker().setValue(null);
        cBoxTimeSlots.clear();
        selectedAnimal = null;
        selectedVet = null;
        selectedTime = "";

    }

}
