/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Appointment;

/**
 *
 * @author igbin
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
