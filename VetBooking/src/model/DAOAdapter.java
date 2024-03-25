/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author igbin
 */
public class DAOAdapter implements DAO{
    
    private SerialisationDAO adaptedDAO;
  

    public DAOAdapter() {
    }
    
    

    public DAOAdapter(SerialisationDAO sd) {
        this.adaptedDAO = sd;
    }

    public SerialisationDAO getAdaptedDAO() {
        return adaptedDAO;
    }

    public void setAdaptedDAO(SerialisationDAO adaptedDAO) {
        this.adaptedDAO = adaptedDAO;
    }

    public List<Animal> getAnimals() {
        return adaptedDAO.getAnimals();
    }

    public void setAnimals(List<Animal> animals) {
        adaptedDAO.setAnimals(animals);
    }

    public List<AnimalType> getAnimalTypes() {
        return adaptedDAO.getAnimalTypes();
    }

    public void setAnimalTypes(List<AnimalType> animalTypes) {
        adaptedDAO.setAnimalTypes(animalTypes);
    }

    public List<Vet> getVets() {
        return adaptedDAO.getVets();
    }

    public void setVets(List<Vet> vets) {
        adaptedDAO.setVets(vets);
    }

    public List<Caretaker> getCaretakers() {
        return adaptedDAO.getCaretakers();
    }

    public void setCaretakers(List<Caretaker> caretakers) {
        adaptedDAO.setCaretakers(caretakers);
    }

    public List<Administrator> getAdministrators() {
        return adaptedDAO.getAdministrators();
    }

    public void setAdministrators(List<Administrator> administrators) {
        adaptedDAO.setAdministrators(administrators);
    }

    public List<Record> getRecords() {
        return adaptedDAO.getRecords();
    }

    public void setRecords(List<Record> records) {
        adaptedDAO.setRecords(records) ;    
    }

    public List<Address> getAddresses() {
        return adaptedDAO.getAddresses();
    }

    public void setAddresses(List<Address> addresses) {
        adaptedDAO.setAddresses(addresses);
    }

    public List<Appointment> getAppointments() {
        return adaptedDAO.getAppointments();
    }

    public void setAppointments(List<Appointment> appointments) {
        adaptedDAO.setAppointments(appointments);
    }
    
    
    

    @Override
    public void saveAppointment(Appointment appointment) {
        adaptedDAO.saveAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        adaptedDAO.deleteAppointment(appointment);
    }

    @Override
    public void saveAnimalType(AnimalType animalType) {
        adaptedDAO.saveAnimalType(animalType);
    }

    @Override
    public void saveRecord(Record record) {
        adaptedDAO.saveRecord(record);
    }

    @Override
    public void saveAnimals(List<Animal> animals) throws IOException {
        adaptedDAO.saveAnimals(animals);
    }

    @Override
    public void saveAnimalTypes(List<AnimalType> animalTypes) throws IOException {
        adaptedDAO.saveAnimalTypes(animalTypes);
    }

    @Override
    public void saveVets(List<Vet> vets) throws IOException {
        adaptedDAO.saveVets(vets);
    }

    @Override
    public void saveAdministrators(List<Administrator> administrators) throws IOException {
        adaptedDAO.saveAdministrators(administrators);
    }

    @Override
    public void saveCaretakers(List<Caretaker> caretakers) throws IOException {
        adaptedDAO.saveCaretakers(caretakers);
    }

    @Override
    public void saveRecords(List<Record> records) throws IOException {
        adaptedDAO.saveRecords(records);
    }

    @Override
    public void saveAppointments(List<Appointment> appointments) throws IOException {
        adaptedDAO.saveAppointments(appointments);
    }

    @Override
    public void saveAddresses(List<Address> addresses) throws IOException {
        adaptedDAO.saveAddresses(addresses);
    }

    @Override
    public List<Animal> getAllAnimals() throws ClassNotFoundException, IOException {
       return adaptedDAO.getAllAnimals();
    }

    @Override
    public List<Record> getAllRecords() throws ClassNotFoundException, IOException {
        return adaptedDAO.getAllRecords();
    }

    @Override
    public List<Caretaker> getAllCaretakers() throws ClassNotFoundException, IOException {
        return adaptedDAO.getAllCaretakers();
    }

    @Override
    public List<Vet> getAllVets() throws ClassNotFoundException, IOException {
        return adaptedDAO.getAllVets();
    }

    @Override
    public List<Administrator> getAllAdministrators() throws ClassNotFoundException, IOException {
        return adaptedDAO.getAllAdministrators();
    }

    @Override
    public List<Address> getAllAddresses() throws ClassNotFoundException, IOException {
        return adaptedDAO.getAllAddresses();
    }

    @Override
    public List<AnimalType> getAllAnimalTypes() throws ClassNotFoundException, IOException {
        return adaptedDAO.getAllAnimalTypes();
    }

    @Override
    public List<Appointment> getAllAppointments() throws ClassNotFoundException, IOException {
        return adaptedDAO.getAllAppointments();
    }
    
}
