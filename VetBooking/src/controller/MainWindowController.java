/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author igbin
 */
public class MainWindowController extends Controller<MainWindow> {

    private Appointment selectedAppointment;
    private List<Appointment> allAppointments;
    private List<Record> allRecords;

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

    private void exitWindow(Event event) {
        try {
            model.saveAppointments();
            model.saveAnimals();
            model.saveAnimalTypes();
            model.saveRecords();
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

    
    private void appointmentSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {
        selectedAppointment = (Appointment) newValue;
    }

    private void openAddNewAnimalTypeWindow(ActionEvent event) {
        AddNewAnimalTypeWindow addNewAnimalTypeWin = new AddNewAnimalTypeWindow();
        AddNewAnimalTypeWindowController anatWinController
                = new AddNewAnimalTypeWindowController(addNewAnimalTypeWin, model);
        addNewAnimalTypeWin.show();
    }

    private void openAddAppointmentWindow(ActionEvent event) {
        AddAppointmentWindow addAppointmentWin = new AddAppointmentWindow();
        AddAppointmentWindowController aawController = new AddAppointmentWindowController(
                addAppointmentWin,
                model,
                AppointmentCalendar.getInstance(allAppointments));
        addAppointmentWin.show();
    }

    private void openViewAndSearchAnimalsWindow(ActionEvent event) {
        ViewAndSearchAnimalsWindow viewAndSearchWin = new ViewAndSearchAnimalsWindow();
        ViewAndSearchController vasWinController = new ViewAndSearchController(
                viewAndSearchWin, model, RecordHandler.getInstance(allRecords));
        viewAndSearchWin.show();
    }

    private void openRegisterAnimalWindow(ActionEvent event) {
        RegisterAnimalWindow registerAnimalWin = new RegisterAnimalWindow();
        RegisterAnimalWindowController raWinController = 
                new RegisterAnimalWindowController(registerAnimalWin, model);
        registerAnimalWin.show();
    }

    private void deleteAppointment(ActionEvent event) {
        if (selectedAppointment != null) {
            model.deleteAppointment(selectedAppointment);
        } else {
            Alert alert = noneSelectedAlert(POJOName.APPOINTMENT);
            alert.showAndWait();
        }
    }

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

    public void refreshTable() {
        view.getAppointmentTable().refresh();
    }

}
