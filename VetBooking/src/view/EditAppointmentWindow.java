/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

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
import model.Appointment.AppointmentType;

/**
 *
 * @author igbin
 */
public class EditAppointmentWindow extends CustomStage {

    private BorderPane root;
    private Scene mainScene;
    private GridPane mainPane;
    private Label animalLabel;
    private TextField animalValueTextField;
    private ScrollPane listScrollPane;
    private ListView filteredAnimalsListView;
    private Label appointmentTypeLabel;
    private FlowPane apptTypeRBPane;
    private ToggleGroup apptTypeToggleGroup;
    private RadioButton standardRBtn;
    private RadioButton emergencyRBtn;
    private RadioButton prescriptionRBtn;
    private RadioButton vaccinationRBtn;
    private RadioButton checkupRBtn;
    private RadioButton surgeryRBtn;
    private Label locationLabel;
    private Label locationValueLabel;
    private Label vetLabel;
    private ComboBox vetCBox;
    private Label hiddenLabel;
    private Label dateLabel;
    private DatePicker apptDatePicker;
    private ButtonBar buttonBar;
    private Button clearAllBtn;
    private Button cancelBtn;
    private Button saveBtn;

    public EditAppointmentWindow() {
        initWindow();
        show();

    }

    @Override
    protected final void initWindow() {
        root = new BorderPane();
        mainScene = new Scene(root);
        mainPane = new GridPane();
        animalLabel = new Label("Animal:");
        animalValueTextField = new TextField();
        listScrollPane = new ScrollPane();
        filteredAnimalsListView = new ListView<>();
        listScrollPane.setContent(filteredAnimalsListView);
        listScrollPane.setFitToHeight(true);
        listScrollPane.setFitToWidth(true);
        appointmentTypeLabel = new Label("Appointment Type:");
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
        locationLabel = new Label("Location:");
        locationValueLabel = new Label("");
        vetLabel = new Label("Vet:");
        vetCBox = new ComboBox();
        hiddenLabel = new Label("If no available doctors, please book on: 0161 1234 5678");
        hiddenLabel.setVisible(false);

        dateLabel = new Label("Date:");
        apptDatePicker = new DatePicker();

        mainPane.add(animalLabel, 0, 0);
        mainPane.add(animalValueTextField, 1, 0);
        mainPane.add(listScrollPane, 1, 1, 1, 4);
        mainPane.add(appointmentTypeLabel, 2, 0);
        mainPane.add(apptTypeRBPane, 3, 0);
        mainPane.add(locationLabel, 2, 1);
        mainPane.add(locationValueLabel, 3, 1);
        mainPane.add(vetLabel, 2, 2);
        mainPane.add(vetCBox, 3, 2);
        mainPane.add(dateLabel, 2, 3);
        mainPane.add(apptDatePicker, 3, 3);

        buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(10));
        clearAllBtn = new Button("Clear All");
        cancelBtn = new Button("Cancel");
        saveBtn = new Button("Save");
        buttonBar.getButtons().addAll(clearAllBtn, cancelBtn, saveBtn);

        root.setCenter(mainPane);
        root.setBottom(buttonBar);

        setTitle("Book Appointment");
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

    public void setMainScene(Scene scene) {
        this.mainScene = scene;
    }

    public GridPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(GridPane mainPane) {
        this.mainPane = mainPane;
    }

    public Label getAnimalLabel() {
        return animalLabel;
    }

    public void setAnimalLabel(Label animalLabel) {
        this.animalLabel = animalLabel;
    }

    public TextField getAnimalValueTextField() {
        return animalValueTextField;
    }

    public void setAnimalValueTextField(TextField animalValueTextField) {
        this.animalValueTextField = animalValueTextField;
    }

    public ScrollPane getListScrollPane() {
        return listScrollPane;
    }

    public void setListScrollPane(ScrollPane listScrollPane) {
        this.listScrollPane = listScrollPane;
    }

    public ListView getFilteredAnimalsListView() {
        return filteredAnimalsListView;
    }

    public void setFilteredAnimalsListView(ListView filteredAnimalsListView) {
        this.filteredAnimalsListView = filteredAnimalsListView;
    }

    public Label getAppointmentTypeLabel() {
        return appointmentTypeLabel;
    }

    public void setAppointmentTypeLabel(Label appointmentTypeLabel) {
        this.appointmentTypeLabel = appointmentTypeLabel;
    }

    public FlowPane getApptTypeRBPane() {
        return apptTypeRBPane;
    }

    public void setApptTypeRBPane(FlowPane apptTypeRBPane) {
        this.apptTypeRBPane = apptTypeRBPane;
    }

    public ToggleGroup getApptTypeToggleGroup() {
        return apptTypeToggleGroup;
    }

    public void setApptTypeToggleGroup(ToggleGroup apptTypeToggleGroup) {
        this.apptTypeToggleGroup = apptTypeToggleGroup;
    }

    public RadioButton getStandardRBtn() {
        return standardRBtn;
    }

    public void setStandardRBtn(RadioButton standardRBtn) {
        this.standardRBtn = standardRBtn;
    }

    public RadioButton getEmergencyRBtn() {
        return emergencyRBtn;
    }

    public void setEmergencyRBtn(RadioButton emergencyRBtn) {
        this.emergencyRBtn = emergencyRBtn;
    }

    public RadioButton getPrescriptionRBtn() {
        return prescriptionRBtn;
    }

    public void setPrescriptionRBtn(RadioButton prescriptionRBtn) {
        this.prescriptionRBtn = prescriptionRBtn;
    }

    public RadioButton getVaccinationRBtn() {
        return vaccinationRBtn;
    }

    public void setVaccinationRBtn(RadioButton vaccinationRBtn) {
        this.vaccinationRBtn = vaccinationRBtn;
    }

    public RadioButton getCheckupRBtn() {
        return checkupRBtn;
    }

    public void setCheckupRBtn(RadioButton checkupRBtn) {
        this.checkupRBtn = checkupRBtn;
    }

    public RadioButton getSurgeryRBtn() {
        return surgeryRBtn;
    }

    public void setSurgeryRBtn(RadioButton surgeryRBtn) {
        this.surgeryRBtn = surgeryRBtn;
    }

    public Label getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(Label locationLabel) {
        this.locationLabel = locationLabel;
    }

    public Label getLocationValueLabel() {
        return locationValueLabel;
    }

    public void setLocationValueLabel(Label locationValueLabel) {
        this.locationValueLabel = locationValueLabel;
    }

    public Label getVetLabel() {
        return vetLabel;
    }

    public void setVetLabel(Label vetLabel) {
        this.vetLabel = vetLabel;
    }

    public ComboBox getVetCBox() {
        return vetCBox;
    }

    public void setVetCBox(ComboBox vetCBox) {
        this.vetCBox = vetCBox;
    }

    public Label getHiddenLabel() {
        return hiddenLabel;
    }

    public void setHiddenLabel(Label hiddenLabel) {
        this.hiddenLabel = hiddenLabel;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public DatePicker getApptDatePicker() {
        return apptDatePicker;
    }

    public void setApptDatePicker(DatePicker apptDatePicker) {
        this.apptDatePicker = apptDatePicker;
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
