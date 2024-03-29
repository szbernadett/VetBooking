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
import javafx.collections.FXCollections;

/**
 *
 * @author igbin
 */
public class SerialisationDAO implements DAO {

    private List<Animal> animals;
    private List<AnimalType> animalTypes;
    private List<Vet> vets;
    private List<Administrator> administrators;
    private List<Caretaker> caretakers;
    private List<Record> records;
    private List<Appointment> appointments;
    private List<Address> addresses;

    public SerialisationDAO() {
    }

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
        if (animals == null) {
            String fileName = FileName.classToFileName(Animal.class);
            FileInputStream fis = new FileInputStream(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                animals = (ArrayList<Animal>) ois.readObject();
                animals = FXCollections.observableArrayList(animals);
            }
        }
        return animals;
    }

    @Override
    public List<Record> getAllRecords() throws ClassNotFoundException, IOException {
        if (records == null) {
            String fileName = FileName.classToFileName(Record.class);
            FileInputStream fis = new FileInputStream(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                records = (ArrayList<Record>) ois.readObject();
                records = FXCollections.observableArrayList(records);
            }
        }
        return records;
    }

    @Override
    public List<Caretaker> getAllCaretakers() throws ClassNotFoundException, IOException {
        if (caretakers == null) {
            String fileName = FileName.classToFileName(Caretaker.class);
            FileInputStream fis = new FileInputStream(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                caretakers = (ArrayList<Caretaker>) ois.readObject();
                caretakers = FXCollections.observableArrayList(caretakers);
            }
        }
        return caretakers;
    }

    @Override
    public List<Vet> getAllVets() throws ClassNotFoundException, IOException {
        if (vets == null) {
            String fileName = FileName.classToFileName(Vet.class);
            FileInputStream fis = new FileInputStream(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                vets = (ArrayList<Vet>) ois.readObject();
                vets = FXCollections.observableArrayList(vets);

            }
        }
        return vets;
    }

    @Override
    public List<Administrator> getAllAdministrators() throws ClassNotFoundException, IOException {
        if (administrators == null) {
            String fileName = FileName.classToFileName(Administrator.class);
            FileInputStream fis = new FileInputStream(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                administrators = (ArrayList<Administrator>) ois.readObject();
                administrators = FXCollections.observableArrayList(administrators);

            }
        }
        return administrators;
    }

    @Override
    public List<Address> getAllAddresses() throws ClassNotFoundException, IOException {
        if (addresses == null) {
            String fileName = FileName.classToFileName(Address.class);
            FileInputStream fis = new FileInputStream(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                addresses = (ArrayList<Address>) ois.readObject();
                addresses = FXCollections.observableArrayList(addresses);
            }
        }
        return addresses;
    }

    @Override
    public List<AnimalType> getAllAnimalTypes() throws ClassNotFoundException, IOException {
        if (animalTypes == null) {
            String fileName = FileName.classToFileName(AnimalType.class);
            FileInputStream fis = new FileInputStream(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                animalTypes = (ArrayList<AnimalType>) ois.readObject();
                animalTypes = FXCollections.observableArrayList(animalTypes);

            }
        }
        return animalTypes;
    }

    @Override
    public List<Appointment> getAllAppointments() throws ClassNotFoundException, IOException {
        if (appointments == null) {
            String fileName = FileName.classToFileName(Appointment.class);
            FileInputStream fis = new FileInputStream(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                appointments = (ArrayList<Appointment>) ois.readObject();
                appointments = FXCollections.observableArrayList(appointments);
            }
        }
        return appointments;
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        if (appointments != null) {
            appointments.add(appointment);
        }
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        if (appointments != null) {
            appointments.remove(appointment);
        }
    }

    @Override
    public void saveAnimalType(AnimalType animalType) {
        if (animalTypes != null) {
            animalTypes.add(animalType);
        }
    }

    @Override
    public void saveRecord(Record record) {
        if (records == null) {
            records = new ArrayList<>();
        }
        records.add(record);

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
