/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Optional;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.CustomStage;
import model.DAO;
import model.SerialisationDAO;

/**
 *
 * @author igbin
 * @param <T>
 */
public abstract class Controller<T extends CustomStage> {

    protected T view;
    protected DAO model;

    public Controller() {
    }

    public Controller(T view, DAO model) {
        this.view = view;
        this.model = model;
    }

    protected abstract void setDataChangeHandlers();

    protected abstract void dataToView();

    public Stage getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public DAO getDao() {
        return model;
    }

    public void setDao(SerialisationDAO model) {
        this.model = model;
    }

    protected Alert saveSuccessAlert(POJOName name) {
        String objectName = name.getStringValue();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Success");
        alert.setContentText(objectName + " saved.");

        return alert;

    }

    protected Alert closeWithoutSaveAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Cancel process");
        alert.setContentText("Changes will not be saved. Do you wish to continue?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        return alert;

    }

    protected Alert saveInterruptedAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Warning");
        alert.setHeaderText("Close without saving");
        alert.setContentText("Changes could not be saved. Continue anyway?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        return alert;

    }

    protected Alert noneSelectedAlert(POJOName name) {
        String objectName = name.getStringValue();
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("No " + objectName + " selected");
        alert.setContentText("Please select a " + objectName + " to continue.");
        alert.getButtonTypes().setAll(ButtonType.OK);

        return alert;

    }

    protected Alert dataAccessAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Cannot access data");
        alert.setContentText("Could not load data from file");

        return alert;
    }
    
    protected Alert missingInfoAlert(String contentText){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Missing information");
        alert.setContentText(contentText);
        
        return alert;
    }

    protected String prepareString(String text) {
        text = text.trim().toLowerCase();
        text = text.replaceAll("[^a-zA-Z0-9]", "");
        return text;
    }   
   

    protected void closeWithoutSave(Event event) {
        Alert alert = closeWithoutSaveAlert();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            view.close();
        }
    }

    public enum POJOName {
        ADDRESS("Address"),
        ADMINISTRATOR("Administrator"),
        ANIMAL("Animal"),
        ANIMAL_TYPE("Animal type"),
        APPOINTMENT("Appointment"),
        APPOINTMENT_NOTE("Appointment note"),
        CARETAKER("Caretaker"),
        CITY("City"),
        FARM_ANIMAL("Farm animal"),
        PERSON("Person"),
        PET("Pet"),
        POSTCODE("Postcode"),
        RECORD("Record"),
        VET("Vet"),
        ZOO_ANIMAL("Zoo animal");

        private final String stringValue;

        private POJOName(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }

    }

    public enum Validate {
        OK, FAIL;
    }

}
