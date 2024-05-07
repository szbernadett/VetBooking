
package controller;

import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.Event;
import model.DAO;
import model.Record;
import model.RecordHandler;
import view.ViewAndSearchAnimalsWindow;

/**
 * ViewAndSearchController class: Represents a controller for the ViewAndSearchAnimalsWindow 
 * in the veterinary administration system.
 * 
 *  - view: ViewAndSearchAnimalsWindow the view to be controlled
 *  - model: DAO the DAO implementation that communicates with the data source
 *  - recordHandler: RecordHandler the RecordHanler object providing methods for displaying and filtering Record objects
 *  - currentList: List<Record> the list of records to be displayed in the view, conatins all 
 *                      records by default and is updated when a search is performed
 *  - currentRecord: Record the Record object the details of which are currently displayed in the view
 *  - currentRecordCount: int the total number of records in the current list. Used to display
 *                             how many records are currently in the list being displayed
 *                             and looped through via user interaction
 *  - currentRecordNum: int 
 *  @see RecordHandler
 *  @see Record
 *  @see ViewAndSearchAnimalsWindow
 *  @see DAO
 *  @see Controller
 * 
 */
public class ViewAndSearchController extends Controller<ViewAndSearchAnimalsWindow> {

    private RecordHandler recordHandler;
    private List<Record> currentList;
    private Record currentRecord;
    private int currentRecordCount;
    private int currentRecordNum;

    /**
     * Constructor for the ViewAndSearchController class. Initialises the list holding the records
     * to be displayed in the view, sets the current record to the first record in the list and
     * sets the event handlers for the view elements that accept user interaction.
     * @param view the ViewAndSearchAnimalsWindow instance to be controlled
     * @param model the DAO implementation that communicates with the data source
     * @param recordHandler the RecordHandler object providing methods for displaying and filtering 
     *                      Record objects
     */
    public ViewAndSearchController(ViewAndSearchAnimalsWindow view, DAO model, RecordHandler recordHandler) {
        super(view, model);
        this.recordHandler = recordHandler;
        currentList = recordHandler.getAllRecords();
        if (!currentList.isEmpty()) {
            currentRecord = currentList.get(0);
        }
        setRecordNavNumbers();
        dataToView();
        setDataChangeHandlers();

    }

    @Override
    protected final void setDataChangeHandlers() {
        view.getNextButton().addEventHandler(ActionEvent.ACTION, this::displayNext);
        view.getPreviousButton().addEventHandler(ActionEvent.ACTION, this::displayPrevious);
        view.getSearchButton().addEventHandler(ActionEvent.ACTION, this::displaySearchResults);
        view.getViewAllButton().addEventHandler(ActionEvent.ACTION, this::displayAll);
        view.getExitButton().addEventHandler(ActionEvent.ACTION, this::exitWindow);
    }

    @Override
    protected final void dataToView() {
        displayRecord(currentRecord);
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

    /**
     * Sets view components to display the details of the Record object passed as an argument.
     * 
     * @param record The Record object to be dispalyed
     * @return void
     */
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

    /**
     * Get the next record in the list from the recordHandler object, set it as the value of the 
     * currentRecord field and display it in the view. Set the currentRecordNum field to reflect
     * the position of the record displayed. 
     * @param event The event created by the user clicking the Next button
     * @return void
     */
    private void displayNext(ActionEvent event) {
        Record nextRecord = recordHandler.getNextRecord(currentRecord, currentList);
        displayRecord(nextRecord);
        currentRecord = nextRecord;
        currentRecordNum = currentList.indexOf(currentRecord) + 1;
    }

    /**
     * Get the previous record in the list from the recordHandler object, set it as the value of the
     * currentRecord field and display it in the view. Set the currentRecordNum field to reflect
     * the position of the record displayed.
     * @param event The event created by the user clicking the Previous button
     * @return void
     * 
     */
    private void displayPrevious(ActionEvent event) {
        Record previousRecord = recordHandler.getPreviousRecord(currentRecord, currentList);
        displayRecord(previousRecord);
        currentRecord = previousRecord;
        currentRecordNum = currentList.indexOf(currentRecord) + 1;
    }
   
    /**
     * Check if the search keyword has been searched for before. If it has, retrieve the search results
     * from the recordHandler object's search results map. If not, perform search via the recordHandler
     * which will uptade the search results map. Retrieve the list of matching records from the search 
     * results map and return it.
     * @param keyword The search keyword retrieved from the view
     * @return The list of records that match contain the search word
     */
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

    /**
     * Get the search keyword from the view, fetch the search results from the recordHandler object
     * and update the view to display the search results. If no results are found, clear the view,
     * set the labels that display the current and total number of records to 0.
     * 
     * @param event The event created by the user clicking the Search button
     * @return void
     */
    private void displaySearchResults(ActionEvent event) {
        String keyword = view.getSearchTextField().getText();  // prepare string?
        List<Record> searchResults = fetchSearchResults(keyword); 
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

    /**
     * Display all available (unfiltered) records in the view and clear the search text field.
     * @param event The event created by the user clicking the View All button
     * @return void
     */
    private void displayAll(ActionEvent event) {
        switchDisplay(recordHandler.getAllRecords());
        view.getSearchTextField().setText("");
    }

    /**
     * Update the view to display the list of records passed as an argument. Set the currentRecord
     * field to the first record in the list. Update the
     * labels that display the current and total number of records.
     * 
     * @param recordsToDisplay The list of records to be displayed in the view
     * @return void
     */
    private void switchDisplay(List<Record> recordsToDisplay) {
        currentList = recordsToDisplay; // set currentList for Previous / Next navigation
        currentRecord = currentList.get(0); // set currently displayed record to first element of current list
        displayRecord(currentRecord); // display current record
        setRecordNavNumbers();
        setRecordNavLabelText();
    }

    /**
     *  Update the currentRecordNum and currentRecordCount fields to reflect the total number 
     *  of records in the list and the position of the current record.
     *  @return void
     */
    private void setRecordNavNumbers() {
        if (!currentList.isEmpty()) {
            currentRecordNum = currentList.indexOf(currentRecord) + 1;
            currentRecordCount = currentList.size();
        } else {
            currentRecordNum = 0;
            currentRecordCount = 0;
        }
    }

    /**
     * Set the text of the label that displays the current and total number of records in the list.
     * @return void
     */
    private void setRecordNavLabelText() {
        String text;
        text = "Displaying " + currentRecordNum
                + " of " + currentRecordCount
                + " records";

        view.getRecordNavigationLabel().setText(text);
    }

    /**
     * Clear the view of all data, setting label texts to empty strings.
     * @return void
     */
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

    /**
     * Close the window. 
     * @param event The event created by the user clicking the Exit button
     * @return void
     */
    private void exitWindow(Event event) {
        view.close();
    }

}
