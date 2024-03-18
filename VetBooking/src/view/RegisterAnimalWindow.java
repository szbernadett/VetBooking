/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Address;
import model.AnimalType;
import model.Caretaker;

/**
 *
 * @author igbin
 */
public class RegisterAnimalWindow extends Stage {
    
    private BorderPane root;
    private Scene mainScene;
    private GridPane mainPane;
    private Label idLabel;
    private TextField idValueTField;
    private Label typeLabel;
    private ComboBox<AnimalType> typeValueCBox;
    private Button addNewTypeBtn;
    private Label caretakerLabel;
    private ComboBox<Caretaker> caretakerValueCBox;
    private Label addressLabel;
    private ComboBox<Address> addressValueCBox;
    private Label dateOfBirthLabel;
    private DatePicker dobDatePicker;
    private ButtonBar buttonBar;
    private Button clearAllBtn;
    private Button cancelBtn;
    private Button saveBtn;
    
    public RegisterAnimalWindow(ObservableList<AnimalType> animalTypes, 
                           ObservableList<Caretaker> caretakers,
                           ObservableList<Address> addresses) {
        
        root = new BorderPane();
        mainScene = new Scene(root);
        mainPane = new GridPane();
        idLabel = new Label ("Identifier / Name:");
        idValueTField = new TextField();
        typeLabel = new Label("Animal type:");
        typeValueCBox = new ComboBox(animalTypes);
        addNewTypeBtn = new Button("Add New");
        addNewTypeBtn.setOnAction((ActionEvent e) -> {
            AddNewAnimalTypeWindow addNewAnimalTypeWindow = new AddNewAnimalTypeWindow();
        });
        caretakerLabel = new Label ("Caretaker / Owner:");
        caretakerValueCBox = new ComboBox(caretakers);
        addressLabel = new Label ("Address:");
        addressValueCBox = new ComboBox(addresses);
        dateOfBirthLabel = new Label ("Date of Birth:");
        dobDatePicker = new DatePicker();
        
        
        
        mainPane.add(idLabel, 0, 0);
        mainPane.add(idValueTField, 1, 0, 2, 1);
        mainPane.add(typeLabel, 0, 1);
        mainPane.add(typeValueCBox, 1, 1);
        mainPane.add(addNewTypeBtn, 2, 1);
        mainPane.add(caretakerLabel, 0, 2);
        mainPane.add(caretakerValueCBox, 1, 2, 2, 1);
        mainPane.add(addressLabel, 0, 3);
        mainPane.add(addressValueCBox, 1, 3, 2, 1);
        mainPane.add(dateOfBirthLabel, 0, 4);
        mainPane.add(dobDatePicker, 1, 4, 2, 1); 
        
        buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(10));
        clearAllBtn = new Button("Clear All");
        cancelBtn = new Button("Cancel");
        saveBtn = new Button("Save");
        buttonBar.getButtons().addAll(clearAllBtn, cancelBtn, saveBtn);
        
        root.setCenter(mainPane);
        root.setBottom(buttonBar);
        
        setTitle("Register New Animal");
        setScene(mainScene);
        show();
        
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

    public Label getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(Label idLabel) {
        this.idLabel = idLabel;
    }

    public TextField getIdValueTField() {
        return idValueTField;
    }

    public void setIdValueTField(TextField idValueTField) {
        this.idValueTField = idValueTField;
    }

    public Label getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(Label typeLabel) {
        this.typeLabel = typeLabel;
    }

    public ComboBox<AnimalType> getTypeValueCBox() {
        return typeValueCBox;
    }

    public void setTypeValueCBox(ComboBox<AnimalType> typeValueCBox) {
        this.typeValueCBox = typeValueCBox;
    }

    public Button getAddNewTypeBtn() {
        return addNewTypeBtn;
    }

    public void setAddNewTypeBtn(Button addNewTypeBtn) {
        this.addNewTypeBtn = addNewTypeBtn;
    }

    public Label getCaretakerLabel() {
        return caretakerLabel;
    }

    public void setCaretakerLabel(Label caretakerLabel) {
        this.caretakerLabel = caretakerLabel;
    }

    public ComboBox<Caretaker> getCaretakerValueCBox() {
        return caretakerValueCBox;
    }

    public void setCaretakerValueCBox(ComboBox<Caretaker> caretakerValueCBox) {
        this.caretakerValueCBox = caretakerValueCBox;
    }

    public Label getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(Label addressLabel) {
        this.addressLabel = addressLabel;
    }

    public ComboBox<Address> getAddressValueCBox() {
        return addressValueCBox;
    }

    public void setAddressValueCBox(ComboBox<Address> addressValueCBox) {
        this.addressValueCBox = addressValueCBox;
    }

    public Label getDateOfBirthLabel() {
        return dateOfBirthLabel;
    }

    public void setDateOfBirthLabel(Label dateOfBirthLabel) {
        this.dateOfBirthLabel = dateOfBirthLabel;
    }

    public DatePicker getDobDatePicker() {
        return dobDatePicker;
    }

    public void setDobDatePicker(DatePicker dobDatePicker) {
        this.dobDatePicker = dobDatePicker;
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
