
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Appointment.AppointmentType;

/**
 *
 * @author igbin
 */
public class AddAppointmentWindow extends CustomStage {

    private BorderPane root;
    private Scene mainScene;
    private GridPane mainPane;
    private GridPane topGridPane;
    private GridPane bottomGridPane;
    private Label animalLabel;
    private TextField animalSearchTextField;
    private ListView filteredAnimalsListView;
    private Label appointmentTypeLabel;
    private GridPane apptTypeGridPane;
    private ToggleGroup apptTypeToggleGroup;
    private RadioButton standardRBtn;
    private RadioButton emergencyRBtn;
    private RadioButton prescriptionRBtn;
    private RadioButton vaccinationRBtn;
    private RadioButton checkupRBtn;
    private RadioButton surgeryRBtn;
    private Label selectedAnimalLbl;
    private Label selectedAnimalNameLbl;
    private Label selectedVetLbl;
    private Label selectedVetNameLbl;
    private Label locationLabel;
    private Label locationValueLabel;
    private Label vetLabel;
    private ListView vetListView;
    private Label dateLabel;
    private DatePicker apptDatePicker;
    private Label timeLabel;
    private ComboBox timeCbox;
    private ButtonBar buttonBar;
    private Button clearAllBtn;
    private Button cancelBtn;
    private Button saveBtn;

    public AddAppointmentWindow() {
        initWindow();

    }

    @Override
    protected final void initWindow() {
        root = new BorderPane();
        mainScene = new Scene(root);
        mainScene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());
        mainPane = new GridPane();
        mainPane.setPadding(new Insets(20));
        
        topGridPane = new GridPane();
        topGridPane.setVgap(10);
        topGridPane.setHgap(10);
        topGridPane.setPadding(new Insets(10));
        
        bottomGridPane = new GridPane();
        bottomGridPane.setVgap(10);
        bottomGridPane.setHgap(10);
        bottomGridPane.setPadding(new Insets(10));
        
        animalLabel = new Label("Animal:");
        animalLabel.getStyleClass().add("label-bold");
        animalSearchTextField = new TextField();
        filteredAnimalsListView = new ListView();
        
        vetLabel = new Label("Vet:");
        vetLabel.getStyleClass().add("label-bold");
        vetListView = new ListView();
        
        appointmentTypeLabel = new Label("Appointment Type:");
        appointmentTypeLabel.getStyleClass().add("label-bold");
        apptTypeGridPane = new GridPane();
        apptTypeGridPane.setVgap(10);
        apptTypeGridPane.setHgap(10);
        apptTypeGridPane.setPadding(new Insets(10, 0, 10, 0));
        apptTypeToggleGroup = new ToggleGroup();
        standardRBtn = new RadioButton(AppointmentType.STANDARD.toString());
        emergencyRBtn = new RadioButton(AppointmentType.EMERGENCY.toString());
        prescriptionRBtn = new RadioButton(AppointmentType.PRESCRIPTION.toString());
        vaccinationRBtn = new RadioButton(AppointmentType.VACCINATION.toString());
        checkupRBtn = new RadioButton(AppointmentType.CHECKUP.toString());
        surgeryRBtn = new RadioButton(AppointmentType.OPERATION.toString());
        standardRBtn.setToggleGroup(apptTypeToggleGroup);
        emergencyRBtn.setToggleGroup(apptTypeToggleGroup);
        prescriptionRBtn.setToggleGroup(apptTypeToggleGroup);
        vaccinationRBtn.setToggleGroup(apptTypeToggleGroup);
        checkupRBtn.setToggleGroup(apptTypeToggleGroup);
        surgeryRBtn.setToggleGroup(apptTypeToggleGroup);
        apptTypeGridPane.add(standardRBtn, 0, 0);
        apptTypeGridPane.add(emergencyRBtn, 1, 0);
        apptTypeGridPane.add(surgeryRBtn, 2, 0);
        apptTypeGridPane.add(checkupRBtn, 0, 1);
        apptTypeGridPane.add(vaccinationRBtn, 1, 1);
        apptTypeGridPane.add(prescriptionRBtn, 2, 1);
        
