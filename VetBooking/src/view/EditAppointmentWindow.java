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
    private Appointment appointment;

    public EditAppointmentWindow(Appointment appointment) {
        super();
        initComponents();
        this.appointment = appointment;
    }

    private void initComponents() {
        super.initWindow();
        paidLabel = new Label("Paid:");
        paidCheckBox = new CheckBox();
        GridPane gridPane = super.getBottomGridPane();
        gridPane.add(paidLabel, 4, 4);
        gridPane.add(paidCheckBox, 5, 4);
        paidCheckBox.addEventHandler(ActionEvent.ACTION, this::checkboxSelected);
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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
    
    private void checkboxSelected(ActionEvent event){
        if(paidCheckBox.isSelected()){
            appointment.setPaid(true);
        } else {
            appointment.setPaid(false);
        }
    }

}
