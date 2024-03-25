/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.IModel;
import view.CustomStage;

/**
 *
 * @author igbin
 */
public abstract class Controller<T extends CustomStage> {
    
    protected T view;
    protected IModel dao;

    public Controller() {
    }

    public Controller(T view, IModel dao) {
        this.view = view;
        this.dao = dao;
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

    public IModel getDao() {
        return dao;
    }

    public void setDao(IModel dao) {
        this.dao = dao;
    }
    
    
}
