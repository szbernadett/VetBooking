/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author igbin
 */
public class ViewAndSearchAnimalsWindow extends CustomStage {

    private BorderPane root;
    private Scene scene;
    private GridPane searchPane;
    private Label searchLabel;
    private TextField searchTextField;
    private Button searchButton;
    private Label searchInstructionLabel;
    private Label recordNavigationLabel;
    private GridPane mainPane;
    private Label nameLabel;
    private Label nameValueLabel;
    private Label animalTypeLabel;
    private Label animalTypeValueLabel;
    private Label dateOfBirthLabel;
    private Label dateOfBirthValueLabel;
    private Label caretakerLabel;
    private Label caretakerValueLabel;
    private Label addressLabel;
    private Label addressValueLabel;
    private Label locationTypeLabel;
    private Label locationTypeValueLabel;
    private Label dateRegisteredLabel;
    private Label dateRegisteredValueLabel;
    private Label medicalHistoryLabel;
    private Label medicalHistoryValueLabel;
    private GridPane bottomButtonPane;
    private GridPane rightButtonPane;
    private Button previousButton;
    private Button nextButton;
    private Button viewAllButton;
    private Button exitButton;

    public ViewAndSearchAnimalsWindow(List<Record> records) {
        initWindow(records);
        show();

    }

