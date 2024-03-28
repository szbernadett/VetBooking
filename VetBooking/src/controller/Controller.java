/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import view.CustomStage;
import model.DAO;
import model.SerialisationDAO;

/**
 *
 * @author igbin
 * @param <T>
 */
public abstract class Controller<T extends CustomStage> {
    
    protected T view;
    protected DAO model;

    public Controller() {
    }

    public Controller(T view, DAO model) {
        this.view = view;
        this.model = model;
    }

    protected abstract void setupEventHandlers();
    
    protected void exitWindow(ActionEvent event){
        view.close();
    }
    
    public Stage getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public DAO getDao() {
        return model;
    }

    public void setDao(SerialisationDAO model) {
        this.model = model;
    }
    
    
}
