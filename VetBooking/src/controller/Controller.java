/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.stage.Stage;
import model.IModel;

/**
 *
 * @author igbin
 */
public abstract class Controller {
    
    private Stage view;
    private IModel dao;

    public Controller() {
    }

    public Controller(Stage view, IModel dao) {
        this.view = view;
        this.dao = dao;
    }

    public Stage getView() {
        return view;
    }

    public void setView(Stage view) {
        this.view = view;
    }

    public IModel getDao() {
        return dao;
    }

    public void setDao(IModel dao) {
        this.dao = dao;
    }
    
    
}
