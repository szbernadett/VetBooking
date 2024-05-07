package controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.util.Callback;
import model.Animal;
import model.Appointment;
import model.Appointment.AppointmentType;
import model.AppointmentCalendar;
import model.DAO;
import model.Vet;
import view.EditAppointmentWindow;

/**
 * A controller class for the EditAppointmentWindow view. 
 * 
 * - view the view to be controlled
 * - model the DAO implementation that communicates with the data source
 * - calendar the AppointmentCalendar object that manages appointment related data
 * - appointment the appointment to be edited
 * - mainWinController the controller for the main window
 * - selectedAnimal the animal selected for the appointment
 * - selectedVet the vet selected for the appointment
 * - selectedAppointmentType the appointment type selected for the appointment
 * - selectedDate the date selected for the appointment
 * - selectedTime the time selected for the appointment
 * - isPaid the payment status of the appointment
 * - allAppointments a list of all appointments
 * - cBoxTimeSlots a list of available time slots
 * - vets a list of all vets
 * - listViewVets a list of vets filtered by animal type
 * - listViewAnimals a list of all animals
 * 
 * @see EditAppointmentWindow
 * @see Controller
 * @see DAO
 * @see AppointmentCalendar
 * @see Appointment
 * @see Animal
 * @see Vet
 * @see MainWindowController
 * @see AppointmentType
 * 
 *  
 */
public class EditAppointmentWindowController extends Controller<EditAppointmentWindow> {

    private AppointmentCalendar calendar;
    private Appointment appointment;
    private MainWindowController mainWinController;
    private Animal selectedAnimal;
    private Vet selectedVet;
    private AppointmentType selectedAppointmentType;
    private LocalDate selectedDate;
    private String selectedTime;
    private Boolean isPaid;
    private List<Appointment> allAppointments;
    private List<String> cBoxTimeSlots;
    private List<Vet> vets;
    private List<Vet> listViewVets;
    private List<Animal> listViewAnimals;


    /**
     * Constructor for the EditAppointmentWindowController class. Calls the superclass constructor
     * and initializes the lists of animals and vets. Sets the values of the view components to
     * reflect the values of the appointment to be edited. Sets the data change handlers for the view
     * components. 
     *
     * @param view the view to be controlled
     * @param model the DAO implementation that communicates with the data source
     * @param calendar the AppointmentCalendar object that manages appointment related data
     * @param appointment the appointment to be edited
     * @param mainWinController the controller for the main window
     */
    public EditAppointmentWindowController(EditAppointmentWindow view,
            DAO model,
            AppointmentCalendar calendar,
            Appointment appointment,
            MainWindowController mainWinController) {
        super(view, model);
        this.calendar = calendar;
        this.appointment = appointment;
        this.mainWinController = mainWinController;
        initLists();
        dataToView();
        setDataChangeHandlers();
    }

    @Override
    protected final void setDataChangeHandlers() {
        view.getSaveBtn().addEventHandler(ActionEvent.ACTION, this::saveAppointment);
        view.getCancelBtn().addEventHandler(ActionEvent.ACTION, this::closeWithoutSave);
        view.getPaidCheckBox().addEventHandler(ActionEvent.ACTION, this::paidCheckboxSelected);
        view.getTimeCbox().addEventHandler(ActionEvent.ACTION, this::timeSelected);
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
        getSelectedValues();
        view.getFilteredAnimalsListView().setItems((ObservableList) listViewAnimals);
        view.getFilteredAnimalsListView().getSelectionModel().select(selectedAnimal);
        view.getFilteredAnimalsListView().setDisable(true);
        filterVets();
        view.getVetListView().setItems((ObservableList) listViewVets);
        view.getVetListView().getSelectionModel().select(selectedVet);
        view.getSelectedAnimalNameLbl().setText(selectedAnimal.toString());
        view.getSelectedVetNameLbl().setText(selectedVet.toString());
        view.getLocationValueLabel().setText(appointment.getLocation());
        findToggle(selectedAppointmentType);
        view.getApptDatePicker().setDayCellFactory(getCustomDayCellFactory(AppointmentCalendar.startDate, AppointmentCalendar.endDate));
        view.getApptDatePicker().setValue(selectedDate);
        filterTimeSlots();
        view.getTimeCbox().setValue(selectedTime);
        view.getPaidCheckBox().setSelected(appointment.isPaid());
    }

