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
import javafx.stage.Stage;
import model.AnimalType.SpecialistCategory;

/**
 *
 * @author igbin
 */
public class AddNewAnimalTypeWindow extends Stage{
    
    protected BorderPane root;
    protected Scene scene;
    protected GridPane mainPane;
    protected Label typeNameLabel;
    protected TextField typeNameValueTField;
    protected Label specialistCategoryLabel;
    protected FlowPane checkboxGroup;
    protected CheckBox largeCheckBox;
    protected CheckBox venomousCheckBox;
    protected CheckBox exoticCheckBox;
    protected CheckBox aquaticCheckBox;
    protected Label maxAgeLabel;
    protected ComboBox maxAgeCBox;
    protected ButtonBar buttonBar;
    protected Button clearAllBtn;
    protected Button cancelBtn;
    protected Button saveBtn;

    public AddNewAnimalTypeWindow() {
        root = new BorderPane();
        scene = new Scene(root);
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
        
        buttonBar= new ButtonBar();
        buttonBar.setPadding(new Insets(10));
        clearAllBtn = new Button("Clear All");
        cancelBtn = new Button("Cancel");
        saveBtn = new Button("Save");
        buttonBar.getButtons().addAll(clearAllBtn, cancelBtn, saveBtn);
        
        root.setCenter(mainPane);
        root.setBottom(buttonBar);
        
        setTitle("Add New Animal Type");
        setScene(scene);
        
        show();
        
        
        
        
        
    }
    
    
    
    
}
