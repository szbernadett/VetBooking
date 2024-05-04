
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
 * Controller class: Represents a controller for the view components in the
 * veterinary administration system.
 * 
 * @param <T> the CustomStage implementation to be controlled
 *            view: <T> the view to be controlled
 *            model: the DAO implementation that communicates with the data
 *            source
 * 
 * @see CustomStage
 */
public abstract class Controller<T extends CustomStage> {

    protected T view;
    protected DAO model;

    public Controller() {
    }

    /**
     * Constructor for the controller class
     * 
     * @param view  the view to be controlled
     * @param model the DAO implementation that communicates with the data source
     */
    public Controller(T view, DAO model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Set the event handlers for view elements that accept user interaction
     */
    protected abstract void setDataChangeHandlers();

    /**
     * Set up view components using data from the model, for example
     * populating a ComboBox or
     * setting fields to the values of a selected object
     * 
     */
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

    /**
     * Create an alert when an object is saved
     * 
     * @param name the name of the class which the object is an instance of
     * @return an Alert instance ready to be shown
     */
    protected Alert saveSuccessAlert(POJOName name) {
        String objectName = name.getStringValue();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Success");
        alert.setContentText(objectName + " saved.");

        return alert;

    }

    /**
     * Create an alert when a window where data is added or edited is due
     * to be closed, usually
     * triggered by the Cancel button
     * 
     * @return an Alert instance ready to be shown
     */

    protected Alert closeWithoutSaveAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Cancel process");
        alert.setContentText("Changes will not be saved. Do you wish to continue?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        return alert;

    }

    /**
     * Create an alert when a data could not be saved because of an error,
     * for example
     * when an exception is trhown while communication with the data source
     * 
     * @return an Alert instance ready to be shown
     */

    protected Alert saveInterruptedAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Warning");
        alert.setHeaderText("Close without saving");
        alert.setContentText("Changes could not be saved. Continue anyway?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        return alert;

    }

    /**
     * Create an alert when an action is attempted that requires an object
     * but there is
     * none selected
     * 
     * @param name then name of the class which the object is an instance of
     * @return an Alert instance ready to be shown
     * @see POJOName
     */
    protected Alert noneSelectedAlert(POJOName name) {
        String objectName = name.getStringValue();
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("No " + objectName + " selected");
        alert.setContentText("Please select a " + objectName + " to continue.");
        alert.getButtonTypes().setAll(ButtonType.OK);

        return alert;

    }

    /**
     * Create an alert when an exception occurs while attemptint to
     * retrieve data from
     * the data source
     * 
     * @return an Alert instance ready to be shown
     */
    protected Alert dataAccessAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Cannot access data");
        alert.setContentText("Could not load data from source");

        return alert;
    }

    /**
     * Create an alert when not all required data is provided by the user,
     * for example when creating anew animal type or registering an animal.
     * 
     * @param contentText The main message of the alert
     * @return an Alert instance ready to be shown
     */

    protected Alert missingInfoAlert(String contentText) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Missing information");
        alert.setContentText(contentText);

        return alert;
    }

    /**
     * Remove unwanted characters and whitespace from a string, for example when
     * searching for an object
     * 
     * @param text the string to be cleaned
     * @return the cleaned string
     */

    protected String prepareString(String text) {
        text = text.trim().toLowerCase();
        text = text.replaceAll("[^a-zA-Z0-9]", "");
        return text;
    }

    /**
     * Close a window without saving data. Shows an alert to the user
     * for confirmation before closing the window.
     * 
     * @param event the event from the view component that triggers the action
     */
    protected void closeWithoutSave(Event event) {
        Alert alert = closeWithoutSaveAlert();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            view.close();
        }
    }

    /**
     * Enum to convert class names to user-friendly strings. Used in alerts and
     * messages.
     */

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

    /**
     * Enum to indicate the result of a validation process, for example checking for
     * duplicate data
     * or verifying date of birth
     */

    public enum Validate {
        OK, FAIL;
    }

}
