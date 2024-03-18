/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.IModel;
import view.MainWindow;

/**
 *
 * @author igbin
 */
public class MainWindowController {
    
    protected MainWindow view;
    protected IModel dao;

    public MainWindowController(MainWindow view, IModel dao) {
        this.view = view;
        this.dao = dao;
    }
    
    
    
    
    
}
