/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    
    protected <T extends Event> void setEventHandler(Node node, EventType<T> et, EventHandler<T> handler){
        node.addEventHandler(et, handler);
    }
    
    protected <U> void addEventListener(ObservableValue<U> observable, ChangeListener<? super U> listener){
        observable.addListener(listener);
    }

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

    protected void exitWindow(Event event) {
        view.close();
    }

    protected Alert saveSuccessAlert(POJOName name) {
        String objectName = name.getStringValue();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Success");
        alert.setContentText(objectName + " saved.");

        return alert;

    }

    protected Alert closeWithoutSaveAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Cancel process");
        alert.setContentText("Changes will not be saved. Do you wish to continue?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        return alert;

    }

    protected Alert saveInterruptedAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Warning");
        alert.setHeaderText("Close without saving");
        alert.setContentText("Changes could not be saved. Continue anyway?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        return alert;

    }

    protected Alert noneSelectedAlert(POJOName name) {
        String objectName = name.getStringValue();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("No " + objectName + " selected");
        alert.setContentText("Please select a " + objectName + " to continue.");
        alert.getButtonTypes().setAll(ButtonType.OK);

        return alert;

    }

    protected String prepareString(String text) {
        text = text.trim().toLowerCase();
        text = text.replaceAll("[^a-zA-Z0-9]", "");
        return text;
    }

    public enum POJOName {
        ADDRESS("address"),
        ADMINISTRATOR("administrator"),
        ANIMAL("animal"),
        ANIMAL_TYPE("animal type"),
        APPOINTMENT("appointment"),
        APPOINTMENT_NOTE("appointment note"),
        CARETAKER("caretaker"),
        CITY("city"),
        FARM_ANIMAL("farm animal"),
        PERSON("person"),
        PET("pet"),
        POSTCODE("postcode"),
        RECORD("record"),
        VET("vet"),
        ZOO_ANIMAL("zoo animal");

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

    public enum DuplicateCheck {
        OK, FAIL;
    }

}
