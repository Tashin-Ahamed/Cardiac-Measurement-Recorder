package com.example.cardiacmeasurementmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public List<ModelClass> sortRecords() {
        List<ModelClass> recordList = records;
        Collections.sort(recordList);
        return recordList;
    }

}