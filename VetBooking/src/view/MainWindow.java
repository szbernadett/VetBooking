/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author igbin
 */
public class MainWindow extends CustomStage {

    private BorderPane root;
    private VBox buttonPaneRight;
    private Scene scene;
    private ScrollPane tableScroll;
    private TableView appointmentTable;
    private Button editBtn;
    private Button deleteBtn;
    private ButtonBar buttonBar;
    private Button bookBtn;
    private Button exitBtn;
    private MenuBar menuBar;
    private Menu animalsMenu;
    private MenuItem viewAndSearchMenuItem;
    private MenuItem registerAnimalMenuItem;
    private MenuItem addNewTypeMenuItem;

    public MainWindow() {
        initWindow();
    }

    @Override
    protected final void initWindow() {
        root = new BorderPane();
        buttonPaneRight = new VBox();
        scene = new Scene(root);
        tableScroll = new ScrollPane();
        appointmentTable = new TableView();
        appointmentTable.setEditable(true);
        TableColumn dateCol = new TableColumn("Date");
        TableColumn timeCol = new TableColumn("Time");
        TableColumn animalCol = new TableColumn("Animal");
        TableColumn locationTypeCol = new TableColumn("Location");
        TableColumn addressCol = new TableColumn("Address");
        TableColumn apptTypeCol = new TableColumn("Type");
        TableColumn paymentCol = new TableColumn("Paid");
        appointmentTable.getColumns().addAll(dateCol,timeCol, animalCol, locationTypeCol, addressCol, apptTypeCol, paymentCol);
        editBtn = new Button("Edit");
        deleteBtn = new Button("Delete");
        bookBtn = new Button("Book");
        exitBtn = new Button("Exit");
        menuBar = new MenuBar();
        animalsMenu = new Menu("Animals");
        viewAndSearchMenuItem = new MenuItem("View and Search ");
        registerAnimalMenuItem = new MenuItem("Register New Animal");
        addNewTypeMenuItem = new MenuItem("Add New Animal Type");
        animalsMenu.getItems().addAll(registerAnimalMenuItem,
                viewAndSearchMenuItem,
                addNewTypeMenuItem);
        menuBar.getMenus().add(animalsMenu);
        tableScroll.setContent(appointmentTable);
        buttonPaneRight.getChildren().addAll(editBtn, deleteBtn, exitBtn);
        buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(10));
        buttonBar.getButtons().addAll(bookBtn);

        root.setTop(menuBar);
        root.setRight(buttonPaneRight);
        root.setCenter(tableScroll);
        root.setBottom(buttonBar);

        setTitle("Main Window");
        setScene(scene);
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public VBox getButtonPaneRight() {
        return buttonPaneRight;
    }

    public void setButtonPaneRight(VBox buttonPaneRight) {
        this.buttonPaneRight = buttonPaneRight;
    }

    public ScrollPane getTableScroll() {
        return tableScroll;
    }

    public void setTableScroll(ScrollPane tableScroll) {
        this.tableScroll = tableScroll;
    }

    public TableView getAppointmentTable() {
        return appointmentTable;
    }

    public void setAppointmentTable(TableView appointmentTable) {
        this.appointmentTable = appointmentTable;
    }

    public Button getEditBtn() {
        return editBtn;
    }

    public void setEditBtn(Button editBtn) {
        this.editBtn = editBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }

    public Button getBookBtn() {
        return bookBtn;
    }

    public void setBookBtn(Button bookBtn) {
        this.bookBtn = bookBtn;
    }

    public Button getExitBtn() {
        return exitBtn;
    }

    public void setExitBtn(Button exitBtn) {
        this.exitBtn = exitBtn;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public Menu getAnimalsMenu() {
        return animalsMenu;
    }

    public void setAnimalsMenu(Menu animalsMenu) {
        this.animalsMenu = animalsMenu;
    }

    public MenuItem getViewAndSearchMenuItem() {
        return viewAndSearchMenuItem;
    }

    public void setViewAndSearchMenuItem(MenuItem viewAndSearchMenuItem) {
        this.viewAndSearchMenuItem = viewAndSearchMenuItem;
    }

    public MenuItem getRegisterAnimalMenuItem() {
        return registerAnimalMenuItem;
    }

    public void setRegisterAnimalMenuItem(MenuItem registerAnimalMenuItem) {
        this.registerAnimalMenuItem = registerAnimalMenuItem;
    }

    public MenuItem getAddNewTypeMenuItem() {
        return addNewTypeMenuItem;
    }

    public void setAddNewTypeMenuItem(MenuItem addNewTypeMenuItem) {
        this.addNewTypeMenuItem = addNewTypeMenuItem;
    }

}
