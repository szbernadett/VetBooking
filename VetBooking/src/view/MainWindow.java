
package view;

import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Animal;
import model.Appointment;
import model.Appointment.AppointmentType;
import model.Vet;

/**
 * MainWindow class: Represents the main window of the veterinary administration system.
 *                   The main window display a table of booked appointments and provides
 *                   access to other windows via buttons and menus where data can be 
 *                   manipulated and viewed.
 * 
 * @see CustomStage
 * @see MainWindowController
 * 
 */
public class MainWindow extends CustomStage {

    private BorderPane root;
    private VBox buttonPaneRight;
    private Scene scene;
    private TableView appointmentTable;
    private Button editBtn;
    private Button deleteBtn;
    private HBox buttonPane;
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
        setMinWidth(850);
        root = new BorderPane();
        buttonPaneRight = new VBox();
        buttonPaneRight.setAlignment(Pos.CENTER);
        buttonPaneRight.setSpacing(10);
        buttonPaneRight.setPadding(new Insets(10));
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());
        appointmentTable = new TableView();
        appointmentTable.setPadding(new Insets(20, 10, 10, 20));
        appointmentTable.setEditable(true);
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("date"));
        TableColumn timeCol = new TableColumn("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("time"));
        TableColumn animalCol = new TableColumn("Animal");
        animalCol.setCellValueFactory(new PropertyValueFactory<Appointment, Animal>("animal"));
        TableColumn vetCol = new TableColumn("Vet");
        vetCol.setCellValueFactory(new PropertyValueFactory<Appointment, Vet>("vet"));
        TableColumn locationCol = new TableColumn("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        TableColumn apptTypeCol = new TableColumn("Type");
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<Appointment, AppointmentType>("appointmentType"));
        TableColumn paymentCol = new TableColumn("Paid");
        paymentCol.setCellValueFactory(new PropertyValueFactory<Appointment, Boolean>("paid"));
        paymentCol.setCellFactory(column -> new TableCell<Appointment, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item ? "Yes" : "No");
                }
            }

        });

        appointmentTable.getColumns().addAll(dateCol, timeCol, animalCol, vetCol,
                locationCol, apptTypeCol, paymentCol);
        editBtn = new Button("Edit");
        editBtn.getStyleClass().add("button");
        deleteBtn = new Button("Delete");
        deleteBtn.getStyleClass().add("button");
        bookBtn = new Button("Book");
        bookBtn.getStyleClass().add("button");
        exitBtn = new Button("Exit");
        exitBtn.getStyleClass().add("button");
        menuBar = new MenuBar();
        animalsMenu = new Menu("Animals");
        viewAndSearchMenuItem = new MenuItem("View and Search ");
        registerAnimalMenuItem = new MenuItem("Register New Animal");
        addNewTypeMenuItem = new MenuItem("Add New Animal Type");
        animalsMenu.getItems().addAll(registerAnimalMenuItem,
                viewAndSearchMenuItem,
                addNewTypeMenuItem);
        menuBar.getMenus().add(animalsMenu);
        buttonPaneRight.getChildren().addAll(editBtn, deleteBtn, exitBtn);
        buttonPane = new HBox();
        buttonPane.setPadding(new Insets(20, 10, 20, 10));
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().add(bookBtn);

        root.setTop(menuBar);
        root.setRight(buttonPaneRight);
        root.setCenter(appointmentTable);
        root.setBottom(buttonPane);

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

    public HBox getButtonPane() {
        return buttonPane;
    }

    public void setButtonPane(HBox buttonPane) {
        this.buttonPane = buttonPane;
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
