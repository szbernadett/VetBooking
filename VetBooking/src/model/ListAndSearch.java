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
    
    Record getNext(Record currentRecord);
    Record getPrevious(Record currentRecord);
    void search(String keyword);
}