        selectedAnimalLbl = new Label("Animal:");
        selectedAnimalLbl.getStyleClass().add("label-bold");
        selectedAnimalNameLbl = new Label();
        selectedVetLbl = new Label("Vet:");
        selectedVetLbl.getStyleClass().add("label-bold");
        selectedVetNameLbl = new Label();
        locationLabel = new Label("Location:");
        locationLabel.getStyleClass().add("label-bold");
        locationValueLabel = new Label("");
        

        dateLabel = new Label("Date:");
        dateLabel.getStyleClass().add("label-bold");
        apptDatePicker = new DatePicker();

        timeLabel = new Label("Time:");
        timeLabel.getStyleClass().add("label-bold");
        timeCbox = new ComboBox();

        topGridPane.add(animalLabel, 0, 0);
        topGridPane.add(animalSearchTextField, 1, 0);
        topGridPane.add(filteredAnimalsListView, 1, 1);
        topGridPane.add(vetLabel, 2, 0);
        topGridPane.add(vetListView, 3, 1);
        
        bottomGridPane.add(selectedAnimalLbl, 0, 0);
        bottomGridPane.add(selectedAnimalNameLbl, 1, 0);
        bottomGridPane.add(selectedVetLbl, 0, 1);
        bottomGridPane.add(selectedVetNameLbl, 1, 1);
        bottomGridPane.add(locationLabel, 0, 2);
        bottomGridPane.add(locationValueLabel, 1, 2);
        bottomGridPane.add(appointmentTypeLabel, 0, 3);
        bottomGridPane.add(apptTypeGridPane, 1, 3);
        bottomGridPane.add(dateLabel, 0, 4);
        bottomGridPane.add(apptDatePicker, 1, 4);
        bottomGridPane.add(timeLabel, 2, 4);
        bottomGridPane.add(timeCbox, 3, 4);

        mainPane.add(topGridPane, 0, 0);
        mainPane.add(bottomGridPane, 0, 1);

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

    public GridPane getTopGridPane() {
        return topGridPane;
    }

    public void setTopGridPane(GridPane topGridPane) {
        this.topGridPane = topGridPane;
    }

    public GridPane getBottomGridPane() {
        return bottomGridPane;
    }

    public void setBottomGridPane(GridPane bottomGridPane) {
        this.bottomGridPane = bottomGridPane;
    }

    
    public Label getAnimalLabel() {
        return animalLabel;
    }

    public void setAnimalLabel(Label animalLabel) {
        this.animalLabel = animalLabel;
    }

    public TextField getAnimalSearchTextField() {
        return animalSearchTextField;
    }

    public void setAnimalSearchTextField(TextField animalSearchTextField) {
        this.animalSearchTextField = animalSearchTextField;
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

    public GridPane getApptTypeGridPane() {
        return apptTypeGridPane;
    }

    public void setApptTypeGridPane(GridPane apptTypeRBPane) {
        this.apptTypeGridPane = apptTypeRBPane;
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

    public Label getSelectedAnimalLbl() {
        return selectedAnimalLbl;
    }

    public void setSelectedAnimalLbl(Label selectedAnimalLbl) {
        this.selectedAnimalLbl = selectedAnimalLbl;
    }

    public Label getSelectedAnimalNameLbl() {
        return selectedAnimalNameLbl;
    }

    public void setSelectedAnimalNameLbl(Label selectedAnimalNameLbl) {
        this.selectedAnimalNameLbl = selectedAnimalNameLbl;
    }

    public Label getSelectedVetLbl() {
        return selectedVetLbl;
    }

    public void setSelectedVetLbl(Label selectedVetLbl) {
        this.selectedVetLbl = selectedVetLbl;
    }

    public Label getSelectedVetNameLbl() {
        return selectedVetNameLbl;
    }

    public void setSelectedVetNameLbl(Label selectedVetNameLbl) {
        this.selectedVetNameLbl = selectedVetNameLbl;
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

    public ListView getVetListView() {
        return vetListView;
    }

    public void setVetCBox(ListView vetCBox) {
        this.vetListView = vetCBox;
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

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public ComboBox getTimeCbox() {
        return timeCbox;
    }

    public void setTimeCbox(ComboBox timeCbox) {
        this.timeCbox = timeCbox;
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
