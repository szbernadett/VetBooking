/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author igbin
 */
public class SerialisationModel implements IModel {

    @Override
    public void saveAnimals(List<Animal> animals) throws IOException {
        String fileName = FileName.classToFileName(Animal.class); 
        FileOutputStream fos = new FileOutputStream(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(animals);
            oos.flush();
        } 
    }

    
    @Override
    public void saveAnimalTypes(List<AnimalType> animalTypes) throws IOException {
        String fileName = FileName.classToFileName(AnimalType.class); 
        FileOutputStream fos = new FileOutputStream(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(animalTypes);
            oos.flush();
        } 
    }

    @Override
    public void saveVets(List<Vet> vets) throws IOException {
        String fileName = FileName.classToFileName(Vet.class); 
        FileOutputStream fos = new FileOutputStream(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(vets);
            oos.flush();
        }
    }

    @Override
    public void saveAdministrators(List<Administrator> administrators) throws IOException {
        String fileName = FileName.classToFileName(Administrator.class); 
        FileOutputStream fos = new FileOutputStream(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(administrators);
            oos.flush();
        }
    }

    @Override
    public void saveCaretakers(List<Caretaker> caretakers) throws IOException {
        String fileName = FileName.classToFileName(Caretaker.class); 
        FileOutputStream fos = new FileOutputStream(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(caretakers);
            oos.flush();
        }
    }

    @Override
    public void saveRecords(List<Record> records) throws IOException {
        String fileName = FileName.classToFileName(Record.class); 
        FileOutputStream fos = new FileOutputStream(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(records);
            oos.flush();
        }
    }

    @Override
    public void saveAppointments(List<Appointment> appointments) throws IOException {
        String fileName = FileName.classToFileName(Appointment.class); 
        FileOutputStream fos = new FileOutputStream(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(appointments);
            oos.flush();
        }
    }

    @Override
    public void saveAddresses(List<Address> addresses) throws IOException {
        String fileName = FileName.classToFileName(Address.class); 
        FileOutputStream fos = new FileOutputStream(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(addresses);
            oos.flush();
        }
    }
    
    @Override
    public List<Animal> getAllAnimals() throws ClassNotFoundException, IOException {
        List<Animal> animals;
        String fileName = FileName.classToFileName(Animal.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            animals = (ArrayList<Animal>) ois.readObject();
        } 

        return animals;
    }

    @Override
    public List<Record> getAllRecords() throws ClassNotFoundException, IOException {
        List<Record> records;
        String fileName = FileName.classToFileName(Record.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            records = (ArrayList<Record>) ois.readObject();
        }

        return records;
    }

    @Override
    public List<Caretaker> getAllCaretakers() throws ClassNotFoundException, IOException {
        List<Caretaker> caretakers;
        String fileName = FileName.classToFileName(Caretaker.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            caretakers = (ArrayList<Caretaker>) ois.readObject();
        } 

        return caretakers;
    }

    @Override
    public List<Vet> getAllVets() throws ClassNotFoundException, IOException {
        List<Vet> vets = new ArrayList<>();
        String fileName = FileName.classToFileName(Vet.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            vets = (ArrayList<Vet>) ois.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }

        return vets;
    }

    @Override
    public List<Administrator> getAllAdministrators() throws ClassNotFoundException, IOException {
        List<Administrator> administrators;
        String fileName = FileName.classToFileName(Administrator.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            administrators = (ArrayList<Administrator>) ois.readObject();
        }

        return administrators;
    }

    @Override
    public List<Address> getAllAddresses() throws ClassNotFoundException, IOException {
        List<Address> addresses;
        String fileName = FileName.classToFileName(Address.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            addresses = (ArrayList<Address>) ois.readObject();
        }

        return addresses;
    }

    @Override
    public List<AnimalType> getAllAnimalTypes() throws ClassNotFoundException, IOException {
        List<AnimalType> animalTypes;
        String fileName = FileName.classToFileName(AnimalType.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            animalTypes = (ArrayList<AnimalType>) ois.readObject();
        }

        return animalTypes;
    }

    @Override
    public List<Appointment> getAllAppointments() throws ClassNotFoundException, IOException {
        List<Appointment> appointments;
        String fileName = FileName.classToFileName(Appointment.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            appointments = (ArrayList<Appointment>) ois.readObject();
        } 

        return appointments;
    }

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
        private static final Map<Class, FileName> classToFileNameMap = new HashMap<>();
        
        static {
                classToFileNameMap.put(Animal.class, ANIMAL);
                classToFileNameMap.put(AnimalType.class, ANIMAL_TYPE);
                classToFileNameMap.put(Vet.class, VET);
                classToFileNameMap.put(Administrator.class, ADMINISTRATOR);
                classToFileNameMap.put(Caretaker.class, CARETAKER);
                classToFileNameMap.put(Appointment.class, APPOINTMENT);
                classToFileNameMap.put(Address.class, ADDRESS);
                classToFileNameMap.put(Record.class, RECORD);     
        }

        private FileName(String stringValue) {
            this.stringValue = stringValue;

        }
        
        public static String classToFileName(Class clazz) {
            String fileName = classToFileNameMap.get(clazz).getStringValue();

            return fileName;
        }

        public String getStringValue() {
            return stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }

    }

