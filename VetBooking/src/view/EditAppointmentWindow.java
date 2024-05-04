
package view;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * EditAppointmentWindow class: Represents a window for editing an appointment in the veterinary administration system.
 *                              Holds the same graphical components in the same arrangement as its superclass
 *                              AddAppointmentWindow with the addition of a CheckBox for marking the 
 *                              appointment as paid. 
 * 
 * @see AddAppointmentWindow
 * @see CustomStage
 * @see EditAppointmentWindowController
 */
public class EditAppointmentWindow extends AddAppointmentWindow {

    private Label paidLabel;
    private CheckBox paidCheckBox;

    public EditAppointmentWindow() {
        super();
        initComponents();
    }

    private void initComponents() {
        super.initWindow();
        paidLabel = new Label("Paid:");
        paidCheckBox = new CheckBox();
        GridPane bGridPane = super.getBottomGridPane();
        bGridPane.add(paidLabel, 4, 4);
        bGridPane.add(paidCheckBox, 5, 4);
        bGridPane.getChildren().remove(super.getClearAllBtn());
        super.getTopGridPane().getChildren().remove(super.getAnimalSearchTextField());  
        setTitle("Edit Appointment");
    }

    public Label getPaidLabel() {
        return paidLabel;
    }

    public void setPaidLabel(Label paidLabel) {
        this.paidLabel = paidLabel;
    }

    public CheckBox getPaidCheckBox() {
        return paidCheckBox;
    }

    public void setPaidCheckBox(CheckBox paidCheckBox) {
        this.paidCheckBox = paidCheckBox;
    }


    
    
    

}
