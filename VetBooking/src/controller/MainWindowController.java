/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.event.ActionEvent;
import model.Appointment;
import view.AddAppointmentWindow;
import view.MainWindow;
import model.SerialisationDAO;
import view.AddNewAnimalTypeWindow;
import view.EditAppointmentWindow;
import view.RegisterAnimalWindow;
import view.ViewAndSearchAnimalsWindow;

/**
 *
 * @author igbin
 */
public class MainWindowController extends Controller<MainWindow>{
    
     private Appointment selectedAppointment;
    
    public MainWindowController(MainWindow view, SerialisationDAO model) {
        super(view, model);
        setupEventHandlers();
    }

    @Override
    protected final void setupEventHandlers() {
        view.getAddNewTypeMenuItem().addEventHandler(ActionEvent.ACTION, this::openAddNewAnimalTypeWindow);
        view.getBookBtn().addEventHandler(ActionEvent.ACTION, this::openAddBookingWindow);
        view.getViewAndSearchMenuItem().addEventHandler(ActionEvent.ACTION, this::openViewAndSearchAnimalsWindow);
        view.getRegisterAnimalMenuItem().addEventHandler(ActionEvent.ACTION, this::openRegisterAnimalWindow);
        view.getDeleteBtn().addEventHandler(ActionEvent.ACTION, this::deleteAppointment);
        view.getEditBtn().addEventHandler(ActionEvent.ACTION, this::editAppointment);
    }

   
    private void openAddNewAnimalTypeWindow(ActionEvent event){
        AddNewAnimalTypeWindow addNewAnimalTypeWin = new AddNewAnimalTypeWindow();
        addNewAnimalTypeWin.show();
    }
    
    private void openAddBookingWindow(ActionEvent event){
        AddAppointmentWindow addAppointmentWin = new AddAppointmentWindow();
        addAppointmentWin.show();
    }
    
    private void openViewAndSearchAnimalsWindow(ActionEvent event){
        ViewAndSearchAnimalsWindow viewAndSearchWin = new ViewAndSearchAnimalsWindow();
        viewAndSearchWin.show();
    }
    
    private void openRegisterAnimalWindow(ActionEvent event){
        RegisterAnimalWindow registerAnimalWin = new RegisterAnimalWindow();
        registerAnimalWin.show();
    }
    
    private void deleteAppointment(ActionEvent event){
        model.deleteAppointment(selectedAppointment);
    }
    
    private void editAppointment(ActionEvent event){
        EditAppointmentWindow editAppointmentWin = new EditAppointmentWindow();
        editAppointmentWin.show();
    }
    
    
    
    
}