    private void initWindow(List<Record> records) {
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
        searchButton = new Button("Go");
        searchInstructionLabel = new Label("Search records by animal name, "
                + "location type (zoo, farm, domestic,"
                + " animal type");
        recordNavigationLabel = new Label("Record " + currentRecordNumber
                + " of " + recordAmount);

        searchPane.add(searchLabel, 0, 0);
        searchPane.add(searchTextField, 1, 0);
        searchPane.add(searchButton, 2, 0);
        searchPane.add(searchInstructionLabel, 1, 1);

        if (currentRecord == null) {
            searchButton.setDisable(true);
            searchInstructionLabel.setText("There are no animal records to display");
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
        //
        //  add code to set values for labels if current record is not null
        //
        bottomButtonPane = new GridPane();
        previousButton = new Button("< Previous");
        nextButton = new Button("Next >");
        bottomButtonPane.add(previousButton, 0, 0);
        bottomButtonPane.add(nextButton, 1, 0);

        rightButtonPane = new GridPane();
        viewAllButton = new Button("View All");
        exitButton = new Button("Exit");
        rightButtonPane.add(viewAllButton, 0,0);
        rightButtonPane.add(exitButton, 0, 1);

        root.setTop(searchPane);
        root.setCenter(mainPane);
        root.setRight(rightButtonPane);
        root.setBottom(bottomButtonPane);

        setTitle("View and Search Animal Records");
        setScene(scene);
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public GridPane getSearchPane() {
        return searchPane;
    }

    public void setSearchPane(GridPane searchPane) {
        this.searchPane = searchPane;
    }

    public Label getSearchLabel() {
        return searchLabel;
    }

    public void setSearchLabel(Label searchLabel) {
        this.searchLabel = searchLabel;
    }

    public TextField getSearchTextField() {
        return searchTextField;
    }

    public void setSearchTextField(TextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public Label getSearchInstructionLabel() {
        return searchInstructionLabel;
    }

    public void setSearchInstructionLabel(Label searchInstructionLabel) {
        this.searchInstructionLabel = searchInstructionLabel;
    }

    public Label getRecordNavigationLabel() {
        return recordNavigationLabel;
    }

    public void setRecordNavigationLabel(Label recordNavigationLabel) {
        this.recordNavigationLabel = recordNavigationLabel;
    }

    public GridPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(GridPane mainPane) {
        this.mainPane = mainPane;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public Label getNameValueLabel() {
        return nameValueLabel;
    }

    public void setNameValueLabel(Label nameValueLabel) {
        this.nameValueLabel = nameValueLabel;
    }

    public Label getAnimalTypeLabel() {
        return animalTypeLabel;
    }

    public void setAnimalTypeLabel(Label animalTypeLabel) {
        this.animalTypeLabel = animalTypeLabel;
    }

    public Label getAnimalTypeValueLabel() {
        return animalTypeValueLabel;
    }

    public void setAnimalTypeValueLabel(Label animalTypeValueLabel) {
        this.animalTypeValueLabel = animalTypeValueLabel;
    }

    public Label getDateOfBirthLabel() {
        return dateOfBirthLabel;
    }

    public void setDateOfBirthLabel(Label dateOfBirthLabel) {
        this.dateOfBirthLabel = dateOfBirthLabel;
    }

    public Label getDateOfBirthValueLabel() {
        return dateOfBirthValueLabel;
    }

    public void setDateOfBirthValueLabel(Label dateOfBirthValueLabel) {
        this.dateOfBirthValueLabel = dateOfBirthValueLabel;
    }

    public Label getCaretakerLabel() {
        return caretakerLabel;
    }

    public void setCaretakerLabel(Label caretakerLabel) {
        this.caretakerLabel = caretakerLabel;
    }

    public Label getCaretakerValueLabel() {
        return caretakerValueLabel;
    }

    public void setCaretakerValueLabel(Label caretakerValueLabel) {
        this.caretakerValueLabel = caretakerValueLabel;
    }

    public Label getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(Label addressLabel) {
        this.addressLabel = addressLabel;
    }

    public Label getAddressValueLabel() {
        return addressValueLabel;
    }

    public void setAddressValueLabel(Label addressValueLabel) {
        this.addressValueLabel = addressValueLabel;
    }

    public Label getLocationTypeLabel() {
        return locationTypeLabel;
    }

    public void setLocationTypeLabel(Label locationTypeLabel) {
        this.locationTypeLabel = locationTypeLabel;
    }

    public Label getLocationTypeValueLabel() {
        return locationTypeValueLabel;
    }

    public void setLocationTypeValueLabel(Label locationTypeValueLabel) {
        this.locationTypeValueLabel = locationTypeValueLabel;
    }

    public Label getDateRegisteredLabel() {
        return dateRegisteredLabel;
    }

    public void setDateRegisteredLabel(Label dateRegisteredLabel) {
        this.dateRegisteredLabel = dateRegisteredLabel;
    }

    public Label getDateRegisteredValueLabel() {
        return dateRegisteredValueLabel;
    }

    public void setDateRegisteredValueLabel(Label dateRegisteredValueLabel) {
        this.dateRegisteredValueLabel = dateRegisteredValueLabel;
    }

    public Label getMedicalHistoryLabel() {
        return medicalHistoryLabel;
    }

    public void setMedicalHistoryLabel(Label medicalHistoryLabel) {
        this.medicalHistoryLabel = medicalHistoryLabel;
    }

    public Label getMedicalHistoryValueLabel() {
        return medicalHistoryValueLabel;
    }

    public void setMedicalHistoryValueLabel(Label medicalHistoryValueLabel) {
        this.medicalHistoryValueLabel = medicalHistoryValueLabel;
    }

    public GridPane getButtonPane() {
        return bottomButtonPane;
    }

    public void setButtonPane(GridPane bottomButtonPane) {
        this.bottomButtonPane = bottomButtonPane;
    }

    public GridPane getRightButtonPane() {
        return rightButtonPane;
    }

    public void setRightButtonPane(GridPane rightButtonPane) {
        this.rightButtonPane = rightButtonPane;
    }

    public Button getPreviousButton() {
        return previousButton;
    }

    public void setPreviousButton(Button previousButton) {
        this.previousButton = previousButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public void setNextButton(Button nextButton) {
        this.nextButton = nextButton;
    }

    public Button getViewAllButton() {
        return viewAllButton;
    }

    public void setViewAllButton(Button viewAllButton) {
        this.viewAllButton = viewAllButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public void setExitButton(Button exitButton) {
        this.exitButton = exitButton;
    }

}
