package com.deepsky.services.dataImport;

import au.com.bytecode.opencsv.CSVReader;
import com.deepsky.lang.plsql.struct.TableDescriptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public class ImportDTO {

    private CSVReader reader;
    private String[] columnNames;
    private int skipRowsFromTop;
    private String selectedTable;
    private Map<String, TableDescriptor> tables;

    public ImportDTO() {
    }

    public ImportDTO(File csvFile, String[] columnNames, int skipRowsFromTop) throws FileNotFoundException {
        Reader r = new FileReader(csvFile);
        this.reader = new CSVReader(r);
        this.columnNames = columnNames;
        this.skipRowsFromTop = skipRowsFromTop;
    }

    public void setTableList(Map<String, TableDescriptor> tables){
        this.tables = tables;
    }

    public void setSelectedTable(String selectedTable){
        this.selectedTable = selectedTable;
    }

    public String[] getColumnNames() {
        return columnNames;
    }


    public String getSelectedTable() {
        return selectedTable;
    }

    public Map<String, TableDescriptor> getTableList() {
        return tables;
    }

    public CSVReader getReader() {
        return reader;
    }

    public void setReader(CSVReader reader) {
        this.reader = reader;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public int getSkipRowsFromTop() {
        return skipRowsFromTop;
    }

    public void setSkipRowsFromTop(int skipRowsFromTop) {
        this.skipRowsFromTop = skipRowsFromTop;
    }

}
