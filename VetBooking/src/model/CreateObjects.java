/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import model.Address.LocationType;
import model.Animal.Gender;
import model.AnimalType.SpecialistCategory;

/**
 *
 * @author igbin
 */
public class CreateObjects {
    
    public static void main(String[] args){
    
    City manchester = new City("Manchester");
    City oldham = new City("Oldham");
    City middleton = new City("Middleton");
    
    Postcode manc1 = new Postcode("MZ4 6GH", manchester);
    Postcode manc2= new Postcode("MZ5 3JL", manchester);
    Postcode manc3 = new Postcode("MZ6 8PN", manchester);
    Postcode manc4 = new Postcode("MZ7 2TF", manchester);
    Postcode manc5= new Postcode("MZ8 5RQ", manchester);
    Postcode manc6 = new Postcode("MZ1 4AB", manchester);
    
    Postcode old1 = new Postcode("OL1 9AB", oldham);
    Postcode midd1 = new Postcode("MD2 8EF", middleton);
    
    Address manczoo = new Address("Lion's Roar Zoo", "25 Wild way", manc6, LocationType.ZOO);
    Address farm1 = new Address("Oldham Fields Farm", "20 Rural Road", old1, LocationType.FARM);
    Address farm2 = new Address("Middleton Meadows Farm", "30 Pasture Lane", midd1, LocationType.FARM);
    Address dom1 = new Address("123 Rosewood crescent","", manc1, LocationType.DOMESTIC);
    Address dom2 = new Address("63 Maple avenue","", manc2, LocationType.DOMESTIC);
    Address dom3 =  new Address("85 Willow grove", "", manc3, LocationType.DOMESTIC);
    Address dom4 = new Address("11 Oakwood terrace", "", manc4, LocationType.DOMESTIC);
    Address dom5 = new Address("66 Elm street", "", manc5, LocationType.DOMESTIC);
    
    Administrator admin1 = new Administrator("Bernadett", "Szito", "bernadett.szito@gmail.com", "07448448612");
    Vet vet1 = new Vet("Emily", "Sinclair", "sinclaire@mail.co.uk", "07700900123", 
                       new HashSet<>(Arrays.asList(SpecialistCategory.EXOTIC, 
                                                    SpecialistCategory.VENOMOUS)));
    Vet vet2 = new Vet("James", "Fletcher", "fletcherj@mail.co.uk", "07912345678",
                        new HashSet<>(Arrays.asList(SpecialistCategory.LARGE)));
    Vet vet3 = new Vet("Sarah", "Patel", "spatel@mail.co.uk", "07845678901",
                        new HashSet<>(Arrays.asList(SpecialistCategory.LARGE, 
                                                    SpecialistCategory.AQUATIC)));
    Vet vet4 = new Vet("Rachel", "Thompson", "rachelt@mail.com", "07555112233",
                        new HashSet<>(Arrays.asList(SpecialistCategory.EXOTIC, 
                                SpecialistCategory.AQUATIC)));
    Vet vet5 = new Vet("Oliver", "Reynolds", "oreynolds@mail.com", "07665877695",
                        new HashSet<>(Arrays.asList(SpecialistCategory.AQUATIC,
                                SpecialistCategory.EXOTIC, SpecialistCategory.LARGE,
                                SpecialistCategory.VENOMOUS)));
    Vet vet6 = new Vet("Andrea", "Reynolds", "areynolds@mail.com", "07665877694",
                        new HashSet<>(Arrays.asList(SpecialistCategory.AQUATIC,
                                SpecialistCategory.EXOTIC, SpecialistCategory.LARGE,
                                SpecialistCategory.VENOMOUS)));
    
    
    Caretaker care1 = new Caretaker("Amrit", "Kour", "amritk@mail.com", "07983654321");
    Caretaker care2 = new Caretaker("Amira", "Basa", "pinkprincess@mail.com", "07880654321");
    Caretaker care3 = new Caretaker("Destiny", "Osadiaye", "destiny@mail.com", "07999554321");
    Caretaker care4 = new Caretaker("Joseph", "Igbinoba", "ninobrown@mail.com", "07112265667");
    Caretaker care5 = new Caretaker("Arthur", "Singh", "kingarthur@mail.com", "07665565222");
    Caretaker care6 = new Caretaker("Samira", "Basa", "samirab@mail.com", "07112332114");
    Caretaker care7 = new Caretaker("Alan", "Basa", "alanb@mail.com", "07553552188");
    
    AnimalType cat = new AnimalType("cat", 12, new HashSet<>());
    AnimalType tarantula = new AnimalType("tarantula", 5, new HashSet<>(
                                            Arrays.asList(SpecialistCategory.EXOTIC, 
                                                          SpecialistCategory.VENOMOUS)));
    AnimalType lion  = new AnimalType("lion", 25, new HashSet(Arrays.asList(
                                                              SpecialistCategory.LARGE)));
    AnimalType seal = new AnimalType("seal", 20, new HashSet(Arrays.asList(
                                                             SpecialistCategory.LARGE,
                                                             SpecialistCategory.AQUATIC)));
    AnimalType duck = new AnimalType("duck", 5, new HashSet<>());
    AnimalType sheep = new AnimalType("sheep", 10, new HashSet<>(Arrays.asList(
                                                                 SpecialistCategory.LARGE)));
    AnimalType goldfish = new AnimalType("goldfish", 1, new HashSet<>(Arrays.asList(
                                                                       SpecialistCategory.AQUATIC)));
    AnimalType python = new AnimalType("python", 5, new HashSet<>(Arrays.asList(
                                                                 SpecialistCategory.EXOTIC)));
    AnimalType elephant = new AnimalType("elephant", 30, new HashSet<>(Arrays.asList(
    SpecialistCategory.EXOTIC, SpecialistCategory.LARGE)));
    
    AnimalType chimpanzee = new AnimalType("chimpanzee", 20, new HashSet<>(Arrays.asList(
                                                                           SpecialistCategory.EXOTIC)));
    AnimalType horse = new AnimalType("horse", 15, new HashSet<>(Arrays.asList(SpecialistCategory.LARGE)));
    
    Pet pet1 = new Pet("Zuzu", care2, dom1, LocalDate.of(2023,1,1), cat, Gender.MALE);
    Pet pet2 = new Pet("Jerry", care6, dom2, LocalDate.of(2023,1,1), tarantula, Gender.NA);
    Pet pet3 = new Pet("Goldie", care7, dom3, LocalDate.of(2024,1,1), goldfish, Gender.NA);
    FarmAnimal fan1 = new FarmAnimal("112233", care4, farm1, LocalDate.of(2022,1,1), duck, Gender.FEMALE);
    FarmAnimal fan2 = new FarmAnimal("Dolly", care5, farm2, LocalDate.of(2020,5,8), horse, Gender.FEMALE);
    FarmAnimal fan3 = new FarmAnimal("34567", care5, farm2, LocalDate.of(2023,1,1), sheep, Gender.MALE);
    ZooAnimal zan1 = new ZooAnimal("Bubbles", care1, manczoo, LocalDate.of(2015,10,22), elephant, Gender.MALE);
    ZooAnimal zan2 = new ZooAnimal("Simba", care3, manczoo, LocalDate.of(2021,4,21), lion, Gender.MALE);
    ZooAnimal zan3 = new ZooAnimal("Monty", care1, manczoo, LocalDate.of(2023,1,1), python, Gender.NA);
    ZooAnimal zan4 = new ZooAnimal("Coco", care3, manczoo, LocalDate.of(2024,2,15), chimpanzee, Gender.MALE);
    ZooAnimal zan5 = new ZooAnimal("Sammy", care3, manczoo, LocalDate.of(2019,11,2), seal, Gender.FEMALE);
    
    Record rec1 = new Record(LocalDate.of(2024,3,15), pet1, "", new ArrayList<>());
    Record rec2 = new Record(LocalDate.of(2024,3,15), pet2, "", new ArrayList<>());
    Record rec3 = new Record(LocalDate.of(2024,3,18), pet3, "", new ArrayList<>());
    Record rec4 = new Record(LocalDate.of(2024,3,18), fan1, "", new ArrayList<>());
    Record rec5 = new Record(LocalDate.of(2024,3,19), fan2, "", new ArrayList<>());
    Record rec6 = new Record(LocalDate.of(2024,3,19), fan3, "", new ArrayList<>());
    Record rec7 = new Record(LocalDate.of(2024,3,20), zan1, "", new ArrayList<>());
    Record rec8 = new Record(LocalDate.of(2024,3,20), zan2, "", new ArrayList<>());
    Record rec9 = new Record(LocalDate.of(2024,3,21), zan3, "", new ArrayList<>());
    Record rec10 = new Record(LocalDate.of(2024,3,21), zan4, "", new ArrayList<>());
    Record rec11 = new Record(LocalDate.of(2024,3,21), zan5, "", new ArrayList<>());

    
    List<Animal> animals = new ArrayList<>();
    animals.add(pet1);
    animals.add(pet2);
    animals.add(pet3);
    animals.add(fan1);
    animals.add(fan2);
    animals.add(fan3);
    animals.add(zan1);
    animals.add(zan2);
    animals.add(zan3);
    animals.add(zan4);
    animals.add(zan5);
    
    List<AnimalType> animalTypes = new ArrayList<>();
    animalTypes.add(duck);
    animalTypes.add(lion);
    animalTypes.add(python);
    animalTypes.add(cat);
    animalTypes.add(seal);
    animalTypes.add(sheep);    
    animalTypes.add(goldfish);
    animalTypes.add(elephant);
    animalTypes.add(chimpanzee);
    animalTypes.add(horse);
    animalTypes.add(tarantula);
    
    List<Record> records = new ArrayList<>();
    records.add(rec1);
    records.add(rec2);
    records.add(rec3);
    records.add(rec4);
    records.add(rec5);
    records.add(rec6);
    records.add(rec7);
    records.add(rec8);
    records.add(rec9);
    records.add(rec10);
    records.add(rec11);

    List<Administrator> administrators = new ArrayList<>();
    administrators.add(admin1);
    
    List<Vet> vets = new ArrayList<>();
    vets.add(vet1);
    vets.add(vet2);
    vets.add(vet3);
    vets.add(vet4);
    vets.add(vet5);
    vets.add(vet6);
    
    List<Caretaker> caretakers = new ArrayList<>();
    caretakers.add(care1);
    caretakers.add(care2);
    caretakers.add(care3);
    caretakers.add(care4);
    caretakers.add(care5);
    caretakers.add(care6);
    caretakers.add(care7);
    
    List<Address> addresses = new ArrayList<>();
    addresses.add(farm1);
    addresses.add(farm2);
    addresses.add(manczoo);
    addresses.add(dom1);
    addresses.add(dom2);
    addresses.add(dom3);
    addresses.add(dom4);
    addresses.add(dom5);
    
    List<Appointment> appointments = new ArrayList<>();
    
    IModel dao = new SerialisationModel();
    
    try {
        dao.saveAnimals(animals);
    } catch (IOException ex) {
        System.out.println(ex);
    }
    
    try {
        dao.saveAnimalTypes(animalTypes);
    } catch (IOException ex) {
        System.out.println(ex);
    }
       
    try {
        dao.saveAdministrators(administrators);
    } catch (IOException ex) {
        System.out.println(ex);
    }
    
    try {
        dao.saveVets(vets);
    } catch (IOException ex) {
        System.out.println(ex);
    }
          
    try {
        dao.saveCaretakers(caretakers);
    } catch (IOException ex) {
        System.out.println(ex);
    }
    
    try {
        dao.saveAddresses(addresses);
    } catch (IOException ex) {
        System.out.println(ex);
    }
     
    try {
        dao.saveRecords(records);
    } catch (IOException ex) {
        System.out.println(ex);
    }
     
    try {
        dao.saveAppointments(appointments);
    } catch (IOException ex) {
        System.out.println(ex);
    }
      
    
    }
}
