
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
 * Controller class for the AddNewAnimalTypeWindow view. Handles user input and passes on data
 * to the model to save. Warns the user about missing or duplicate data.
 * - view The view to be controlled
 * - model The DAO implementation that communicates with the data source
 * - maxAgeList A list of integers representing the maximum expected age of an animal type
 * - specialistCategories A set of SpecialistCategory enums representing the specialist
 *                          categories of an animal type
 * - animalTypeName A string representing the name of an animal type
 * - selectedMaxAge An integer representing the selected maximum expected age of an animal type
 * - existingNames A list of strings representing the names of currently saved animal types
 * 
 * @see AddNewAnimalTypeWindow
 * @see DAO
 * @see AnimalType
 * @see AnimalType.SpecialistCategory
 * @see AnimalType.AgeConstraint
 * @see Controller
 * 
 */

 
public class AddNewAnimalTypeWindowController extends Controller<AddNewAnimalTypeWindow> {

    private List<Integer> maxAgeList;
    private Set<SpecialistCategory> specialistCategories;
    private String animalTypeName;
    private Integer selectedMaxAge;
    private List<String> existingNames;

    /**
     * Constructor for the AddNewAnimalTypeWindowController class. Calls the superclass constructor
     * and initializes the lists and sets used in the class. Calls the dataToView method to populate
     * the view with data and sets the data change handlers.
     * @param view The view to be controlled
     * @param model The DAO implementation that communicates with the data source
     */
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

    /**
     * Initializes the lists and sets used in the class. The specialistCategories set is initialized
     * as an empty HashSet, the maxAgeList list is initialized as an empty ArrayList, and the existingNames
     * list is populated with the names of all currently saved animal types.  If the animalTypes cannot
     * be retrieved from the data source, the existingNames list is initialized as an empty ArrayList.
     * @see AnimalType.SpecialistCategory
     * @see AnimalType.AgeConstraint
     * @return void
     */
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

    /**
     * Populates the maxAgeList list with integers representing the maximum expected age of an 
     * animal type. The list is populated with integers in ascdending order starting form and including
     * the MIN_AGE_ALL to and including the MAX_AGE_ALL values of the AgeConstraint enum of the AnimalType class.
     * @see AnimalType.AgeConstraint
     * @return void
     */
    private void populateAgeList() {
        if (maxAgeList != null) {
            for (Integer i = AgeConstraint.MIN_AGE_ALL.getValue();
                    i <= AgeConstraint.MAX_AGE_ALL.getValue(); i++) {
                maxAgeList.add(i);
            }
        }
    }

    /**
     * Handles the event of the user selecting a specialist category checkbox. If the checkbox is 
     * selected, the corresponding SpecialistCategory enum is added to the specialistCategories set.
     * If the checkbox is deselected, the corresponding SpecialistCategory enum is removed from the
     * specialistCategories set. 
     * @param event The event of the user selecting a checkbox
     * @see AnimalType.SpecialistCategory
     * @return void
     */
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

    /**
     * Checks if the animalTypeName and selectedMaxAge fields are set. 
     * @return Validate.OK if all fields are set, Validate.FAIL if any of the fields is not set
     * 
     * @see Validate
     */
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

    /** 
     * Saves the animal type data to the data source. If the animal type name is not set,
     * an alert is shown. If the animal type name is set, the checkExistingNames method is called
     * to check if the name already exists. If the name does not exist, the animal type data is saved
     * to the data source and a success alert is shown. If the name already exists, a warning alert 
     * is shown.
     * 
     * @param event The event of the user clicking the Save button
     * @see checkExistingNames()
     * @see checkUnsetValues()
     * @return void
     */
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

    /**
     * Clear view elements of user input. Fields that hold user input whose values are not set by
     *  listeners or event handlers are explicitly set to their initial value.
     * 
     * @param event The event of the user clicking the Clear All button
     * @return void
     */
    private void clearView(ActionEvent event) {
        view.getTypeNameValueTField().setText("");
        for (Node node : view.getCheckboxGroup().getChildren()) {
            ((CheckBox) node).setSelected(false);
        }
        view.getMaxAgeCBox().setValue(null);
        animalTypeName = "";
    }

    /**
     * Check if there is an animal type that already exists with the name entered by the user.
     * If the existingNames list is empty, a warning alert is shown. If the existingNames list
     * contains the name entered by the user, the return value is set to Validate.FAIL. 
     * If the name does not exist, the return value is set to Validate.OK.
     * 
     * @return Validate.OK if the name does not exist, Validate.FAIL if the name already exists
     */
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

    /**
     * Set the selectedMaxAge field to the integer value selected by the user in the maxAgeCBox 
     * combo box.
     * @param event The event of the user selecting a value in the maxAgeCBox combo box
     * @return void
     */
    private void maxAgeSelected(ActionEvent event) {
        selectedMaxAge = (Integer) view.getMaxAgeCBox().getValue();
    }

}
