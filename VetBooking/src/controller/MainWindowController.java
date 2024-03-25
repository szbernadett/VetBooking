/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.event.ActionEvent;
import view.AddAppointmentWindow;
import view.MainWindow;
import model.SerialisationDAO;

/**
 *
 * @author igbin
 */
public class MainWindowController extends Controller<MainWindow>{
    

    public MainWindowController(MainWindow view, SerialisationDAO dao) {
        super(view, dao);
        
        setupEventHandlers();
    }

    @Override
    protected final void setupEventHandlers() {
        view.getAddNewTypeMenuItem().addEventHandler(ActionEvent.ACTION, this::openAddNewAnimalTypeWindow);
        view.getBookBtn().addEventHandler(ActionEvent.ACTION, this::openAddBookingWindow);
        view.getViewAndSearchMenuItem().addEventHandler(ActionEvent.ACTION, this::openViewAndSearchAnimalsWindow);
        view.getRegisterAnimalMenuItem().addEventHandler(ActionEvent.ACTION, this::openRegisterAnimalWindow);
        view.getDeleteBtn().addEventHandler(ActionEvent.ACTION, this::deleteBooking);
        view.getEditBtn().addEventHandler(ActionEvent.ACTION, this::editBooking);
    }

   


    private void openAddNewAnimalTypeWindow(ActionEvent event){}
    
    private void openAddBookingWindow(ActionEvent event){
        AddAppointmentWindow addBookingWindow = new AddAppointmentWindow();
    }
    
    private void openViewAndSearchAnimalsWindow(ActionEvent event){}
    
    private void openRegisterAnimalWindow(ActionEvent event){}
    
    private void deleteBooking(ActionEvent event){}
    
    private void editBooking(ActionEvent event){}
    
    
    
    
}
