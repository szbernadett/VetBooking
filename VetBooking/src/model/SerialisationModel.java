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
import java.io.Serializable;
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
    public void save(ObjectOutputStream out, List<Serializable> objects) throws IOException {
        Object o = objects.get(0);
        Class clazz = o.getClass();
        String fileName = FileName.classToFileName(clazz); // or .getStringValue()
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
        List<Animal> animals = new ArrayList<>();
        String fileName = FileName.classToFileName(Animal.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            animals = (ArrayList<Animal>) ois.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }

        return animals;
    }

    @Override
    public List<Record> getAllRecords() throws ClassNotFoundException, IOException {
        List<Record> records = new ArrayList<>();
        String fileName = FileName.classToFileName(Record.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            records = (ArrayList<Record>) ois.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }

        return records;
    }

    @Override
    public List<Caretaker> getAllCaretakers() throws ClassNotFoundException, IOException {
        List<Caretaker> caretakers = new ArrayList<>();
        String fileName = FileName.classToFileName(Caretaker.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            caretakers = (ArrayList<Caretaker>) ois.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
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
        List<Administrator> administrators = new ArrayList<>();
        String fileName = FileName.classToFileName(Administrator.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            administrators = (ArrayList<Administrator>) ois.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }

        return administrators;
    }

    @Override
    public List<Address> getAllAddresses() throws ClassNotFoundException, IOException {
        List<Address> addresses = new ArrayList<>();
        String fileName = FileName.classToFileName(Address.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            addresses = (ArrayList<Address>) ois.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }

        return addresses;
    }

    @Override
    public List<AnimalType> getAllAnimalTypes() throws ClassNotFoundException, IOException {
        List<AnimalType> animalTypes = new ArrayList<>();
        String fileName = FileName.classToFileName(AnimalType.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            animalTypes = (ArrayList<AnimalType>) ois.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }

        return animalTypes;
    }

    @Override
    public List<Appointment> getAllAppointments() throws ClassNotFoundException, IOException {
        List<Appointment> appointments = new ArrayList<>();
        String fileName = FileName.classToFileName(Appointment.class);
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            appointments = (ArrayList<Appointment>) ois.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }

        return appointments;
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
        private static Map<Class, FileName> classToFileNameMap;

        private FileName(String stringValue) {
            this.stringValue = stringValue;
            createMap();

        }

        private void createMap() {
            classToFileNameMap = new HashMap<>(Map.ofEntries(
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
}
