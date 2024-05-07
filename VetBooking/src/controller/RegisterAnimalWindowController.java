
package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.util.Callback;
import model.Address;
import model.Address.LocationType;
import model.Animal;
import model.Animal.DateOfBirth;
import model.Animal.Gender;
import model.AnimalType;
import model.AnimalType.AgeConstraint;
import model.Caretaker;
import model.DAO;
import model.FarmAnimal;
import model.Pet;
import model.ZooAnimal;
import view.RegisterAnimalWindow;
import view.RegisterAnimalWindow.DoBRadioLabel;
import model.Record;

/**
 * Controller class for the RegisterAnimalWindow view. 
 * - view The RegisterAnimalWindow view
 * - model The DAO model
 * - identifier The identifier for the animal being registered 
 * - gender the gender of the animal being registered
 * - animalType the animal type of the animal being registered
 * - caretaker the caretaker assigned to the animal being registered
 * - address the address of the animal being registered
 * - dateSelected the date of birth of the animal being registered
 * - dateRegistered the date the animal is registered
 * - dateOfBirth the date of birth of the animal being registered
 * 
 * @see Gender
 * @see AnimalType
 * @see Caretaker
 * @see Address
 * @see DateOfBirth
 * @see RegisterAnimalWindow
 * @see DAO
 * @see Controller
 * 
 * 
 */
public class RegisterAnimalWindowController extends Controller<RegisterAnimalWindow> {

    private String identifier;
    private Gender gender;
    private AnimalType animalType;
    private Caretaker caretaker;
    private Address address;
    private LocalDate dateSelected;
    private LocalDate dateRegistered;
    private DateOfBirth dateOfBirth;

    public RegisterAnimalWindowController(RegisterAnimalWindow view, DAO model) {
        super(view, model);
        dataToView();
        setDataChangeHandlers();

    }

    @Override
    protected final void setDataChangeHandlers() {
        view.getTypeValueCBox().addEventHandler(ActionEvent.ACTION, this::animalTypeSelected);
        view.getGenderToggleGroup().selectedToggleProperty().addListener(this::genderToggleSelected);
        view.getCaretakerValueCBox().addEventHandler(ActionEvent.ACTION, this::caretakerSelected);
        view.getAddressValueCBox().addEventHandler(ActionEvent.ACTION, this::addressSelected);
        view.getDobToggleGroup().selectedToggleProperty().addListener(this::dobToggleSelected);
        view.getDobDatePicker().valueProperty().addListener(this::dateSelected);
        view.getClearAllBtn().addEventHandler(ActionEvent.ACTION, this::clearView);
        view.getCancelBtn().addEventHandler(ActionEvent.ACTION, this::closeWithoutSave);
        view.getSaveBtn().addEventHandler(ActionEvent.ACTION, this::saveAnimal);

    }

