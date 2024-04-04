/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import model.Address;
import model.Address.LocationType;
import model.Animal;
import model.Animal.DateOfBirth;
import model.AnimalType;
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
    private AnimalType animalType;
    private Caretaker caretaker;
    private Address address;
    private LocalDate dateSelected;
    private LocalDate dateRegistered;

    public RegisterAnimalWindowController(RegisterAnimalWindow view, DAO model) {
        super(view, model);
        setDataChangeHandlers();
        dataToView();
    }

    @Override
    protected final void setDataChangeHandlers() {
        view.getTypeValueCBox().addEventHandler(ActionEvent.ACTION, this::animalTypeSelected);
        view.getCaretakerValueCBox().addEventHandler(ActionEvent.ACTION, this::caretakerSelected);
        view.getAddressValueCBox().addEventHandler(ActionEvent.ACTION, this::addressSelected);
        view.getDobToggleGroup().selectedToggleProperty().addListener(this::dobToggleSelected);
        view.getClearAllBtn().addEventHandler(ActionEvent.ACTION, this::clearView);
        view.getCancelBtn().addEventHandler(ActionEvent.ACTION, this::closeWithoutSave);
        view.getSaveBtn().addEventHandler(ActionEvent.ACTION, this::saveAnimal);
        view.setOnCloseRequest(this::closeWithoutSave);

    }

    @Override
    protected final void dataToView() {
        try {
            view.getTypeValueCBox().setItems((ObservableList) model.getAllAnimalTypes());
            view.getCaretakerValueCBox().setItems((ObservableList) model.getAllCaretakers());
            view.getAddressValueCBox().setItems((ObservableList) model.getAllAddresses());
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

    private void dobToggleSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {

        if (newValue != null) {
            RadioButton rb = (RadioButton) newValue;
            String buttonText = rb.getText();

            if (buttonText.equals(DoBRadioLabel.DATE.getStringValue())) {
                view.getDobDatePicker().setDisable(false);
                dateSelected = view.getDobDatePicker().getValue();
            } else {
                view.getDobDatePicker().setDisable(true);
                dateSelected = null;
            }
        }
    }

    private void dateSelected(ObservableValue<? extends Object> observable,
            Object oldValue, Object newValue) {

        dateSelected = (LocalDate) newValue;
        Validate selected = dateCheck();
        if (selected != null) {
            if (selected == Validate.FAIL) {
                view.getDobDatePicker().setValue(null);
                System.out.println(dateSelected);

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date not accepted");
                alert.setContentText("The selected exceeds the maximum age of"
                        + " this animal type.");
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
        Validate result = null;

        if (animalType != null) {
            int years = animalType.getMaxAge();
            LocalDate toCompare = LocalDate.now().minusYears(years);
            if (dateSelected.isAfter(toCompare) || dateSelected.isEqual(toCompare)) {
                result = Validate.OK;
            } else {
                result = Validate.FAIL;
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Not enough information");
            alert.setContentText("Please select an animal type first.");
            alert.show();
        }

        return result;
    }

    private Validate isDoBSelected() {
        Validate result = Validate.OK;
        if (dateSelected == null) {
            if (view.getDobToggleGroup().getSelectedToggle() == view.getPickDateRBtn()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Missing information");
                alert.setContentText("Please select a date or select \""
                        + DoBRadioLabel.NA.getStringValue() + "\" option to continue.");
                alert.show();
                result = Validate.FAIL;
            }
        }
        return result;
    }

    private Validate checkUnsetValues() {
        Validate result = Validate.OK;
        String id = view.getIdValueTField().getText();
        if (id.isEmpty()
                || animalType == null
                || caretaker == null
                || address == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please provide a name, select an animal type, "
                    + "a caretaker and an address to continue.");
            alert.show();
            result = Validate.FAIL;
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

    private void saveAnimal(ActionEvent event) {
        Validate dob = isDoBSelected();
        if (dob == Validate.OK) {
            Validate fieldValues = checkUnsetValues();
            if (fieldValues == Validate.OK) {
                Animal animal = selectAnimalSubclass();
                if (animal != null) {
                    animal.setAddress(address);
                    animal.setAnimalType(animalType);
                    animal.setCaretaker(caretaker);
                    animal.setDateOfBirth(DateOfBirth.of(dateSelected));
                    animal.setGender(Animal.Gender.MALE);
                    animal.setIdentifier(identifier);
                    model.saveAnimal(animal);

                    Record record = new Record(LocalDate.now(),
                            animal, "", new ArrayList<>());
                    
                    model.saveRecord(record);
                    Alert alert = saveSuccessAlert(POJOName.ANIMAL);
                    alert.show();
                    view.close();
                }
            }
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
