
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
 * SerialisationDAO class: Implements the DAO interface to provide serialisation
 * based data access to the veterinary administration system.
 * Once retrieved from the files, the data is accessible via the instance variables
 * of the class. 
 * 
 * @see DAO
 * 
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

    /**
     * Save the list of animals to a file. Find the file name associated with the Animal class.
     * Create a file output stream using the file name to create a connection stream for writing
     * bytes to the specified file. Create an array list and copy the elements of the animals list
     * into it, in case the list was not received as a serializable implementation. Create an object output stream
     * to convert the list to a byte stream and write it to the file using the file output stream. he 
     * object output stream's flush method, which ensures that buffered bytes are written to the file
     *  is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @param animals the list of animals to be saved
     * @return void
     */
    @Override
    public void saveAnimals(List<Animal> animals) throws IOException {
        if (animals != null) {
            String fileName = FileName.classToFileName(Animal.class);
            FileOutputStream fos = new FileOutputStream(fileName);
            animals = new ArrayList<>(animals);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(animals);
                oos.flush();
            }
        }
    }

    /**
     * Save the list of animal types to a file. Find the file name associated with the AnimalType class.
     * Create a file output stream using the file name to create a connection stream for writing
     * bytes to the specified file. Create an array list and copy the elements of the animals list
     * into it, in case the list was not received as a serializable implementation. Create an object output stream
     * to convert the list to a byte stream and write it to the file using the file output stream. The 
     * object output stream's flush method, which ensures that buffered bytes are written to the file
     * is automatically called when closing the stream, handled by the try-with-resources branch. 
     * Exceptions are caught and handled by the calling method. 
     * @param animalTypes the list of animal types to be saved
     * @return void
     */

     /**
      * Save the list of animal types to a file. Find the file name associated with the AnimalType class.
      * Create a file output stream using the file name to create a connection stream for writing
      * bytes to the specified file. Create an array list and copy the elements of the animals list
     * into it, in case the list was not received as a serializable implementation. Create an object output stream
      * to convert the list to a byte stream and write it to the file using the file output stream. The
      * object output stream's flush method, which ensures that buffered bytes are written to the file
      * is automatically called when closing the stream, handled by the try-with-resources branch.
      * Exceptions are caught and handled by the calling method.
      * @param animalTypes the list of animal types to be saved
      * @return void
      */
    @Override
    public void saveAnimalTypes(List<AnimalType> animalTypes) throws IOException {
        if (animalTypes != null) {
            String fileName = FileName.classToFileName(AnimalType.class);
            FileOutputStream fos = new FileOutputStream(fileName);
            animalTypes = new ArrayList<>(animalTypes);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(animalTypes);
            }
        }
    }

    /**
     * Save the list of vets to a file. Find the file name associated with the Vet class.
     * Create a file output stream using the file name to create a connection stream for writing
     * bytes to the specified file. Create an array list and copy the elements of the animals list
     * into it, in case the list was not received as a serializable implementation. Create an object output stream
     * to convert the list to a byte stream and write it to the file using the file output stream. The
     * object output stream's flush method, which ensures that buffered bytes are written to the file
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @param vets the list of vets to be saved
     * @return void
     */
    @Override
    public void saveVets(List<Vet> vets) throws IOException {
        if (vets != null) {
            String fileName = FileName.classToFileName(Vet.class);
            FileOutputStream fos = new FileOutputStream(fileName);
            vets = new ArrayList<>(vets);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(vets);
                oos.flush();
            }
        }
    }

    /**
     * Save the list of administrators to a file. Find the file name associated with the Administrator class.
     * Create a file output stream using the file name to create a connection stream for writing
     * bytes to the specified file. Create an array list and copy the elements of the animals list
     * into it, in case the list was not received as a serializable implementation. Create an object output stream
     * to convert the list to a byte stream and write it to the file using the file output stream. The
     * object output stream's flush method, which ensures that buffered bytes are written to the file
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @param administrators the list of administrators to be saved
     * @return void
     */
    @Override
    public void saveAdministrators(List<Administrator> administrators) throws IOException {
        if (administrators != null) {
            String fileName = FileName.classToFileName(Administrator.class);
            FileOutputStream fos = new FileOutputStream(fileName);
            administrators = new ArrayList<>(administrators);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(administrators);
                oos.flush();
            }
        }
    }

    /**
     * Save the list of caretakers to a file. Find the file name associated with the Caretaker class.
     * Create a file output stream using the file name to create a connection stream for writing
     * bytes to the specified file. Cast the caretakers list to an array list, in case the list
     * was received as a serializable implementation. Create an object output stream
     * to convert the list to a byte stream and write it to the file using the file output stream. The
     * object output stream's flush method, which ensures that buffered bytes are written to the file
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @param caretakers the list of caretakers to be saved
     * @return void
     */
    @Override
    public void saveCaretakers(List<Caretaker> caretakers) throws IOException {
        if (caretakers != null) {
            String fileName = FileName.classToFileName(Caretaker.class);
            FileOutputStream fos = new FileOutputStream(fileName);
            caretakers = new ArrayList<>(caretakers);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(caretakers);
                oos.flush();
            }
        }
    }

    /**
     * Save the list of records to a file. Find the file name associated with the Record class.
     * Create a file output stream using the file name to create a connection stream for writing
     * bytes to the specified file. Cast the records list to an array list, in case the list
     * was received as a serializable implementation. Create an object output stream
     * to convert the list to a byte stream and write it to the file using the file output stream. The
     * object output stream's flush method, which ensures that buffered bytes are written to the file
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @param records the list of records to be saved
     * @return void
     */
    @Override
    public void saveRecords(List<Record> records) throws IOException {
        if (records != null) {
            String fileName = FileName.classToFileName(Record.class);
            FileOutputStream fos = new FileOutputStream(fileName);
            records = new ArrayList<>(records);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(records);
                oos.flush();
            }
        }
    }


    /**
     * Save the list of appointments to a file. Find the file name associated with the Appointment class.
     * Create a file output stream using the file name to create a connection stream for writing
     * bytes to the specified file. Create an array list and copy the elements of the animals list
     * into it, in case the list was not received as a serializable implementation. Create an object output stream
     * to convert the list to a byte stream and write it to the file using the file output stream. The
     * object output stream's flush method, which ensures that buffered bytes are written to the file
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @param appointments the list of appointments to be saved
     * @return void
     */
    @Override
    public void saveAppointments(List<Appointment> appointments) throws IOException {
        if (appointments != null) {
            String fileName = FileName.classToFileName(Appointment.class);
            FileOutputStream fos = new FileOutputStream(fileName);
            appointments = new ArrayList<>(appointments);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(appointments);
                oos.flush();
            }
        }
    }

    /**
     * Save the list of addresses to a file. Find the file name associated with the Address class.
     * Create a file output stream using the file name to create a connection stream for writing
     * bytes to the specified file. Create an array list and copy the elements of the animals list
     * into it, in case the list was not received as a serializable implementation. Create an object output stream
     * to convert the list to a byte stream and write it to the file using the file output stream. The
     * object output stream's flush method, which ensures that buffered bytes are written to the file
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @param addresses the list of addresses to be saved
     * @return void
     */
    @Override
    public void saveAddresses(List<Address> addresses) throws IOException {
        if (addresses != null) {
            String fileName = FileName.classToFileName(Address.class);
            FileOutputStream fos = new FileOutputStream(fileName);
            addresses = new ArrayList<>(addresses);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(addresses);
                oos.flush();
            }
        }
    }

    /**
     * Load the list of animals from a file. Find the file name associated with the Animal class.
     * Create a file input stream using the file name to create a connection stream for reading
     * bytes from the specified file. Create an object input stream to convert the byte stream
     * to a list of animals and read it from the file using the file input stream. The object input
     * stream's readObject method is used to read the object from the file and cast it to an array list
     * of animals. The object input stream's close method, which ensures that the stream is closed
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @return the list of animals
     */
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

    /**
     * Load the list of records from a file. Find the file name associated with the Record class.
     * Create a file input stream using the file name to create a connection stream for reading
     * bytes from the specified file. Create an object input stream to convert the byte stream
     * to a list of records and read it from the file using the file input stream. The object 
     * input stream's readObject method is used to read the object from the file and cast it to 
     * an array list of records. The object input stream's close method, which ensures that the
     * stream is closed is automatically called when closing the stream, handled by the 
     * try-with-resources branch. Exceptions are caught and handled by the calling method.
     * @return the list of records
     */
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
    /** 
    * Load the list of caretakers from a file. Find the file name associated with the Caretaker class.
    * Create a file input stream using the file name to create a connection stream for reading
    * bytes from the specified file. Create an object input stream to convert the byte stream
    * to a list of caretakers and read it from the file using the file input stream. The object input
    * stream's readObject method is used to read the object from the file and cast it to an array list
    * of caretakers. The object input stream's close method, which ensures that the stream is closed
    * is automatically called when closing the stream, handled by the try-with-resources branch.
    * Exceptions are caught and handled by the calling method.
    * @return the list of caretakers
    */
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

    /**
     * Load the list of vets from a file. Find the file name associated with the Vet class.
     * Create a file input stream using the file name to create a connection stream for reading
     * bytes from the specified file. Create an object input stream to convert the byte stream
     * to a list of vets and read it from the file using the file input stream. The object input
     * stream's readObject method is used to read the object from the file and cast it to an array list
     * of vets. The object input stream's close method, which ensures that the stream is closed
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @return the list of vets
     */
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

    /**
     * Load the list of administrators from a file. Find the file name associated with the Administrator class.
     * Create a file input stream using the file name to create a connection stream for reading
     * bytes from the specified file. Create an object input stream to convert the byte stream
     * to a list of administrators and read it from the file using the file input stream. The object input
     * stream's readObject method is used to read the object from the file and cast it to an array list
     * of administrators. The object input stream's close method, which ensures that the stream is closed
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @return the list of administrators
     */
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

    /**
     * Load the list of addresses from a file. Find the file name associated with the Address class.
     * Create a file input stream using the file name to create a connection stream for reading
     * bytes from the specified file. Create an object input stream to convert the byte stream
     * to a list of addresses and read it from the file using the file input stream. The object input
     * stream's readObject method is used to read the object from the file and cast it to an array list
     * of addresses. The object input stream's close method, which ensures that the stream is closed
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @return the list of addresses
     */
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

    /**
     * Load the list of animal types from a file. Find the file name associated with the AnimalType class.
     * Create a file input stream using the file name to create a connection stream for reading
     * bytes from the specified file. Create an object input stream to convert the byte stream
     * to a list of animal types and read it from the file using the file input stream. The object input
     * stream's readObject method is used to read the object from the file and cast it to an array list
     * of animal types. The object input stream's close method, which ensures that the stream is closed
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @return the list of animal types
     */
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

    /**
     * Load the list of appointments from a file. Find the file name associated with the Appointment class.
     * Create a file input stream using the file name to create a connection stream for reading
     * bytes from the specified file. Create an object input stream to convert the byte stream
     * to a list of appointments and read it from the file using the file input stream. The object input
     * stream's readObject method is used to read the object from the file and cast it to an array list
     * of appointments. The object input stream's close method, which ensures that the stream is closed
     * is automatically called when closing the stream, handled by the try-with-resources branch.
     * Exceptions are caught and handled by the calling method.
     * @return the list of appointments
     */
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

    /**
     * Add an appointment to the list of appointments in memory. The list will be saved to a file
     * when the application is closed.
     * @param appointment the appointment to be saved
     * @return void
     */
    @Override
    public void saveAppointment(Appointment appointment) {
        if (appointments != null) {
            appointments.add(appointment);
        }
    }

    /**
     * Delete an appointment from the list of appointments in memory. The list will be saved to a file
     * when the application is closed.
     * @param appointment the appointment to be deleted
     * @return void
     */
    @Override
    public void deleteAppointment(Appointment appointment) {
        if (appointments != null) {
            appointments.remove(appointment);
        }
    }

    /**
     * Add an animal type to the list of animal types in memory. The list will be saved to a file
     * when the application is closed.
     * @param animalType the animal type to be saved
     * @return void
     */
    @Override
    public void saveAnimalType(AnimalType animalType) {
        if (animalTypes != null) {
            animalTypes.add(animalType);
        }
    }

    /**
     * Add a record to the list of records in memory. The list will be saved to a file
     * when the application is closed.
     * @param record the record to be saved
     * @return void
     */
    @Override
    public void saveRecord(Record record) {
        if (records == null) {
            records = new ArrayList<>();
        }
        records.add(record);

    }

    /**
     * Add an animal to the list of animals in memory. The list will be saved to a file
     * when the application is closed.
     * @param animal the animal to be saved
     * @return void
     */
    @Override
    public void saveAnimal(Animal animal) {
        if(animals == null){
            animals = new ArrayList<>();
        }
        animals.add(animal);
    }

    /**
     * Save lists of objects that may have been modified during the application's runtime to files.
     * Exceptions are caught and handled by the calling method.
     * @return void
     */
    @Override
    public void save() throws IOException {
        saveAnimals(animals);
        saveAnimalTypes(animalTypes);
        saveRecords(records);
        saveAppointments(appointments);
    }

}

/**
 * Enum class that associates file names with classes. Uses a static hashmap to map classes to 
 * string value of the FileName enums, which are then used to save and load data. 
 */
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

    /**
     * Static block to initialise the classToFileNameMap
     */
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

    /**
     * Fetch the file name associated with a class
     * @param clazz the class to be converted to a file name
     * @return the file name associated with the class
     */
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
