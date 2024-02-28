/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Animal;
import model.Appointment.AppointmentType;
import model.Vet;

/**
 *
 * @author igbin
 */
public class AddBookingWindow extends Stage{
    
    protected BorderPane root;
    protected Scene scene;
    protected GridPane mainPane;
    protected Label animalLabel;
    protected TextField animalValueTextField;
    protected ScrollPane listScrollPane;
    protected ListView filteredAnimalsListView;
    protected Label appointmentTypeLabel;
    protected FlowPane apptTypeRBPane;
    protected ToggleGroup apptTypeToggleGroup;
    protected RadioButton standardRBtn;
    protected RadioButton emergencyRBtn;
    protected RadioButton prescriptionRBtn;
    protected RadioButton vaccinationRBtn;
    protected RadioButton checkupRBtn;
    protected RadioButton surgeryRBtn;
    protected Label vetLabel;
    protected ComboBox vetCBox;
    protected Label hiddenLabel;
    protected Label dateLabel;
    protected DatePicker apptDatePicker;
    protected ButtonBar buttonBar;
    protected Button clearAllBtn;
    protected Button cancelBtn;
    protected Button saveBtn; 

    public AddBookingWindow(ObservableList<Animal> animals, ObservableList<Vet> vets) {
        root = new BorderPane();
        scene = new Scene(root);
        mainPane = new GridPane();
        animalLabel = new Label("Animal:");
        animalValueTextField = new TextField();
        listScrollPane = new ScrollPane();
        filteredAnimalsListView = new ListView<>(animals);
        listScrollPane.setContent(filteredAnimalsListView);
        listScrollPane.setFitToHeight(true);
        listScrollPane.setFitToWidth(true);
        appointmentTypeLabel = new Label ("Appointment Type:");
        apptTypeRBPane = new FlowPane();
        apptTypeToggleGroup = new ToggleGroup();
        standardRBtn = new RadioButton(AppointmentType.STANDARD.toString());
        emergencyRBtn = new RadioButton(AppointmentType.EMERGENCY.toString());
        prescriptionRBtn = new RadioButton(AppointmentType.PRESCRIPTION.toString());
        vaccinationRBtn = new RadioButton(AppointmentType.VACCINATION.toString());
        checkupRBtn = new RadioButton(AppointmentType.CHECKUP.toString());
        surgeryRBtn = new RadioButton(AppointmentType.SURGERY.toString());
        standardRBtn.setToggleGroup(apptTypeToggleGroup);
        emergencyRBtn.setToggleGroup(apptTypeToggleGroup);
        prescriptionRBtn.setToggleGroup(apptTypeToggleGroup);
        vaccinationRBtn.setToggleGroup(apptTypeToggleGroup);
        checkupRBtn.setToggleGroup(apptTypeToggleGroup);
        surgeryRBtn.setToggleGroup(apptTypeToggleGroup);
        apptTypeRBPane.getChildren().addAll(standardRBtn, emergencyRBtn, 
                                             prescriptionRBtn, vaccinationRBtn, 
                                             checkupRBtn, surgeryRBtn);
        
        vetLabel = new Label("Vet:");
        vetCBox = new ComboBox(vets);
        hiddenLabel = new Label("If no available doctors, please book on: 0161 1234 5678");
        hiddenLabel.setVisible(false);
        
        dateLabel = new Label("Date:");
        apptDatePicker = new DatePicker();
        
        mainPane.add(animalLabel, 0, 0);
        mainPane.add(animalValueTextField, 1, 0);
        mainPane.add(listScrollPane, 1, 1);
        mainPane.add(appointmentTypeLabel, 2, 0);
        mainPane.add(apptTypeRBPane, 3, 0);
        mainPane.add(vetLabel, 0, 2);
        mainPane.add(vetCBox, 1, 2);
        mainPane.add(dateLabel, 2, 1);
        mainPane.add(apptDatePicker, 3, 1);
        
        buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(10));
        clearAllBtn = new Button("Clear All");
        cancelBtn = new Button("Cancel");
        saveBtn = new Button("Save");
        buttonBar.getButtons().addAll(clearAllBtn, cancelBtn, saveBtn);
        
        root.setCenter(mainPane);
        root.setBottom(buttonBar);
        
        setTitle("Book Appointment");
        setScene(scene);
        
        show();
        
    }
    
    
    
    
    
    
    
}
