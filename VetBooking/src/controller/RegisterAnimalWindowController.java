/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author igbin
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

    private void genderToggleSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {
        RadioButton rb = (RadioButton) newValue;
        String text = rb.getText();
        gender = Gender.fromStringValue(text);
    }

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

    private void animalTypeSelected(ActionEvent event) {
        animalType = view.getTypeValueCBox().getValue();
    }

    private void caretakerSelected(ActionEvent event) {
        caretaker = view.getCaretakerValueCBox().getValue();
    }

    private void addressSelected(ActionEvent event) {
        address = view.getAddressValueCBox().getValue();
    }

    private Validate dateCheck() {
        Validate result = Validate.FAIL;
        int years = animalType.getMaxAge();
        LocalDate toCompare = LocalDate.now().minusYears(years);
        if (dateSelected.isAfter(toCompare) || dateSelected.isEqual(toCompare)) {
            result = Validate.OK;
        }

        return result;
    }

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

    private void prepareId() {
        if (identifier != null) {
            identifier = identifier.trim().replace("[^a-zA-Z0-9]", "");
        }
    }

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

    private void clearView(ActionEvent event) {
        view.getIdValueTField().setText("");
        view.getTypeValueCBox().setValue(null);
        view.getCaretakerValueCBox().setValue(null);
        view.getAddressValueCBox().setValue(null);
        view.getDobToggleGroup().selectToggle(view.getPickDateRBtn());
    }

}
