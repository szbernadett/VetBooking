/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.util.List;


/**
 *
 * @author igbin
 */
public interface ListAndSearch {
    
    Record getNextRecord(Record currentRecord, List<Record> currentList);
    Record getPreviousRecord(Record currentRecord, List<Record> currentList);
    void search(String keyword);
}
