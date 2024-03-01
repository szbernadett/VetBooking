/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Address.LocationType;

/**
 *
 * @author igbin
 */
public class CreateObjects {
    
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
    
    
    
    
    
}
