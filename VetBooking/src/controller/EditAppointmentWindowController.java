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
 *
 * @author igbin
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

    private void getSelectedValues() {
        selectedAnimal = appointment.getAnimal();
        selectedVet = appointment.getVet();
        selectedDate = appointment.getDate();
        selectedTime = appointment.getTime();
        selectedAppointmentType = appointment.getAppointmentType();
        isPaid = appointment.isPaid();

    }

    private void findToggle(AppointmentType type) {
        for (Toggle toggle : view.getApptTypeToggleGroup().getToggles()) {
            RadioButton rb = (RadioButton) toggle;
            if ((rb.getText().equals(type.toString()))) {
                view.getApptTypeToggleGroup().selectToggle(toggle);
            }
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

    private void filterTimeSlots() {
        view.getTimeCbox().setValue(null);
        cBoxTimeSlots = FXCollections
                .observableArrayList(calendar.getFreeTimeSlots(selectedVet, selectedDate, selectedAppointmentType));
        view.getTimeCbox().setItems((ObservableList) cBoxTimeSlots);

    }

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
        if (selectedVet == null
                || selectedAppointmentType == null
                || selectedDate == null
                || selectedTime == null) {

            result = Validate.FAIL;
        }
        return result;
    }

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
