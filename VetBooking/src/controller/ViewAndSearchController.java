/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import model.Record;
import model.IModel;
import model.RecordHandler;
import model.SerialisationModel;
import view.ViewAndSearchAnimalsWindow;

/**
 *
 * @author igbin
 */
public class ViewAndSearchController {

    private ViewAndSearchAnimalsWindow view;
    private SerialisationModel dao;
    private RecordHandler recordHandler;
    private List<Record> currentList;
    private Record currentRecord;

    public ViewAndSearchController(ViewAndSearchAnimalsWindow view, SerialisationModel dao, RecordHandler recordHandler) {
        this.view = view;
        this.dao = dao;
        this.recordHandler = recordHandler;
        currentList = recordHandler.getAllRecords();
        if (!currentList.isEmpty()) {
            currentRecord = currentList.get(0);
        }
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        view.setEventHandler(view.getNextButton(), ActionEvent.ACTION, this::displayNext);
        view.setEventHandler(view.getPreviousButton(), ActionEvent.ACTION, this::displayPrevious);
        view.setEventHandler(view.getSearchButton(), ActionEvent.ACTION, this:displaySearchResults
    

    );
    }

    public ViewAndSearchAnimalsWindow getView() {
        return view;
    }

    public void setView(ViewAndSearchAnimalsWindow view) {
        this.view = view;
    }

    public IModel getDao() {
        return dao;
    }

    public void setDao(SerialisationModel dao) {
        this.dao = dao;
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

    public void displayRecord(Record record) {

        view.getNameValueLabel().setText(record.getAnimal().getIdentifier());
        view.getAnimalTypeValueLabel().setText(record.getAnimal().getAnimalType().getTypeName());
        view.getAddressValueLabel().setText(record.getAnimal().getAddress().toString());
        view.getCaretakerValueLabel().setText(record.getAnimal().getCaretaker().toString());
        view.getDateOfBirthValueLabel().setText(record.getAnimal().getDateOfBirth().toString());
        view.getDateRegisteredValueLabel().setText(record.getDateRegistered().toString());
        view.getLocationTypeValueLabel().setText(record.getAnimal().getAddress().getLocationType().toString());
        view.getMedicalHistoryValueLabel().setText(record.getMedicalHistory());
    }

    public void displayNext(ActionEvent event) {
        Record nextRecord = recordHandler.getNextRecord(currentRecord, currentList);
        displayRecord(nextRecord);
        currentRecord = nextRecord;
    }

    public void displayPrevious(ActionEvent event) {
        Record previousRecord = recordHandler.getPreviousRecord(currentRecord, currentList);
        displayRecord(previousRecord);
        currentRecord = previousRecord;
    }

    public void displaySearchResults(ActionEvent event) {
        List<Record> searchResults = null;
        Map searchResultsMap = recordHandler.getSearchResultsMap();
        if(searchResultsMap.containsKey("needs to be set to keyword from view!!!!")){
            searchResults = searchResultsMap.get("keyword missing here too");
        } else {
            recordHandler.search("keyword missing here too");
        }
        
        if(searchResults == null){
            searchResults = new ArrayList<>();
        }

        if (!searchResults.isEmpty()) {
            switchDisplay(searchResults);
        }
    }

    public void displayAll() {
        switchDisplay(recordHandler.getAllRecords());
    }

    public void switchDisplay(List<Record> recordsToDisplay) {
        currentList = recordsToDisplay; // set currentList for Previous / Next navigation
        currentRecord = currentList.get(0);
        displayRecord(currentList.get(0)); // display first element of current list
    }
}
