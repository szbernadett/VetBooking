/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author igbin
 */
public interface IModel {
    
    void save(ObjectOutputStream out, List<Serializable> objects) throws IOException;
 
    List<Animal> getAllAnimals() throws ClassNotFoundException, IOException;
    List<Record> getAllRecords() throws ClassNotFoundException, IOException;
    List<Caretaker> getAllCaretakers() throws ClassNotFoundException, IOException;
    List<Vet> getAllVets() throws ClassNotFoundException, IOException;
    List<Administrator> getAllAdministrators() throws ClassNotFoundException, IOException;
    List<Address> getAllAddresses() throws ClassNotFoundException, IOException;
    List<AnimalType> getAllAnimalTypes() throws ClassNotFoundException, IOException;
    List<Appointment> getAllAppointments() throws ClassNotFoundException, IOException;  
    
    
    
}
