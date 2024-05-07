
package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Appointment;
import model.AppointmentCalendar;
import model.DAO;
import model.RecordHandler;
import view.AddAppointmentWindow;
import view.MainWindow;
import view.AddNewAnimalTypeWindow;
import view.EditAppointmentWindow;
import view.RegisterAnimalWindow;
import view.ViewAndSearchAnimalsWindow;
import model.Record;

/**
 * A controller class for the MainWindow component of the veterinary administration system.
 * 
 * - view the MainWindow view to be controlled
 * - model the DAO implementation that communicates with the data source
 * - selectedAppointment the currently selected appointment
 * - allAppointments a list of all currently saved appointments
 * - allRecords a list of all currently saved records
 * 
 * @see MainWindow
 * @see DAO
 * @see Appointment
 * @see Record
 * 
 */
public class MainWindowController extends Controller<MainWindow> {

    private Appointment selectedAppointment;
    private List<Appointment> allAppointments;
    private List<Record> allRecords;

    /**
     * Constructor for the MainWindowController class. Calls the superclass constructor,
     * initialises the view components with data retrieved from the model and 
     * sets the event handlers for the view elements that accept user interaction.
     * 
     * @param view the MainWindow view to be controlled
     * @param model the DAO implementation that communicates with the data source
     */
    public MainWindowController(MainWindow view, DAO model) {
        super(view, model);
        dataToView();
        setDataChangeHandlers();
    }

    @Override
    protected final void setDataChangeHandlers() {
        view.getAddNewTypeMenuItem().addEventHandler(ActionEvent.ACTION, this::openAddNewAnimalTypeWindow);
        view.getBookBtn().addEventHandler(ActionEvent.ACTION, this::openAddAppointmentWindow);
        view.getViewAndSearchMenuItem().addEventHandler(ActionEvent.ACTION, this::openViewAndSearchAnimalsWindow);
        view.getRegisterAnimalMenuItem().addEventHandler(ActionEvent.ACTION, this::openRegisterAnimalWindow);
        view.getDeleteBtn().addEventHandler(ActionEvent.ACTION, this::deleteAppointment);
        view.getEditBtn().addEventHandler(ActionEvent.ACTION, this::editAppointment);
        view.getExitBtn().addEventHandler(ActionEvent.ACTION, this::exitWindow);
        view.setOnCloseRequest(this::exitWindow);
        view.getAppointmentTable()
                .getSelectionModel().selectedItemProperty()
                .addListener(this::appointmentSelected);
    }

    @Override
    protected final void dataToView() {
        try {
            allAppointments = model.getAllAppointments();
            allRecords = model.getAllRecords();
            view.getAppointmentTable().setItems((ObservableList) allAppointments);
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
            Alert alert = dataAccessAlert();
            alert.show();
        }
    }

    public Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    public void setSelectedAppointment(Appointment selectedAppointment) {
        this.selectedAppointment = selectedAppointment;
    }

    public List<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public void setAllAppointments(List<Appointment> allAppointments) {
        this.allAppointments = allAppointments;
    }

    public List<Record> getAllRecords() {
        return allRecords;
    }

