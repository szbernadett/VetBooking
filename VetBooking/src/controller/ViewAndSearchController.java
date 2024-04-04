/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import model.DAO;
import model.Record;
import model.RecordHandler;
import view.ViewAndSearchAnimalsWindow;

/**
 *
 * @author igbin
 */
public class ViewAndSearchController extends Controller<ViewAndSearchAnimalsWindow> {

    private RecordHandler recordHandler;
    private List<Record> currentList;
    private Record currentRecord;
    private int currentRecordCount;
    private int currentRecordNum;

    public ViewAndSearchController(ViewAndSearchAnimalsWindow view, DAO model, RecordHandler recordHandler) {
        super(view, model);
        this.recordHandler = recordHandler;
        currentList = recordHandler.getAllRecords();
        if (!currentList.isEmpty()) {
            currentRecord = currentList.get(0);
        }
        setRecordNavNumbers();

        setDataChangeHandlers();
        displayRecord(currentRecord);
    }

    @Override
    protected final void setDataChangeHandlers() {
        setEventHandler(view.getNextButton(), ActionEvent.ACTION, this::displayNext);
        setEventHandler(view.getPreviousButton(), ActionEvent.ACTION, this::displayPrevious);
        setEventHandler(view.getSearchButton(), ActionEvent.ACTION, this::displaySearchResults);
        setEventHandler(view.getViewAllButton(), ActionEvent.ACTION, this::displayAll);
        setEventHandler(view.getExitButton(), ActionEvent.ACTION, this::exitWindow);
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
        setRecordNavLabelText();
    }

    private void displayNext(ActionEvent event) {
        Record nextRecord = recordHandler.getNextRecord(currentRecord, currentList);
        displayRecord(nextRecord);
        currentRecord = nextRecord;
        currentRecordNum = currentList.indexOf(currentRecord) + 1;
    }

    private void displayPrevious(ActionEvent event) {
        Record previousRecord = recordHandler.getPreviousRecord(currentRecord, currentList);
        displayRecord(previousRecord);
        currentRecord = previousRecord;
        currentRecordNum = currentList.indexOf(currentRecord) + 1;
    }

    private List<Record> fetchSearchResults(String keyword) {
        List<Record> searchResults;
        Map<String, List<Record>> searchResultsMap = recordHandler.getSearchResultsMap();
        if (!searchResultsMap.containsKey(keyword)) {
            keyword = prepareString(keyword);
            recordHandler.search(keyword);
        }

        searchResults = searchResultsMap.get(keyword);

        return searchResults;
    }

    private void displaySearchResults(ActionEvent event) {
        String keyword = view.getSearchTextField().getText();
        List<Record> searchResults = fetchSearchResults(keyword);
        System.out.println(searchResults);
        currentRecordCount = searchResults.size();

        if (!searchResults.isEmpty()) {
            switchDisplay(searchResults);
        } else {
            clearView();
            currentRecordCount = 0;
            currentRecordNum = 0;
            setRecordNavLabelText();
        }

    }

    private void displayAll(ActionEvent event) {
        switchDisplay(recordHandler.getAllRecords());
        view.getSearchTextField().setText("");
    }

    private void switchDisplay(List<Record> recordsToDisplay) {
        currentList = recordsToDisplay; // set currentList for Previous / Next navigation
        currentRecord = currentList.get(0); // set currently displayed record to first element of current list
        displayRecord(currentRecord); // display current record
        setRecordNavNumbers();
        setRecordNavLabelText();
    }

    private void setRecordNavNumbers() {
        if (!currentList.isEmpty()) {
            currentRecordNum = currentList.indexOf(currentRecord) + 1;
            currentRecordCount = currentList.size();
        } else {
            currentRecordNum = 0;
            currentRecordCount = 0;
        }
    }

    private void setRecordNavLabelText() {
        String text;
        text = "Displaying " + currentRecordNum
                + " of " + currentRecordCount
                + " records";

        view.getRecordNavigationLabel().setText(text);
    }

    private void clearView() {
        view.getNameValueLabel().setText("");
        view.getAnimalTypeValueLabel().setText("");
        view.getCaretakerValueLabel().setText("");
        view.getLocationTypeValueLabel().setText("");
        view.getAddressValueLabel().setText("");
        view.getDateOfBirthValueLabel().setText("");
        view.getDateRegisteredValueLabel().setText("");
        view.getMedicalHistoryValueLabel().setText("");

    }

}
