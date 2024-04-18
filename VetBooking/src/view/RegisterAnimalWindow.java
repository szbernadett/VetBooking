
package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Address;
import model.Animal.Gender;
import model.AnimalType;
import model.Caretaker;

/**
 *
 * @author igbin
 */
public class RegisterAnimalWindow extends CustomStage {

    private BorderPane root;
    private Scene mainScene;
    private GridPane mainPane;
    private Label idLabel;
    private TextField idValueTField;
    private Label genderLabel;
    private HBox genderRadioPane;
    private RadioButton femaleRBtn;
    private RadioButton maleRBtn;
    private RadioButton naRBtn;
    private ToggleGroup genderToggleGroup;
    private Label typeLabel;
    private ComboBox<AnimalType> typeValueCBox;
    private Label caretakerLabel;
    private ComboBox<Caretaker> caretakerValueCBox;
    private Label addressLabel;
    private ComboBox<Address> addressValueCBox;
    private Label dateOfBirthLabel;
    private HBox dobRadioPane;
    private RadioButton pickDateRBtn;
    private RadioButton notApplicableRBtn;
    private ToggleGroup dobToggleGroup;
    private DatePicker dobDatePicker;
    private Label medHistoryLabel;
    private TextArea medHistoryTextArea;
    private ButtonBar buttonBar;
    private Button clearAllBtn;
    private Button cancelBtn;
    private Button saveBtn;

    public RegisterAnimalWindow() {
        initWindow();
    }

    @Override
    protected final void initWindow() {
        root = new BorderPane();
        mainScene = new Scene(root);
        mainScene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());
        mainPane = new GridPane();
        mainPane.setVgap(20);
        mainPane.setHgap(10);
        mainPane.setPadding(new Insets(40, 20, 10, 20));
        mainPane.getColumnConstraints().add(getRightAlignCol());
        
        idLabel = new Label("Identifier / Name:");
        idLabel.getStyleClass().add("label-bold");
        idValueTField = new TextField();
        genderLabel = new Label("Gender");
        genderLabel.getStyleClass().add("label-bold");
        genderRadioPane = new HBox();
        genderToggleGroup = new ToggleGroup();
        femaleRBtn = new RadioButton(Gender.FEMALE.getStringValue());
        femaleRBtn.setToggleGroup(genderToggleGroup);
        maleRBtn = new RadioButton(Gender.MALE.getStringValue());
        maleRBtn.setToggleGroup(genderToggleGroup);
        naRBtn = new RadioButton(Gender.NA.getStringValue());
        naRBtn.setToggleGroup(genderToggleGroup);
        genderRadioPane.getChildren().addAll(femaleRBtn, maleRBtn, naRBtn);
        typeLabel = new Label("Animal type:");
        typeLabel.getStyleClass().add("label-bold");
        typeValueCBox = new ComboBox();
        caretakerLabel = new Label("Caretaker / Owner:");
        caretakerLabel.getStyleClass().add("label-bold");
        caretakerValueCBox = new ComboBox();
        addressLabel = new Label("Address:");
        addressLabel.getStyleClass().add("label-bold");
        addressValueCBox = new ComboBox();
        dateOfBirthLabel = new Label("Date of Birth:");
        dateOfBirthLabel.getStyleClass().add("label-bold");
        dobRadioPane = new HBox();
        dobToggleGroup = new ToggleGroup();
        pickDateRBtn = new RadioButton(DoBRadioLabel.DATE.getStringValue());
        pickDateRBtn.setToggleGroup(dobToggleGroup);
        notApplicableRBtn = new RadioButton(DoBRadioLabel.NA.getStringValue());
        notApplicableRBtn.setToggleGroup(dobToggleGroup);
        dobRadioPane.getChildren().addAll(pickDateRBtn, notApplicableRBtn);
        dobDatePicker = new DatePicker();
        medHistoryLabel = new Label("Medical History: ");
        medHistoryLabel.getStyleClass().add("label-bold");
        medHistoryTextArea = new TextArea();
        medHistoryTextArea.setPrefWidth(300);
        medHistoryTextArea.setPrefHeight(100);

