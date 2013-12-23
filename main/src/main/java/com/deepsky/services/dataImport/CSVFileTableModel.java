package com.deepsky.services.dataImport;

import au.com.bytecode.opencsv.CSVReader;
import org.jetbrains.annotations.NotNull;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSVFileTableModel extends AbstractTableModel {

    int columnCntMax = 0;
    List<String[]> content = new ArrayList<String[]>();

    public CSVFileTableModel(@NotNull File file, int numberRows) {

        try {
            Reader r = new FileReader(file);
            CSVReader reader = new CSVReader(r);
            for (int i = 0; i < numberRows; i++) {
                String[] row = reader.readNext();
                if (row != null) {
                    if (columnCntMax < row.length) {
                        columnCntMax = row.length;
                    }
                    content.add(row);
                } else {
                    break;
                }
            }

            reader.close();
            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public int getRowCount() {
        return content.size();
    }

    @Override
    public int getColumnCount() {
        return columnCntMax;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (rowIndex < content.size() && columnIndex < content.get(rowIndex).length) {
            return content.get(rowIndex)[columnIndex];
        }

        return "";
    }
}
