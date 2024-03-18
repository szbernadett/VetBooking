/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.IModel;
import view.ViewAndSearchAnimalsWindow;

/**
 *
 * @author igbin
 */
public class ViewAndSearchController {
    
    private ViewAndSearchAnimalsWindow view;
    private IModel dao;

    public ViewAndSearchController(ViewAndSearchAnimalsWindow view, IModel dao) {
        this.view = view;
        this.dao = dao;
    }

    public ViewAndSearchAnimalsWindow getView() {
        return view;
    }

    public void setView(ViewAndSearchAnimalsWindow view) {
        this.view = view;
    }

    public IModel getDao() {
        return dao;
    }

    public void setDao(IModel dao) {
        this.dao = dao;
    }
    
    
    
}
