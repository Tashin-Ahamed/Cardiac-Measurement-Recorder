package com.example.cardiacmeasurementmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * unit test class
 */
public class RecordList {
    private List<ModelClass> records = new ArrayList<>();

    /**
     * This method adds a record in the list
     * @param record
     *      the object which is going to be added to the list
     */
    public void addRecord (ModelClass record) {
        if(records.contains(record)) {
            throw new IllegalArgumentException();
        } else {
            records.add(record);
        }
    }
    /**
     * This method is for returning a sorted list
     * @return
     *      returns a sorted list of records
     */
    public List<ModelClass> sortRecords() {
        List<ModelClass> recordList = records;
        Collections.sort(recordList);
        return recordList;
    }
    /**
     * This method deletes an existing method to the list
     * @param record
     *      the object which is going to be deleted from the list
     */
    public void deleteRecord (ModelClass record) {
        if(records.contains(record)) {
            records.remove(record);
        } else {
            throw new IllegalArgumentException();
        }
    }

}