/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.LocalDate;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.RadioButton;
import model.Address;
import model.Animal.DateOfBirth;
import model.AnimalType;
import model.Caretaker;
import model.DAO;
import view.RegisterAnimalWindow;
import view.RegisterAnimalWindow.RadioLabel;

/**
 *
 * @author igbin
 */
public class RegisterAnimalWindowController extends Controller<RegisterAnimalWindow> {

    private String identifier;
    private AnimalType animalType;
    private Caretaker caretaker;
    private Address address;
    private DateOfBirth dateOfBirth;
    private LocalDate dateRegistered;

    public RegisterAnimalWindowController(RegisterAnimalWindow view, DAO model) {
        super(view, model);
    }

    private void dobToggleSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {

        if (newValue != null) {
            RadioButton rb = (RadioButton) newValue;
            String buttonText = rb.getText();

            if (buttonText.equals(RadioLabel.DATE.getStringValue())) {
                view.getDobDatePicker().setDisable(false);
                dateOfBirth=DateOfBirth.of(view.getDobDatePicker().getValue());
            } else {
                view.getDobDatePicker().setDisable(true);
                dateOfBirth=DateOfBirth.notApplicable();
            }
        }
    }
    
    private void dateSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue){
        
        dateOfBirth = DateOfBirth.of((LocalDate)newValue);
        
    }
    
    private void animalTypeSelected(Event event){
        animalType = view.getTypeValueCBox().getValue();
    }
    
    private void caretakerSelected(Event event){
        caretaker = (Caretaker) view.getCaretakerValueCBox().getValue();
    }
    
    private void addressSelected(Event event){
        address = view.getAddressValueCBox().getValue();
    }

    @Override
    protected void setDataChangeHandlers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
