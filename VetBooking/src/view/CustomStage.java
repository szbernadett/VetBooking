/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author igbin
 */
public abstract class CustomStage extends Stage {

    public <T extends Event> void setEventHandler(Node node, EventType<T> type, EventHandler<T> handler){
        node.addEventHandler(type, handler);
    }
}
