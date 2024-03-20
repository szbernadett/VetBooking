/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author igbin
 */
public class RecordHandler implements ListAndSearch {

    private List<Record> records;
    private Map<String, ArrayList<Record>> searchMap;
    private Map<String, ArrayList<Record>> searchResults;

    public RecordHandler(List<Record> records) {
        this.records = records;
        searchMap = new HashMap<>();
        searchResults = new HashMap<>();
        populateSearchMap();
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Map<String, ArrayList<Record>> getSearchMap() {
        return searchMap;
    }

    public void setSearchMap(Map<String, ArrayList<Record>> searchMap) {
        this.searchMap = searchMap;
    }

    private void addToSearchMap(String key, Record record){
        if(!searchMap.containsKey(key)){
            searchMap.put(key, new ArrayList<>());
        }
        searchMap.get(key).add(record);
    }

    public Map<String, ArrayList<Record>> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(Map<String, ArrayList<Record>> searchResults) {
        this.searchResults = searchResults;
    }
    
    
    private List<String> extractRecordKeys(Record record){
        List<String> recordKeys = new ArrayList<>();
        recordKeys.add(record.getAnimal().getIdentifier());
        recordKeys.add(record.getAnimal().getCareTaker().getFirstName());
        recordKeys.add(record.getAnimal().getCareTaker().getLastName());
        recordKeys.add(record.getAnimal().getAnimalType().getTypeName());
        
        return recordKeys;
    }

    private void populateSearchMap(){
     for(Record record : records){
         List<String> recordKeys = extractRecordKeys(record);
         for(String recordKey : recordKeys){
             addToSearchMap(recordKey, record);
         }
     }
        
    }
    
    private ArrayList<String> getSortedKeys(){
        Set<String> keySet = searchMap.keySet();
        ArrayList<String> sortedList = new ArrayList<>(keySet);
        Collections.sort(sortedList);
        
        return sortedList;
        
    }
    
    private ArrayList<Record> binarySearch(String target, ArrayList<String> sortedList, int startIndex, int endIndex ){
        ArrayList<Record> matchedRecords = new ArrayList<>();
        if (startIndex <= endIndex){
        int midIndex = (int)(startIndex + endIndex) / 2;
        String midElement = sortedList.get(midIndex);
        
        if(midElement.equals(target)){
                matchedRecords = searchMap.get(target);
        } else {
            if(target.compareTo(midElement) < 0){
                binarySearch(target, sortedList, startIndex, midIndex -1);
            } else if (target.compareTo(midElement) > 0){ // can just be else
                binarySearch(target, sortedList, midIndex + 1, endIndex);
            }
        }
      }
        
        return matchedRecords;
    }
    
    @Override
    public Record getNext(Record currentRecord) {
        Record nextRecord = null;

        if (currentRecord != null) {
            int maxIndex = records.size() - 1;
            int currentIndex = records.indexOf(currentRecord);

            if (currentIndex != -1) {
                if (currentIndex < maxIndex) {
                    nextRecord = records.get(currentIndex + 1);
                } else if (currentIndex == maxIndex) {
                    nextRecord = records.get(0);
                }
            } else {
                nextRecord = records.get(0);
            }
        } 

        return nextRecord;
    }

    @Override
    public Record getPrevious(Record currentRecord) {
        Record previousRecord = null;
        
        if(currentRecord != null){
        int maxIndex = records.size() - 1;
        int currentIndex = records.indexOf(currentRecord);
        
        if(currentIndex != -1){
            if (currentIndex > 0){
                previousRecord = records.get(currentIndex -1);
            } else if (currentIndex == 0){
                previousRecord = records.get(maxIndex);
            }
        } else {
            previousRecord = records.get(0);
        }
        
        }
        
        return previousRecord;
    }

    @Override
    public void search(String keyword) {
        List<Record> results = new ArrayList<>();
        ArrayList<String> sortedList = getSortedKeys();
        ArrayList<Record> matchedRecords = binarySearch(keyword, 
                                                        sortedList, 
                                                        0, 
                                                        sortedList.size()-1);
        searchResults.put(keyword, matchedRecords);
    }

}
