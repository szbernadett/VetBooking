/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author igbin
 */
public class MainWindow extends Stage {
    
    protected BorderPane root;
    protected VBox buttonPaneRight;
    protected Scene scene;
    protected ScrollPane tableScroll;
    protected TableView appointmentTable;
    protected Button editBtn;
    protected Button deleteBtn;
    protected ButtonBar buttonBar;
    protected Button bookBtn;
    protected Button exitBtn;
    protected MenuBar menuBar;
    protected Menu animalsMenu;
    protected MenuItem viewAndSearchMenuItem;
    protected MenuItem registerAnimalMenuItem;

    public MainWindow() {
    
        root = new BorderPane();
        buttonPaneRight = new VBox();
        scene = new Scene(root);
        tableScroll = new ScrollPane();
        appointmentTable = new TableView();
        editBtn = new Button("Edit");
        deleteBtn = new Button("Delete");
        bookBtn = new Button("Book");
        bookBtn.setOnAction(e -> {
            AddBookingWindow addBookingWindow = new AddBookingWindow(FXCollections.observableArrayList(),
                    FXCollections.observableArrayList()); 
                    });
        exitBtn = new Button("Exit");
        menuBar = new MenuBar();
        animalsMenu = new Menu("Animals");
        viewAndSearchMenuItem = new MenuItem("View and Search ");
        registerAnimalMenuItem = new MenuItem("Register New Animal");
        registerAnimalMenuItem.setOnAction(e -> new RegisterAnimalWindow(FXCollections.observableArrayList(),
        FXCollections.observableArrayList(),  FXCollections.observableArrayList()));
        
        animalsMenu.getItems().addAll(registerAnimalMenuItem, viewAndSearchMenuItem);
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
    
    
    
    
    
    
    
}
