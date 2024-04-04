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

/**
 *
 * @author igbin
 */
public class RecordHandler implements ListAndSearch {

    private static RecordHandler instance;
    private List<Record> allRecords;
    private Map<String, List<Record>> searchMap;
    private Map<String, List<Record>> searchResultsMap;

    public RecordHandler(List<Record> allRecords) {
        this.allRecords = allRecords;
        searchMap = new HashMap<>();
        searchResultsMap = new HashMap<>();
        populateSearchMap();
    }

    public static RecordHandler getInstance(List<Record> allRecords) {
        if (instance == null) {
            instance = new RecordHandler(allRecords);
        }

        return instance;
    }

    @Override
    public Record getNextRecord(Record currentRecord, List<Record> currentList) {
        Record nextRecord = null;

        if (currentRecord != null) {
            int maxIndex = currentList.size() - 1;
            int currentIndex = currentList.indexOf(currentRecord);

            if (currentIndex != -1) {
                if (currentIndex < maxIndex) {
                    nextRecord = currentList.get(currentIndex + 1);
                } else if (currentIndex == maxIndex) {
                    nextRecord = currentList.get(0);
                }
            } else {
                nextRecord = currentList.get(0);
            }
        }

        return nextRecord;
    }

    @Override
    public Record getPreviousRecord(Record currentRecord, List<Record> currentList) {
        Record previousRecord = null;
        if (currentRecord != null) {
            int maxIndex = currentList.size() - 1;
            int currentIndex = currentList.indexOf(currentRecord);

            if (currentIndex != -1) {
                if (currentIndex > 0) {
                    previousRecord = currentList.get(currentIndex - 1);
                } else if (currentIndex == 0) {
                    previousRecord = currentList.get(maxIndex);
                }
            } else {
                previousRecord = currentList.get(0);
            }

        }

        return previousRecord;
    }

    @Override
    public void search(String keyword) {
        if (!searchResultsMap.containsKey(keyword)) {

            List<String> sortedList = getSortedKeys();
            List<Record> matchedRecords;

            int result = binarySearch(keyword,
                    sortedList,
                    0,
                    sortedList.size() - 1);

            if (result != -1) {
                matchedRecords = searchMap.get(keyword);
                searchResultsMap.put(keyword, matchedRecords);
            } else {
                searchResultsMap.put(keyword, new ArrayList<>());
            }
        }
    }

    public List<Record> getAllRecords() {
        return allRecords;
    }

    public void setAllRecords(List<Record> allRecords) {
        this.allRecords = allRecords;
    }

    public Map<String, List<Record>> getSearchMap() {
        return searchMap;
    }

    public void setSearchMap(Map<String, List<Record>> searchMap) {
        this.searchMap = searchMap;
    }

    private void addToSearchMap(String key, Record record) {
        if (!searchMap.containsKey(key)) {
            searchMap.put(key, new ArrayList<>());
        }
        searchMap.get(key).add(record);
    }

    public Map<String, List<Record>> getSearchResultsMap() {
        return searchResultsMap;
    }

    public void setSearchResultsMap(Map<String, List<Record>> searchResultsMap) {
        this.searchResultsMap = searchResultsMap;
    }

    private List<String> extractRecordKeys(Record record) {
        List<String> recordKeys = new ArrayList<>();
        recordKeys.add(record.getAnimal().getIdentifier().toLowerCase());
        recordKeys.add(record.getAnimal().getAnimalType().getTypeName().toLowerCase());
        recordKeys.add(record.getAnimal().getCaretaker().getFirstName().toLowerCase());
        recordKeys.add(record.getAnimal().getCaretaker().getLastName().toLowerCase());
        recordKeys.add(record.getAnimal().getAddress().getLocationType().toString().toLowerCase());

        return recordKeys;
    }

    private void populateSearchMap() {
        if (searchMap != null) {
            for (Record record : allRecords) {
                List<String> recordKeys = extractRecordKeys(record);
                for (String recordKey : recordKeys) {
                    addToSearchMap(recordKey, record);
                }
            }
        }
    }

    private ArrayList<String> getSortedKeys() {
        Set<String> keySet = searchMap.keySet();
        ArrayList<String> sortedList = new ArrayList<>(keySet);
        Collections.sort(sortedList);
        System.out.println("sorted list: ");
        for (String string : sortedList) {
            System.out.println(string);
        }
        return sortedList;

    }

    private int binarySearch(String target, List<String> sortedList, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            return -1;
        }
        int midIndex = (leftIndex + rightIndex) / 2;
        String midElement = sortedList.get(midIndex);
        System.out.println(target + ", " + midElement);

        if (midElement.equalsIgnoreCase(target)) {
            return midIndex;
        } else if (target.compareToIgnoreCase(midElement) < 0) {
            return binarySearch(target, sortedList, leftIndex, midIndex - 1);
        } else {
            return binarySearch(target, sortedList, midIndex + 1, rightIndex);
        }

    }

}
