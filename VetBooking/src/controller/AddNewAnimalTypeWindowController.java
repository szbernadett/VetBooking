/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import model.AnimalType;
import model.AnimalType.AgeConstraint;
import model.AnimalType.SpecialistCategory;
import model.DAO;
import view.AddNewAnimalTypeWindow;

/**
 *
 * @author igbin
 */
public class AddNewAnimalTypeWindowController extends Controller<AddNewAnimalTypeWindow> {

    private List<Integer> maxAgeList;
    private Set<SpecialistCategory> specialistCategories;
    private String animalTypeName;
    private Integer selectedMaxAge;
    private List<String> existingNames;

    public AddNewAnimalTypeWindowController(AddNewAnimalTypeWindow view, DAO model) {
        super(view, model);
        initLists();
        dataToView();
        setDataChangeHandlers();
    }

    @Override
    protected final void setDataChangeHandlers() {
        view.getAquaticCheckBox().addEventHandler(ActionEvent.ACTION, this::specialistCheckboxSelected);
        view.getVenomousCheckBox().addEventHandler(ActionEvent.ACTION, this::specialistCheckboxSelected);
        view.getExoticCheckBox().addEventHandler(ActionEvent.ACTION, this::specialistCheckboxSelected);
        view.getLargeCheckBox().addEventHandler(ActionEvent.ACTION, this::specialistCheckboxSelected);
        view.getCancelBtn().addEventHandler(ActionEvent.ACTION, this::closeWithoutSave);
        view.getClearAllBtn().addEventHandler(ActionEvent.ACTION, this::clearView);
        view.getSaveBtn().addEventHandler(ActionEvent.ACTION, this::saveAnimalType);
        view.getMaxAgeCBox().addEventHandler(ActionEvent.ACTION, this::maxAgeSelected);
    }

    @Override
    protected final void dataToView() {
        populateAgeList();
        view.getMaxAgeCBox().setItems(FXCollections.observableArrayList(maxAgeList));
    }

    private void initLists() {
        specialistCategories = new HashSet<>();
        maxAgeList = new ArrayList<>();

        List<AnimalType> allAnimalTypes = null;
        try {
            allAnimalTypes = model.getAllAnimalTypes();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }
        if (allAnimalTypes != null) {
            existingNames = allAnimalTypes.stream().map(AnimalType::getTypeName)
                    .collect(Collectors.toList());
        } else {
            existingNames = new ArrayList<>();
        }
    }

    private void populateAgeList() {
        if (maxAgeList != null) {
            for (Integer i = AgeConstraint.MIN_AGE_ALL.getValue();
                    i <= AgeConstraint.MAX_AGE_ALL.getValue(); i++) {
                maxAgeList.add(i);
            }
        }
    }

    private void specialistCheckboxSelected(ActionEvent event) {
        CheckBox chBox = (CheckBox) event.getSource();
        String text = chBox.getText();
        SpecialistCategory specCat = SpecialistCategory.fromStringValue(text);
        if (chBox.isSelected()) {
            specialistCategories.add(specCat);
        } else {
            if (specialistCategories.contains(specCat)) {
                specialistCategories.remove(specCat);
            }
        }
    }

    private Validate checkUnsetValues() {
        Validate result = Validate.OK;
        if (animalTypeName.isBlank()) {
            result = Validate.FAIL;
        }

        if (selectedMaxAge == null) {
            result = Validate.FAIL;
        }

        return result;
    }

    private void saveAnimalType(ActionEvent event) {
        animalTypeName = prepareString(view.getTypeNameValueTField().getText());
        Validate fieldsComplete = checkUnsetValues();
        if (fieldsComplete == Validate.FAIL) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please enter a name and select a maximum "
                    + "expected age to continue.");
            alert.show();
        } else {
            if (checkExistingNames() == Validate.OK) {
                AnimalType animalType = new AnimalType(animalTypeName,
                        selectedMaxAge,
                        specialistCategories);
                model.saveAnimalType(animalType);
                Alert alert = saveSuccessAlert(POJOName.ANIMAL_TYPE);
                alert.show();
                view.close();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Duplicate data");
                alert.setContentText("The selected name already exists. Please "
                        + "enter a different name to continue");
                alert.show();
            }
        }
    }

    private void clearView(ActionEvent event) {
        view.getTypeNameValueTField().setText("");
        for (Node node : view.getCheckboxGroup().getChildren()) {
            ((CheckBox) node).setSelected(false);
        }
        view.getMaxAgeCBox().setValue(null);

    }

    private Validate checkExistingNames() {
        Validate result = Validate.OK;
        if (!existingNames.isEmpty()) {
            if (existingNames.contains(animalTypeName)) {
                result = Validate.FAIL;
            }
        } else {
            result = Validate.FAIL;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing data");
            alert.setContentText("Cannot check for duplicates because of missing data. "
                    + "New animal type cannot be added.");
            alert.show();
        }
        return result;
    }

    private void maxAgeSelected(ActionEvent event) {
        selectedMaxAge = (Integer) view.getMaxAgeCBox().getValue();
    }

}