    public AppointmentCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(AppointmentCalendar calendar) {
        this.calendar = calendar;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public MainWindowController getMainWinController() {
        return mainWinController;
    }

    public void setMainWinController(MainWindowController mainWinController) {
        this.mainWinController = mainWinController;
    }

    public Animal getSelectedAnimal() {
        return selectedAnimal;
    }

    public void setSelectedAnimal(Animal selectedAnimal) {
        this.selectedAnimal = selectedAnimal;
    }

    public Vet getSelectedVet() {
        return selectedVet;
    }

    public void setSelectedVet(Vet selectedVet) {
        this.selectedVet = selectedVet;
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

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public List<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public void setAllAppointments(List<Appointment> allAppointments) {
        this.allAppointments = allAppointments;
    }

    public List<String> getcBoxTimeSlots() {
        return cBoxTimeSlots;
    }

    public void setcBoxTimeSlots(List<String> cBoxTimeSlots) {
        this.cBoxTimeSlots = cBoxTimeSlots;
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

    public List<Animal> getListViewAnimals() {
        return listViewAnimals;
    }

    public void setListViewAnimals(List<Animal> listViewAnimals) {
        this.listViewAnimals = listViewAnimals;
    }

    private void paidCheckboxSelected(ActionEvent event) {
        isPaid = view.getPaidCheckBox().isSelected();
    }

    /**
     * Retrieves the values of the appointment to be edited and assigns them to the corresponding
     * fields in the controller.
     * @return void
     */
    private void getSelectedValues() {
        selectedAnimal = appointment.getAnimal();
        selectedVet = appointment.getVet();
        selectedDate = appointment.getDate();
        selectedTime = appointment.getTime();
        selectedAppointmentType = appointment.getAppointmentType();
        isPaid = appointment.isPaid();

    }

    /**
     * Find  adn select the toggle button corresponding to the appointment type of the 
     * appointment.
     * 
     * @param type the AppointmentType enum to be matched with a toggle button
     * @return void
     */
    private void findToggle(AppointmentType type) {
        for (Toggle toggle : view.getApptTypeToggleGroup().getToggles()) {
            RadioButton rb = (RadioButton) toggle;
            if ((rb.getText().equals(type.toString()))) {
                view.getApptTypeToggleGroup().selectToggle(toggle);
            }
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
     * Initialise the lists of animals, vets and time slots. Only the listview holding the animals is
     * populated in this method, the other elements are initialised with empty observable arraylists
     * to be populated based on selections made by the user.
     * @return void
     */
    private void initLists() {
        try {
            allAppointments = model.getAllAppointments();
            listViewAnimals = FXCollections.observableArrayList(model.getAllAnimals());
            vets = model.getAllVets();
            listViewVets = FXCollections.observableArrayList();
            cBoxTimeSlots = FXCollections.observableArrayList();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
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
     * @return void
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
        if (selectedVet == null
                || selectedAppointmentType == null
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
            appointment.setVet(selectedVet);
            appointment.setDate(selectedDate);
            appointment.setTime(selectedTime);
            appointment.setAppointmentType(selectedAppointmentType);
            appointment.setPaid(isPaid);
            mainWinController.refreshTable();

            Alert alert = saveSuccessAlert(POJOName.APPOINTMENT);
            alert.show();
            view.close();

        } else {

            Alert alert = missingInfoAlert("Please select a vet, date, "
                    + "appointment type and time");
            alert.show();
        }
    }

}
