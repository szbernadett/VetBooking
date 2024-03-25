/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.AnimalType.SpecialistCategory;

/**
 *
 * @author igbin
 */
public class AddNewAnimalTypeWindow extends CustomStage {

    private BorderPane root;
    private Scene mainScene;
    private GridPane mainPane;
    private Label typeNameLabel;
    private TextField typeNameValueTField;
    private Label specialistCategoryLabel;
    private FlowPane checkboxGroup;
    private CheckBox largeCheckBox;
    private CheckBox venomousCheckBox;
    private CheckBox exoticCheckBox;
    private CheckBox aquaticCheckBox;
    private Label maxAgeLabel;
    private ComboBox maxAgeCBox;
    private ButtonBar buttonBar;
    private Button clearAllBtn;
    private Button cancelBtn;
    private Button saveBtn;

    public AddNewAnimalTypeWindow() {
        initWindow();
        show();
    }

    @Override
    protected final void initWindow() {
        root = new BorderPane();
        mainScene = new Scene(root);
        mainPane = new GridPane();
        typeNameLabel = new Label("Name of type:");
        typeNameValueTField = new TextField();
        specialistCategoryLabel = new Label("Specialist categories:");
        checkboxGroup = new FlowPane();
        largeCheckBox = new CheckBox(SpecialistCategory.LARGE.toString());
        venomousCheckBox = new CheckBox(SpecialistCategory.VENOMOUS.toString());
        exoticCheckBox = new CheckBox(SpecialistCategory.EXOTIC.toString());
        aquaticCheckBox = new CheckBox(SpecialistCategory.AQUATIC.toString());
        checkboxGroup.getChildren().addAll(largeCheckBox, venomousCheckBox,
                exoticCheckBox, aquaticCheckBox);
        maxAgeLabel = new Label("Maximum expeted age (years):");
        maxAgeCBox = new ComboBox(FXCollections.observableArrayList());

        mainPane.add(typeNameLabel, 0, 0);
        mainPane.add(typeNameValueTField, 1, 0);
        mainPane.add(specialistCategoryLabel, 0, 1);
        mainPane.add(checkboxGroup, 1, 1);
        mainPane.add(maxAgeLabel, 0, 2);
        mainPane.add(maxAgeCBox, 1, 2);

        buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(10));
        clearAllBtn = new Button("Clear All");
        cancelBtn = new Button("Cancel");
        saveBtn = new Button("Save");
        buttonBar.getButtons().addAll(clearAllBtn, cancelBtn, saveBtn);

        root.setCenter(mainPane);
        root.setBottom(buttonBar);

        setTitle("Add New Animal Type");
        setScene(mainScene);
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public GridPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(GridPane mainPane) {
        this.mainPane = mainPane;
    }

    public Label getTypeNameLabel() {
        return typeNameLabel;
    }

    public void setTypeNameLabel(Label typeNameLabel) {
        this.typeNameLabel = typeNameLabel;
    }

    public TextField getTypeNameValueTField() {
        return typeNameValueTField;
    }

    public void setTypeNameValueTField(TextField typeNameValueTField) {
        this.typeNameValueTField = typeNameValueTField;
    }

    public Label getSpecialistCategoryLabel() {
        return specialistCategoryLabel;
    }

    public void setSpecialistCategoryLabel(Label specialistCategoryLabel) {
        this.specialistCategoryLabel = specialistCategoryLabel;
    }

    public FlowPane getCheckboxGroup() {
        return checkboxGroup;
    }

    public void setCheckboxGroup(FlowPane checkboxGroup) {
        this.checkboxGroup = checkboxGroup;
    }

    public CheckBox getLargeCheckBox() {
        return largeCheckBox;
    }

    public void setLargeCheckBox(CheckBox largeCheckBox) {
        this.largeCheckBox = largeCheckBox;
    }

    public CheckBox getVenomousCheckBox() {
        return venomousCheckBox;
    }

    public void setVenomousCheckBox(CheckBox venomousCheckBox) {
        this.venomousCheckBox = venomousCheckBox;
    }

    public CheckBox getExoticCheckBox() {
        return exoticCheckBox;
    }

    public void setExoticCheckBox(CheckBox exoticCheckBox) {
        this.exoticCheckBox = exoticCheckBox;
    }

    public CheckBox getAquaticCheckBox() {
        return aquaticCheckBox;
    }

    public void setAquaticCheckBox(CheckBox aquaticCheckBox) {
        this.aquaticCheckBox = aquaticCheckBox;
    }

    public Label getMaxAgeLabel() {
        return maxAgeLabel;
    }

    public void setMaxAgeLabel(Label maxAgeLabel) {
        this.maxAgeLabel = maxAgeLabel;
    }

    public ComboBox getMaxAgeCBox() {
        return maxAgeCBox;
    }

    public void setMaxAgeCBox(ComboBox maxAgeCBox) {
        this.maxAgeCBox = maxAgeCBox;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }

    public Button getClearAllBtn() {
        return clearAllBtn;
    }

    public void setClearAllBtn(Button clearAllBtn) {
        this.clearAllBtn = clearAllBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(Button cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(Button saveBtn) {
        this.saveBtn = saveBtn;
    }

}
