/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 *
 * @author igbin
 */
public class EditAppointmentWindow extends AddAppointmentWindow {

    private Label paidLabel;
    private CheckBox paidCheckBox;

    public EditAppointmentWindow() {
       // super();
        initComponents();
    }

    private void initComponents() {
        super.initWindow();
        paidLabel = new Label("Paid:");
        paidCheckBox = new CheckBox();
        super.getMainPane().getChildren().remove(super.getListScrollPane());
        super.getMainPane().add(super.getListScrollPane(), 1, 1, 1, 6);
        super.getMainPane().add(paidLabel, 2, 5);
        super.getMainPane().add(paidCheckBox, 3, 5);
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
