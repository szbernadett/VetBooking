/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author igbin
 */
public class ViewAndSearchAnimalsWindow extends Stage {
    
    protected BorderPane root;
    protected Scene scene;
    protected GridPane searchPane;
    protected Label searchLabel;
    protected TextField searchTextField;
    protected Button searchButton;
    protected Label searchInstructionLabel;
    protected Label recordNavigationLabel;
    protected GridPane mainPane;
    protected Label nameLabel;
    protected Label nameValueLabel;
    protected Label animalTypeLabel;
    protected Label animalTypeValueLabel;
    protected Label dateOfBirthLabel;
    protected Label dateOfBirthValueLabel;
    protected Label caretakerLabel;
    protected Label caretakerValueLabel;
    protected Label addressLabel;
    protected Label addressValueLabel;
    protected Label locationTypeLabel;
    protected Label locationTypeValueLabel;
    protected Label dateRegisteredLabel;
    protected Label dateRegisteredValueLabel;
    protected Label medicalHistoryLabel;
    protected Label medicalHistoryValueLabel;
    protected GridPane buttonPane;
    protected Button previousButton;
    protected Button nextButton;
    protected Button exitButton;

    public ViewAndSearchAnimalsWindow(ArrayList<Record> records) {
        int recordAmount = records.size();
        System.out.println("records size: " + recordAmount);
        Record currentRecord;
        int currentRecordNumber;
        if (recordAmount > 0) {
            currentRecord = records.get(0);
            currentRecordNumber = 1;
        } else {
            currentRecord = null;
            currentRecordNumber = 0;
        }
        
        root = new BorderPane();
        scene = new Scene(root);
        searchPane = new GridPane();
        searchLabel = new Label("Search:");
        searchTextField = new TextField();
        searchButton = new Button("Find");
        searchInstructionLabel = new Label("Search records by animal name, "
                                         + "location type (zoo, farm, domestic,"
                                         + " animal type");
        recordNavigationLabel = new Label("Record " + currentRecordNumber
                                         +" of " + recordAmount);
        
        searchPane.add(searchLabel, 0, 0);
        searchPane.add(searchTextField, 1, 0);
        searchPane.add(searchButton, 2, 0);
        searchPane.add(searchInstructionLabel, 1, 1);
        
        if (currentRecord == null){
            searchButton.setDisable(true);
            searchInstructionLabel.setText("There are no animal records to "
                                         + "display or search");
        }
        
        mainPane = new GridPane();
        nameLabel = new Label("Name:");
        nameValueLabel = new Label("");
        animalTypeLabel = new Label("Type:");
        animalTypeValueLabel = new Label("");
        dateOfBirthLabel = new Label("Date of Birth:");
        dateOfBirthValueLabel = new Label("");
        caretakerLabel = new Label("Owner / Caretaker:");
        caretakerValueLabel = new Label("");
        addressLabel = new Label("Address:");
        addressValueLabel = new Label("");
        locationTypeLabel = new Label("Address Location Type:");
        locationTypeValueLabel = new Label("");
        dateRegisteredLabel = new Label("Date Registered:");
        dateRegisteredValueLabel = new Label("");
        medicalHistoryLabel = new Label("Medical History:");
        medicalHistoryValueLabel = new Label("");
        
        mainPane.add(nameLabel, 0, 0);
        mainPane.add(nameValueLabel, 1, 0);
        mainPane.add(animalTypeLabel, 0, 1);
        mainPane.add(animalTypeValueLabel, 1, 1);
        mainPane.add(dateOfBirthLabel, 0, 2);
        mainPane.add(dateOfBirthValueLabel, 1, 2);
        mainPane.add(caretakerLabel, 0, 3);
        mainPane.add(caretakerValueLabel, 1, 3);
        mainPane.add(addressLabel, 2, 0);
        mainPane.add(addressValueLabel, 3, 0, 1, 4);
        mainPane.add(dateRegisteredLabel, 2, 4);
        mainPane.add(dateRegisteredValueLabel, 3, 4);
        mainPane.add(medicalHistoryLabel, 2, 5);
        mainPane.add(medicalHistoryValueLabel, 3, 5);
        
        // TODO add code to set values for labels if current record is not null
        
        buttonPane = new GridPane();
        previousButton = new Button("< Previous");
        nextButton = new Button("Next >");
        buttonPane.add(previousButton, 0, 0);
        buttonPane. add(nextButton, 1, 0);
        
        exitButton = new Button("Exit");
        
        root.setTop(searchPane);
        root.setCenter(mainPane);
        root.setRight(exitButton);
        root.setBottom(buttonPane);
        
        setTitle("View and Search Animal Records");
        setScene(scene);
        
        show();
               
        
    }
    
    
   
}
