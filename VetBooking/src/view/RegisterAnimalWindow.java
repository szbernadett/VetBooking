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
    
    protected BorderPane root;
    protected Scene scene;
    protected GridPane mainPane;
    protected Label idLabel;
    protected TextField idValueTField;
    protected Label typeLabel;
    protected ComboBox<AnimalType> typeValueCBox;
    protected Button addNewTypeBtn;
    protected Label caretakerLabel;
    protected ComboBox<Caretaker> caretakerValueCBox;
    protected Label addressLabel;
    protected ComboBox<Address> addressValueCBox;
    protected Label dateOfBirthLabel;
    protected DatePicker dobDatePicker;
    protected ButtonBar buttonBar;
    protected Button clearAllBtn;
    protected Button cancelBtn;
    protected Button saveBtn;
    
    public RegisterAnimalWindow(ObservableList<AnimalType> animalTypes, 
                           ObservableList<Caretaker> caretakers,
                           ObservableList<Address> addresses) {
        
        root = new BorderPane();
        scene = new Scene(root);
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
        setScene(scene);
        show();
        
    }
    
    
    
    
}