    public void setAllRecords(List<Record> allRecords) {
        this.allRecords = allRecords;
    }

    
    /**
     * Save all appointments, animals, animal types and records to the data source. If there is
     * an exception preventing the model to persist data, an alert is shown to the user with an option to
     * close the application without saving the data.
     * 
     * @param event the event created from the user pressing the Exit button or closing the window
     * @return void
     */
    private void exitWindow(Event event) {
        try {
            model.save();
            view.close();
        } catch (IOException ex) {
            System.out.println(ex);
            Alert alert = saveInterruptedAlert();
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                view.close();
            }

        }

    }

    /**
     * Set the selectedAppointment field to the value of the newly selected appointment in the table.
     * 
     * @param observable the observable value of the table
     * @param oldValue the previously selected appointment
     * @param newValue the newly selected appointment
     * @return void
     */

    private void appointmentSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {
        selectedAppointment = (Appointment) newValue;
    }

    /**
     * Create and show and instance of the AddNewAnimalTypeWindow and its controller.
     * @param event the event created from the user selecting the Add New Animal Type menu item
     * @see AddNewAnimalTypeWindow
     * @see AddNewAnimalTypeWindowController
     * @return void
     */
    private void openAddNewAnimalTypeWindow(ActionEvent event) {
        AddNewAnimalTypeWindow addNewAnimalTypeWin = new AddNewAnimalTypeWindow();
        AddNewAnimalTypeWindowController anatWinController
                = new AddNewAnimalTypeWindowController(addNewAnimalTypeWin, model);
        addNewAnimalTypeWin.show();
    }

    /**
     * Create and show an instance of the AddAppointmentWindow and its controller.
     * @param event the event created from the user pressing the Book button
     * @see AddAppointmentWindow
     * @see AddAppointmentWindowController
     * @return void
     * 
     */
    private void openAddAppointmentWindow(ActionEvent event) {
        AddAppointmentWindow addAppointmentWin = new AddAppointmentWindow();
        AddAppointmentWindowController aawController = new AddAppointmentWindowController(
                addAppointmentWin,
                model,
                AppointmentCalendar.getInstance(allAppointments));
        addAppointmentWin.show();
    }

    /**
     * Create and show an instance of the ViewAndSearchAnimalsWindow and its controller.
     * @param event the event created from the user selecting the View and Search Animals menu item
     * @see ViewAndSearchAnimalsWindow
     * @see ViewAndSearchController
     * @return void
     */
    private void openViewAndSearchAnimalsWindow(ActionEvent event) {
        ViewAndSearchAnimalsWindow viewAndSearchWin = new ViewAndSearchAnimalsWindow();
        ViewAndSearchController vasWinController = new ViewAndSearchController(
                viewAndSearchWin, model, RecordHandler.getInstance(allRecords));
        viewAndSearchWin.show();
    }

    /**
     * Create and show an instance of the RegisterAnimalWindow and its controller.
     * @param event the event created from the user selecting the Register Animal menu item
     * @see RegisterAnimalWindow
     * @see RegisterAnimalWindowController
     * @return void
     */
    private void openRegisterAnimalWindow(ActionEvent event) {
        RegisterAnimalWindow registerAnimalWin = new RegisterAnimalWindow();
        RegisterAnimalWindowController raWinController = 
                new RegisterAnimalWindowController(registerAnimalWin, model);
        registerAnimalWin.show();
    }

    /**
     * Delete the selected appointment from the data source and the table. If no appointment is 
     * selected, an alert is shown to the user.
     * @param event the event created from the user pressing the Delete button
     * @see POJOName
     * @see noneSelectedAlert
     * @see Appointment
     * @return void
     */
    private void deleteAppointment(ActionEvent event) {
        if (selectedAppointment != null) {
            model.deleteAppointment(selectedAppointment);
        } else {
            Alert alert = noneSelectedAlert(POJOName.APPOINTMENT);
            alert.showAndWait();
        }
    }

    /**
     * Check that there is an appointment selected and if so, create and show an instance of the EditAppointmentWindow
     * and its controller. 
     * @param event the event created from the user pressing the Edit button
     * @see EditAppointmentWindow
     * @see EditAppointmentWindowController
     * @return void
     */
    private void editAppointment(ActionEvent event) {
        if (selectedAppointment != null) {
            if (!selectedAppointment.getDate().isBefore(LocalDate.now())) {
                EditAppointmentWindow editAppointmentWin = new EditAppointmentWindow();
                EditAppointmentWindowController eaWinController
                        = new EditAppointmentWindowController(editAppointmentWin,
                                model,
                                AppointmentCalendar
                                        .getInstance(allAppointments),
                                selectedAppointment,
                                this);
                editAppointmentWin.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Wrong date");
                alert.setContentText("Past appointments cannot be edited.");
                alert.show();
            }
        } else {
            Alert alert = noneSelectedAlert(POJOName.APPOINTMENT);
            alert.show();
        }
    }

    /**
     * Refresh the appointment table to reflect changes made to the data source in the view.
     * @return void
     */
    public void refreshTable() {
        view.getAppointmentTable().refresh();
    }

}
