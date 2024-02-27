/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    protected HBox buttonPaneBottom;
    protected Scene scene;
    protected ScrollPane tableScroll;
    protected TableView appointmentTable;
    protected Button editBtn;
    protected Button deleteBtn;
    protected Button bookBtn;
    protected Button exitBtn;
    protected MenuBar menuBar;
    protected Menu animalsMenu;
    protected MenuItem viewAndSearchMenuItem;

    public MainWindow() {
    
        root = new BorderPane();
        buttonPaneRight = new VBox();
        buttonPaneBottom = new HBox();
        scene = new Scene(root);
        tableScroll = new ScrollPane();
        appointmentTable = new TableView();
        editBtn = new Button("Edit");
        deleteBtn = new Button("Delete");
        bookBtn = new Button("Book");
        exitBtn = new Button("Exit");
        menuBar = new MenuBar();
        animalsMenu = new Menu("Animals");
        viewAndSearchMenuItem = new MenuItem("View and Search");
        
        animalsMenu.getItems().add(viewAndSearchMenuItem);
        menuBar.getMenus().add(animalsMenu);
        tableScroll.setContent(appointmentTable);
        buttonPaneRight.getChildren().addAll(editBtn, deleteBtn);
        buttonPaneBottom.setAlignment(Pos.CENTER_RIGHT);
        buttonPaneBottom.getChildren().addAll(bookBtn, exitBtn);   
        
        root.setTop(menuBar);
        root.setRight(buttonPaneRight);
        root.setCenter(tableScroll);
        root.setBottom(buttonPaneBottom);
        
        setTitle("Main Window");
        setScene(scene);
    }
    
    
    
    
    
    
    
}
