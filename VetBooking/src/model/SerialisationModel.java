/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author igbin
 */
public class SerialisationModel implements IModel {

    @Override
    public void save(ObjectOutputStream out, List<Serializable> objects) throws IOException {
        Object o = objects.get(0);
        Class clazz = o.getClass();
        String fileName = FileName.classToFileName().get(clazz).toString(); // or .getStringValue()
        FileOutputStream fos = new FileOutputStream(fileName);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(objects);
            oos.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Animal> getAllAnimals() throws ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Record> getAllRecords() throws ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Caretaker> getAllCaretakers() throws ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Vet> getAllVets() throws ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Administrator> getAllAdministrators() throws ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Address> getAllAddresses() throws ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AnimalType> getAllAnimalTypes() throws ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Appointment> getAllAppointments() throws ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    enum FileName {
        ANIMAL("animals.ser"),
        ANIMAL_TYPE("animal_types.ser"),
        VET("vets.ser"),
        ADMINISTRATOR("admins.ser"),
        CARETAKER("caretakers.ser"),
        APPOINTMENT("appointments.ser"),
        ADDRESS("addresses.ser"),
        RECORD("records.ser");

        private final String stringValue;
        private static Map<Class, FileName> classToFileName;

        private FileName(String stringValue) {
            this.stringValue = stringValue;
            createMap();

        }

        private void createMap() {
            classToFileName = new HashMap<>(Map.ofEntries(
                    Map.entry(Animal.class, ANIMAL),
                    Map.entry(AnimalType.class, ANIMAL_TYPE),
                    Map.entry(Vet.class, VET),
                    Map.entry(Administrator.class, ADMINISTRATOR),
                    Map.entry(Caretaker.class, CARETAKER),
                    Map.entry(Appointment.class, APPOINTMENT),
                    Map.entry(Address.class, ADDRESS),
                    Map.entry(Record.class, RECORD)
            ));
        }

        public static Map<Class, FileName> classToFileName() {
            return classToFileName;
        }

        public String getStringValue() {
            return stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }

    }
}
