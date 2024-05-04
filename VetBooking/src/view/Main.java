
package view;

import controller.MainWindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SerialisationDAO;

/**
 *  Main class: The entry point of the application. Extends javafx.application.Application 
 *  which has an abstract method, start, that must be implemented in order to run the code. 
 *  
 * @see javafx.application.Application
 * 
 */
public class Main extends Application {


    
    /**
     * Calls the Application class' launch method, which calls other methods including the start 
     * method implemented by this class to start the application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SerialisationDAO model = new SerialisationDAO();
        MainWindow mainWindow = new MainWindow();
        MainWindowController mainWinController = new MainWindowController(mainWindow, model);
        mainWinController.getView().show();
    }


    
    
    
}