        mainPane.add(idLabel, 0, 0);
        mainPane.add(idValueTField, 1, 0);
        mainPane.add(genderLabel, 0, 1);
        mainPane.add(genderRadioPane, 1, 1);
        mainPane.add(typeLabel, 0, 2);
        mainPane.add(typeValueCBox, 1, 2);
        mainPane.add(caretakerLabel, 0, 3);
        mainPane.add(caretakerValueCBox, 1, 3);
        mainPane.add(addressLabel, 0, 4);
        mainPane.add(addressValueCBox, 1, 4);
        mainPane.add(dateOfBirthLabel, 0, 5);
        mainPane.add(dobRadioPane, 1, 5);
        mainPane.add(dobDatePicker, 1, 6);
        mainPane.add(medHistoryLabel, 0, 7);
        mainPane.add(medHistoryTextArea, 1, 7);

        buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(10, 20, 20, 20));
        clearAllBtn = new Button("Clear All");
        clearAllBtn.getStyleClass().add("button");
        cancelBtn = new Button("Cancel");
        cancelBtn.getStyleClass().add("button");
        saveBtn = new Button("Save");
        saveBtn.getStyleClass().add("button");
        buttonBar.getButtons().addAll(clearAllBtn, cancelBtn, saveBtn);

        root.setCenter(mainPane);
        root.setBottom(buttonBar);

        setTitle("Register New Animal");
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

    public Label getGenderLabel() {
        return genderLabel;
    }

    public void setGenderLabel(Label genderLabel) {
        this.genderLabel = genderLabel;
    }

    public HBox getGenderRadioPane() {
        return genderRadioPane;
    }

    public void setGenderRadioPane(HBox genderRadioPane) {
        this.genderRadioPane = genderRadioPane;
    }

    public RadioButton getFemaleRBtn() {
        return femaleRBtn;
    }

    public void setFemaleRBtn(RadioButton femaleRBtn) {
        this.femaleRBtn = femaleRBtn;
    }

    public RadioButton getMaleRBtn() {
        return maleRBtn;
    }

    public void setMaleRBtn(RadioButton maleRBtn) {
        this.maleRBtn = maleRBtn;
    }

    public RadioButton getNaRBtn() {
        return naRBtn;
    }

    public void setNaRBtn(RadioButton naRBtn) {
        this.naRBtn = naRBtn;
    }

    public ToggleGroup getGenderToggleGroup() {
        return genderToggleGroup;
    }

    public void setGenderToggleGroup(ToggleGroup genderToggleGroup) {
        this.genderToggleGroup = genderToggleGroup;
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

    public HBox getDobRadioPane() {
        return dobRadioPane;
    }

    public void setDobRadioPane(HBox dobRadioPane) {
        this.dobRadioPane = dobRadioPane;
    }

    public RadioButton getPickDateRBtn() {
        return pickDateRBtn;
    }

    public void setPickDateRBtn(RadioButton pickDateRBtn) {
        this.pickDateRBtn = pickDateRBtn;
    }

    public RadioButton getNotApplicableRBtn() {
        return notApplicableRBtn;
    }

    public void setNotApplicableRBtn(RadioButton notApplicableRBtn) {
        this.notApplicableRBtn = notApplicableRBtn;
    }

    public ToggleGroup getDobToggleGroup() {
        return dobToggleGroup;
    }

    public void setDobToggleGroup(ToggleGroup dobToggleGroup) {
        this.dobToggleGroup = dobToggleGroup;
    }

    public DatePicker getDobDatePicker() {
        return dobDatePicker;
    }

    public Label getMedHistoryLabel() {
        return medHistoryLabel;
    }

    public void setMedHistoryLabel(Label medHistoryLabel) {
        this.medHistoryLabel = medHistoryLabel;
    }

    public TextArea getMedHistoryTextArea() {
        return medHistoryTextArea;
    }

    public void setMedHistoryTextArea(TextArea medHistoryTextArea) {
        this.medHistoryTextArea = medHistoryTextArea;
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

    public enum DoBRadioLabel {
        DATE("select date"),
        NA("not applicable");

        private final String stringValue;

        private DoBRadioLabel(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        @Override
        public String toString() {
            return stringValue;

        }

    }

}
