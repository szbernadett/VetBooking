/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import javafx.event.Event;

/**
 *
 * @author igbin
 * @param <T>
 */
@FunctionalInterface
public interface TheEventHandler<T extends Event> {
    void handleEvent(T event);
}