    @Override
    protected final void dataToView() {
        try {
            view.getTypeValueCBox().setItems((ObservableList) model.getAllAnimalTypes());
            view.getCaretakerValueCBox().setItems((ObservableList) model.getAllCaretakers());
            view.getAddressValueCBox().setItems((ObservableList) model.getAllAddresses());
            LocalDate startEnable = LocalDate.now().minusYears(AgeConstraint.MAX_AGE_ALL.getValue());
            view.getDobDatePicker().setDayCellFactory(getCustomDayCellFactory(startEnable, LocalDate.now()));
            view.getGenderToggleGroup().selectToggle(view.getFemaleRBtn());
            RadioButton rb = (RadioButton) view.getGenderToggleGroup().getSelectedToggle();
            gender = Gender.fromStringValue(rb.getText());
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
            Alert alert = dataAccessAlert();
            alert.show();
        }

        view.getDobToggleGroup().selectToggle(view.getPickDateRBtn());
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateSelected;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateSelected = dateOfBirth;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    /** Create a custom day cell factory for the date picker. The factory creates a DateCell which 
     * disables dates that are before the start date and after the end date, which dates
     * depend on the maximum expected age of the animal type selected by the user. 
     * 
     * @param startDate the first acceptable date for the date picker to be enabled
     * @param endDate the last acceptable date for the date picker to be enabled
     * @return a Callback object that creates a DateCell object
     * 
     * @see AnimalType.AgeConstraint
     */
    private Callback<DatePicker, DateCell> getCustomDayCellFactory(LocalDate startDate, LocalDate endDate) {
        return (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(startDate) || item.isAfter(endDate)) {
                    setDisable(true);
                }
            }
        };
    }

    /**
     * Find the selected radio button and fetch the Gender enum that corresponds to the text value 
     * of the button. Assign selected Gender enum to the gender field.
     * 
     * @param observable The object that wraps the selected radio button through which other 
     *                   objects can listen for changes
     * @param oldValue The previously selected radio button
     * @param newValue The currently selected radio button 
     * @see Gender
     * @return void
     */
    private void genderToggleSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {
        RadioButton rb = (RadioButton) newValue;
        String text = rb.getText();
        gender = Gender.fromStringValue(text);
    }

    /**
     * Find the selected radio button and fetch the text value of the button. If the text value is
     * equal to the text value of of the DobRadioLabel.DATE enum, enable the date picker. If not,
     * disable the date picker and ensure it does not have a selected date by setting its value to 
     * null, then set the dateOfBirth field to DateOfBirth.notApplicable().
     * 
     * @param observable the object that wraps the selected radio button through which other
     *                   objects can listen for changes   
     * @param oldValue the previously selected radio button
     * @param newValue the currently selected radio button
     * @see RegisterAnimalWindow.DoBRadioLabel
     * @see Animal.DateOfBirth
     */
    private void dobToggleSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {

        if (newValue != null) {
            RadioButton rb = (RadioButton) newValue;
            String buttonText = rb.getText();

            if (buttonText.equals(DoBRadioLabel.DATE.getStringValue())) {
                view.getDobDatePicker().setDisable(false);

            } else {
                view.getDobDatePicker().setDisable(true);
                view.getDobDatePicker().setValue(null);
                dateOfBirth = DateOfBirth.notApplicable();
            }
        }
    }

    /**
     * Fetch the selected date from the date picker and assign it to the dateSelected field. If the
     * dateSelected field is not null, check if there is an animal type selected and if there is, 
     * check that the selected date is within the maximum age limit of the animal type. If the date
     * is within the limit, assign the date to the dateOfBirth field. If not, display a warning alert
     * and set the dateOfBirth field to null. If there is no animal type selected, warn a user to 
     * select one.
     * 
     * @param observable
     * @param oldValue
     * @param newValue
     * @see dateCheck
     * @see Validate
     * @return void
     */
    private void dateSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {

        dateSelected = (LocalDate) newValue;
        if (dateSelected != null) {
            if (animalType != null) {
                Validate selected = dateCheck();
                if (selected == Validate.OK) {
                    dateOfBirth = DateOfBirth.of(dateSelected);
                } else {
                    view.getDobDatePicker().setValue(null);
                    dateOfBirth = null;
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Date not accepted");
                    alert.setContentText("The selected exceeds the maximum age of"
                            + " this animal type.");
                    alert.show();
                }
            } else {
                Alert alert = missingInfoAlert("Please select an animal type first.");
                alert.show();
            }
        }
    }

    /**
     * Assign the selected value of the animal type combo box to the animalType field.
     * @param event The event created by the user selecting an item from the combo box
     * @return void
     */
    private void animalTypeSelected(ActionEvent event) {
        animalType = view.getTypeValueCBox().getValue();
    }

    /**
     * Assign the selected value of the caretaker combo box to the caretaker field.
     * @param event The event created by the user selecting an item from the combo box
     * @return void
     */
    private void caretakerSelected(ActionEvent event) {
        caretaker = view.getCaretakerValueCBox().getValue();
    }

    /** 
     * Assign the selected value of the address combo box to the address field.
     * @param event The event created by the user selecting an item from the combo box
     * @return void
     */
    private void addressSelected(ActionEvent event) {
        address = view.getAddressValueCBox().getValue();
    }

    /**
     * Check if the selected date is within the maximum age limit of the animal type. 
     * @return Validate.OK if the date is within the limit, Validate.FAIL otherwise
     * @see Validate
     * 
     */
    private Validate dateCheck() {
        Validate result = Validate.FAIL;
        int years = animalType.getMaxAge();
        LocalDate toCompare = LocalDate.now().minusYears(years);
        if (dateSelected.isAfter(toCompare) || dateSelected.isEqual(toCompare)) {
            result = Validate.OK;
        }

        return result;
    }

    /**
     * Check if any of the required fields are not set.
     * @return Validate.OK if all required fields are set, Validate.FAIL otherwise
     */
    private Validate checkUnsetValues() {
        Validate result = Validate.OK;
        if (identifier.isEmpty()
                || gender == null
                || animalType == null
                || caretaker == null
                || address == null
                || dateOfBirth == null) {

            result = Validate.FAIL;
        }
        return result;
    }

    /**
     * Check if the identifier entered by the user is already in use by another animal. Get all animal
     * objects from the data source and collect all the identifiers into a list. Check if the identifier
     * entered by the user is in the list.
     * @return Validate.OK if the identifier is not in use, Validate.FAIL if the identifier is in use
     *         or null if the check could not be performed due to an exception
     */
    private Validate checkDuplicateId() {
        Validate result = Validate.OK;
        try {
            List<Animal> animals = model.getAllAnimals();
            List<String> identifiers = animals.stream()
                    .map(Animal::getIdentifier)
                    .collect(Collectors.toList());
            for (String idText : identifiers) {
                if (idText.equalsIgnoreCase(identifier)) {
                    result = Validate.FAIL;
                }
            }

        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
            Alert alert = dataAccessAlert();
            alert.show();
            result = null;
        }

        return result;
    }

    /**
     * Select the subclass of the Animal class based on the LocationType of the address selected
     * by the user.
     * @return An instance of the subclass of the Animal class that corresponds to the LocationType
     *         of the selected address
     */
    private Animal selectAnimalSubclass() {
        Animal animal = null;
        if (address != null) {
            LocationType locType = address.getLocationType();
            switch (locType) {
                case LocationType.DOMESTIC -> {
                    animal = new Pet();
                }
                case LocationType.FARM -> {
                    animal = new FarmAnimal();
                }
                case LocationType.ZOO -> {
                    animal = new ZooAnimal();
                }
                default -> {
                    animal = new Pet();
                }
            }
        }
        return animal;
    }

    /**
     * Prepare the identifier by removing any leading or trailing white spaces and any 
     * non-alphanumeric characters.
     * @return void
     */
    private void prepareId() {
        if (identifier != null) {
            identifier = identifier.trim().replace("[^a-zA-Z0-9]", "");
        }
    }

    /**
     * Save the animal to the data source. If the identifier is not set, display a warning alert. If
     * the identifier is set, check if all required fields are set. If they are, check if the 
     * identifier is already in use. If it is not, create an instance of the subclass of the Animal
     * class that corresponds to the LocationType of the selected address. Set the fields of the
     * animal object with the values of the fields of the controller. Save the animal to the data
     * source. Create a record object with the animal object, the medical history entered by the user
     * and an empty list of treatments. Save the record to the data source. Display a success alert
     * and close the view.
     * @param event the event created by the user clicking the Save button
     * @see selectAnimalSubclass
     * @see prepareId
     * @see checkUnsetValues
     * @see checkDuplicateId
     * @see Validate
     * @return void
     */
    private void saveAnimal(ActionEvent event) {

        identifier = view.getIdValueTField().getText();
        prepareId();
        if (checkUnsetValues() == Validate.OK) {
            Validate id = checkDuplicateId();
            if (id == Validate.OK) {
                Animal animal = selectAnimalSubclass();
                if (animal != null) {
                    animal.setAddress(address);
                    animal.setAnimalType(animalType);
                    animal.setCaretaker(caretaker);
                    animal.setDateOfBirth(dateOfBirth);
                    animal.setGender(gender);
                    animal.setIdentifier(identifier);
                    model.saveAnimal(animal);

                    Record record = new Record(LocalDate.now(),
                            animal,
                            view.getMedHistoryTextArea().getText(),
                            new ArrayList<>());

                    model.saveRecord(record);
                    Alert alert = saveSuccessAlert(POJOName.ANIMAL);
                    alert.show();
                    view.close();
                }
            } else {
                if (id == Validate.FAIL) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Duplicate date");
                    alert.setContentText("This identifier has already been "
                            + "registered for an animal. Please enter a "
                            + "different identifier to continue.");
                    alert.show();
                }
            }
        } else {
            Alert alert = missingInfoAlert("Please make sure the following"
                    + "information is provided or selected: identifier,"
                    + "gender, animal type, caretaker, address, date of birth");
            alert.show();
        }
    }


    /**
     * Set the view components to their initial values. Fields that hold user input whose values are
     * not set by listeners or event handlers are explicitly set back to their initial value.
     * @param event The event created by the user pressing the Clear All button
     * @return void 
     */
    private void clearView(ActionEvent event) {
        view.getIdValueTField().setText("");
        view.getGenderToggleGroup().selectToggle(view.getFemaleRBtn());
        view.getTypeValueCBox().setValue(null);
        view.getCaretakerValueCBox().setValue(null);
        view.getAddressValueCBox().setValue(null);
        view.getDobToggleGroup().selectToggle(view.getPickDateRBtn());
        view.getMedHistoryTextArea().setText("");
        identifier = "";

    }

}
