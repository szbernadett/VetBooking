
package model;

import java.io.IOException;
import java.util.List;

/**
 * DAO interface: Defines the methods to be implemented by the actual data access object.
 *
 * @see SerialisationDAO
 * @see IOEXception
 * @see ClassNotFoundException
 */
public interface DAO {
    
    void saveAppointment(Appointment appointment);
    void deleteAppointment(Appointment appointment);
    
    void saveAnimalType(AnimalType animalType);
    void saveRecord(Record record);
    void saveAnimal(Animal animal);

    
    void saveAnimals(List<Animal> animals) throws IOException;
    void saveAnimals() throws IOException;
    void saveAnimalTypes(List<AnimalType> animalTypes) throws IOException;
    void saveAnimalTypes() throws IOException;
    void saveVets(List<Vet> vets) throws IOException;
    void saveAdministrators(List<Administrator> administrators) throws IOException;
    void saveCaretakers(List<Caretaker> caretakers) throws IOException;
    void saveRecords(List<Record> records) throws IOException;
    void saveRecords() throws IOException;
    void saveAppointments(List<Appointment> appointments) throws IOException;
    void saveAppointments() throws IOException;
    void saveAddresses(List<Address> addresses) throws IOException;

    List<Animal> getAllAnimals() throws ClassNotFoundException, IOException;
    List<Record> getAllRecords() throws ClassNotFoundException, IOException;
    List<Caretaker> getAllCaretakers() throws ClassNotFoundException, IOException;
    List<Vet> getAllVets() throws ClassNotFoundException, IOException;
    List<Administrator> getAllAdministrators() throws ClassNotFoundException, IOException;
    List<Address> getAllAddresses() throws ClassNotFoundException, IOException;
    List<AnimalType> getAllAnimalTypes() throws ClassNotFoundException, IOException;
    List<Appointment> getAllAppointments() throws ClassNotFoundException, IOException;  


    
    
    
}
