/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Appointment;
import model.AppointmentCalendar;
import model.DAO;
import view.AddAppointmentWindow;
import view.MainWindow;
import view.AddNewAnimalTypeWindow;
import view.EditAppointmentWindow;
import view.RegisterAnimalWindow;
import view.ViewAndSearchAnimalsWindow;

/**
 *
 * @author igbin
 */
public class MainWindowController extends Controller<MainWindow> {

    private Appointment selectedAppointment;

    public MainWindowController(MainWindow view, DAO model) {
        super(view, model);
        try {
            view.getAppointmentTable().setItems((ObservableList) model.getAllAppointments());
        } catch (ClassNotFoundException | IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot access data");
            alert.setContentText("Could not load appointments from file");

        }
        setupEventHandlers();
        addListeners();
    }

    @Override
    protected final void setupEventHandlers() {
        view.getAddNewTypeMenuItem().addEventHandler(ActionEvent.ACTION, this::openAddNewAnimalTypeWindow);
        view.getBookBtn().addEventHandler(ActionEvent.ACTION, this::openAddAppointmentWindow);
        view.getViewAndSearchMenuItem().addEventHandler(ActionEvent.ACTION, this::openViewAndSearchAnimalsWindow);
        view.getRegisterAnimalMenuItem().addEventHandler(ActionEvent.ACTION, this::openRegisterAnimalWindow);
        view.getDeleteBtn().addEventHandler(ActionEvent.ACTION, this::deleteAppointment);
        view.getEditBtn().addEventHandler(ActionEvent.ACTION, this::editAppointment);
        view.getExitBtn().addEventHandler(ActionEvent.ACTION, this::exitWindow);
        view.setOnCloseRequest(this::exitWindow);
    }
    
    private void addListeners(){
        view.getAppointmentTable().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedAppointment = (Appointment) newValue;
                    System.out.println(newValue);
                });
    }

    private void openAddNewAnimalTypeWindow(ActionEvent event) {
        AddNewAnimalTypeWindow addNewAnimalTypeWin = new AddNewAnimalTypeWindow();
        addNewAnimalTypeWin.show();
    }

    private void openAddAppointmentWindow(ActionEvent event) {
        AddAppointmentWindow addAppointmentWin = new AddAppointmentWindow();
        try {
            AddAppointmentWindowController aawController = new AddAppointmentWindowController(
                    addAppointmentWin,
                    model,
                    AppointmentCalendar.getInstance(model.getAllAppointments()));
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }
        addAppointmentWin.show();
    }

    private void openViewAndSearchAnimalsWindow(ActionEvent event) {
        ViewAndSearchAnimalsWindow viewAndSearchWin = new ViewAndSearchAnimalsWindow();
        viewAndSearchWin.show();
    }

    private void openRegisterAnimalWindow(ActionEvent event) {
        RegisterAnimalWindow registerAnimalWin = new RegisterAnimalWindow();
        registerAnimalWin.show();
    }

    private void deleteAppointment(ActionEvent event) {
        if (selectedAppointment != null) {
            model.deleteAppointment(selectedAppointment);
        }
    }

    private void editAppointment(ActionEvent event) {
        if (selectedAppointment != null) {
            EditAppointmentWindow editAppointmentWin = new EditAppointmentWindow(selectedAppointment);
            editAppointmentWin.show();
        }
    }

    @Override
    protected void exitWindow(Event event) {
        try {
            model.saveAppointments();
            model.saveAnimals();
            model.saveAnimalTypes();
            model.saveRecords();
        } catch (IOException ex) {
            System.out.println(ex);
            Alert alert = warningAlert("Warning",
                    "Close without saving",
                    "Could not save changes. Close anyway?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                super.exitWindow(event);
            }

        }

    }

}
