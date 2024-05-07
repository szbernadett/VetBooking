
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class that provides methods for searching and navigating through records in the veterinary
 * administration system.
 * Holds an instance reference to check if the class has been instantiated before. 
 * - searchMap: a map that holds the search keys and the records that match them
 * - searchResultsMap: a map that holds previous search results 
 * @see Record
 */
public class RecordHandler {

    private static RecordHandler instance;
    private List<Record> allRecords;
    private Map<String, List<Record>> searchMap;
    private Map<String, List<Record>> searchResultsMap;

    /**
     * Constructor for the RecordHandler class. Initialises the searchMap and searchResultsMap to
     * empty hashmaps then calls the populateSearchMap method. 
     * @param allRecords a list of all records in the system
     * @see populateSearchMap
     * 
     */
    private RecordHandler(List<Record> allRecords) {
        this.allRecords = allRecords;
        searchMap = new HashMap<>();
        searchResultsMap = new HashMap<>();
        populateSearchMap();
    }

    /**
     * If there is no existing instance of the RecordHandler class, call the constructor to create
     * a new instance.
     * @param allRecords
     * @return an instance of the RecordHandler class
     */
    public static RecordHandler getInstance(List<Record> allRecords) {
        if (instance == null) {
            instance = new RecordHandler(allRecords);
        }

        return instance;
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

    /**
     * Get the next record in the list of records. Determine the maximum index of the list based on 
     * the size of the list. Determine the index of the current record. If the index of the current
     * record is smaller than the maximum index, the next record will be at the index of the current
     * record plus 1. If the index of the current record is equal to the maximum index, the next
     * record will be at the index of 0. If the index of the current record is not found, the next
     * record will be at the index of 0.
     * @param currentRecord the current record
     * @param currentList the list of records in which the current and next record are located
     * @return the next record in the list
     */
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

    /**
     * Get the previous record in the list of records. Determine the maximum index of the list based
     * on the size of the list. Determine the index of the current record. If the index of the current
     * record is greater than 0, the previous record will be at the index of the current record minus
     * 1. If the index of the current record is equal to 0, the previous record will be at the index
     * of the maximum index. If the index of the current record is not found, the previous record will
     * be at the index of 0.
     * @param currentRecord the current record
     * @param currentList the list of records in which the current and previous record are located
     * @return the previous record in the list
     */
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

    /**
     * Search for a keyword in the searchMapResults map to determine whether a search with the given
     * keyword has been performed before. If the keyword is found, retrieve the previous search result
     * from the searchResultsMap.If the keyword is not found in the searchResultsMap, 
     * create a sorted list of the keys of the searchMap. Perform a binary search on the sorted list
     * to find the keyword. If the keyword is found, fetch the matching records from the searchMap
     * using the matched keyword. Add the keyword and the list of matching records to the searchREsultsMap.
     * If the keyword is not found, add the keyword and an empty list to the searchResultsMap.
     * 
     * @param keyword the keyword to search for
     * @return void
     */
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

    /**
     * Extract values from a Record to be used as search keys. The search keys are the animal's
     * identifier, the animal's type, the caretaker's first name, the caretaker's last name, and the
     * location type of the animal's address. The search keys are converted to lowercase before being
     * added to the list of record keys.
     * @param record the record from which to extract the search keys
     * @return a list of search keys
     */
    private List<String> extractRecordKeys(Record record) {
        List<String> recordKeys = new ArrayList<>();
        recordKeys.add(record.getAnimal().getIdentifier().toLowerCase());
        recordKeys.add(record.getAnimal().getAnimalType().getTypeName().toLowerCase());
        recordKeys.add(record.getAnimal().getCaretaker().getFirstName().toLowerCase());
        recordKeys.add(record.getAnimal().getCaretaker().getLastName().toLowerCase());
        recordKeys.add(record.getAnimal().getAddress().getLocationType().toString().toLowerCase());

        return recordKeys;
    }

    /**
     * Populate the searchMap with the search keys extracted from the records. For each record in the
     * list of all records, extract the search keys from the record and add the record to the searchMap
     * using the search keys as the key. 
     * @see addtoSearchMap
     * @return void
     * 
     */
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

    /**
     * Get a sorted list of the keys in the searchMap. Get a set of the keys from the map and
     * create an arraylist populated with the items of the set, then sort the arraylist.
     * @return a sorted list of the keys in the searchMap
     */
    private ArrayList<String> getSortedKeys() {
        Set<String> keySet = searchMap.keySet();
        ArrayList<String> sortedList = new ArrayList<>(keySet);
        Collections.sort(sortedList);
        return sortedList;

    }

    /**
     * Perform a binary search on a sorted list of keys to find a keyword. If the keyword is found,
     * return the index of the keyword in the sorted list. If the keyword is not found, return -1. 

     * @param target the keyword to search for
     * @param sortedList the list of keys in the searchMap
     * @param leftIndex the left index of the search segment
     * @param rightIndex the right index of the search segment
     * @return the index of the keyword in the sorted list
     */
    private int binarySearch(String target, List<String> sortedList, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            return -1;
        }
        int midIndex = (leftIndex + rightIndex) / 2;
        String midElement = sortedList.get(midIndex);

        if (midElement.equalsIgnoreCase(target)) {
            return midIndex;
        } else if (target.compareToIgnoreCase(midElement) < 0) {
            return binarySearch(target, sortedList, leftIndex, midIndex - 1);
        } else {
            return binarySearch(target, sortedList, midIndex + 1, rightIndex);
        }

    }

}
