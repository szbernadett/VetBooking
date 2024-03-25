/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import controller.MainWindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DAOAdapter;
import model.SerialisationDAO;

/**
 *
 * @author igbin
 */
public class Main extends Application {


    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SerialisationDAO sd = new SerialisationDAO();
        DAOAdapter dao = new DAOAdapter(sd);
        MainWindow mainWindow = new MainWindow();
        new MainWindowController(mainWindow, dao);
        mainWindow.show();
    }


    
    
    
}
