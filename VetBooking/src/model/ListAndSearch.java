/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;


/**
 *
 * @author igbin
 */
public interface ListAndSearch {
    
    Record getNextRecord(Record currentRecord);
    Record getPreviousRecord(Record currentRecord);
    void search(String keyword);
}
