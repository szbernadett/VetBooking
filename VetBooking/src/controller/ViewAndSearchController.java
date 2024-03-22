/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
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

    public ViewAndSearchController(ViewAndSearchAnimalsWindow view, SerialisationModel dao, RecordHandler recordHandler) {
        this.view = view;
        this.dao = dao;
        this.recordHandler = recordHandler;
        currentList = recordHandler.getAllRecords();
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

    public void displayNext(Record currentRecord) {
        Record nextRecord = recordHandler.getNextRecord(currentRecord, currentList);
        displayRecord(nextRecord);
    }

    public void displayPrevious(Record currentRecord) {
        Record previousRecord = recordHandler.getPreviousRecord(currentRecord, currentList);
        displayRecord(previousRecord);
    }

    public void displaySearchResults(ArrayList<Record> searchResults) {
        if (!searchResults.isEmpty()) {
          switchDisplay(searchResults);
        }
    }

    public void displayAll(){
        switchDisplay(recordHandler.getAllRecords());
    }

    public void switchDisplay(List<Record> recordsToDisplay){
        currentList = recordsToDisplay; // set currentList for Previous / Next navigation
        displayRecord(currentList.get(0)); // display first element of current list
    }
}
