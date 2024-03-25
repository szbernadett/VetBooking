/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import model.Record;
import model.RecordHandler;
import model.SerialisationDAO;
import view.ViewAndSearchAnimalsWindow;

/**
 *
 * @author igbin
 */
public class ViewAndSearchController extends Controller<ViewAndSearchAnimalsWindow> {

    private RecordHandler recordHandler;
    private List<Record> currentList;
    private Record currentRecord;

    public ViewAndSearchController(ViewAndSearchAnimalsWindow view, SerialisationDAO dao, RecordHandler recordHandler) {
        super(view, dao);
        this.recordHandler = recordHandler;
        currentList = recordHandler.getAllRecords();
        if (!currentList.isEmpty()) {
            currentRecord = currentList.get(0);
        }
        setupEventHandlers();
    }

    @Override
    protected final void setupEventHandlers() {
        view.setEventHandler(view.getNextButton(), ActionEvent.ACTION, this::displayNext);
        view.setEventHandler(view.getPreviousButton(), ActionEvent.ACTION, this::displayPrevious);
        view.setEventHandler(view.getSearchButton(), ActionEvent.ACTION, this::displaySearchResults);
        view.setEventHandler(view.getViewAllButton(), ActionEvent.ACTION, this::displayAll);
        view.setEventHandler(view.getExitButton(), ActionEvent.ACTION, this::exitWindow);
    }

    public RecordHandler getRecordHandler() {
        return recordHandler;
    }

    public void setRecordHandler(RecordHandler recordHandler) {
        this.recordHandler = recordHandler;
    }

    public List<Record> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<Record> currentList) {
        this.currentList = currentList;
    }

    private void displayRecord(Record record) {

        view.getNameValueLabel().setText(record.getAnimal().getIdentifier());
        view.getAnimalTypeValueLabel().setText(record.getAnimal().getAnimalType().getTypeName());
        view.getAddressValueLabel().setText(record.getAnimal().getAddress().toString());
        view.getCaretakerValueLabel().setText(record.getAnimal().getCaretaker().toString());
        view.getDateOfBirthValueLabel().setText(record.getAnimal().getDateOfBirth().toString());
        view.getDateRegisteredValueLabel().setText(record.getDateRegistered().toString());
        view.getLocationTypeValueLabel().setText(record.getAnimal().getAddress().getLocationType().toString());
        view.getMedicalHistoryValueLabel().setText(record.getMedicalHistory());
    }

    private void displayNext(ActionEvent event) {
        Record nextRecord = recordHandler.getNextRecord(currentRecord, currentList);
        displayRecord(nextRecord);
        currentRecord = nextRecord;
    }

    private void displayPrevious(ActionEvent event) {
        Record previousRecord = recordHandler.getPreviousRecord(currentRecord, currentList);
        displayRecord(previousRecord);
        currentRecord = previousRecord;
    }

    private List<Record> fetchSearchResults(String keyword) {
        List<Record> searchResults = null;
        Map<String, List<Record>> searchResultsMap = recordHandler.getSearchResultsMap();
        if (searchResultsMap.containsKey(keyword)) {
            searchResults = searchResultsMap.get(keyword);
        } else {
            //
            // call method to prepare search keyword (remove characters, validate etc.)
            //
            recordHandler.search(keyword);
        }

        return searchResults;
    }

    private void displaySearchResults(ActionEvent event) {
        String keyword = view.getSearchTextField().getText();
        List<Record> searchResults = fetchSearchResults(keyword);
        if (searchResults == null) {
            searchResults = new ArrayList<>();
        }

        if (!searchResults.isEmpty()) {
            switchDisplay(searchResults);
        }
    }

    private void displayAll(ActionEvent event) {
        switchDisplay(recordHandler.getAllRecords());
    }

    public void switchDisplay(List<Record> recordsToDisplay) {
        currentList = recordsToDisplay; // set currentList for Previous / Next navigation
        currentRecord = currentList.get(0); // set currently displayed record to first element of current list
        displayRecord(currentRecord); // display current record
    }
}
