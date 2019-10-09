package com.hexaware.frameworks.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ApiFramework {

    //method to turn a list into an array
    public String[] turnArray(ArrayList<String> list, int index) {
        String[] array = list.get(index).split(",");
        return array;
    }
    //Method to read a sheet and receive as string
    public String readSheet(Workbook workbook, int sheet) {
        Sheet sheets = workbook.getSheetAt(sheet);
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        // System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheets.rowIterator();
        String s = "";
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String s1 = "";
            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                String cellValue = dataFormatter.formatCellValue(cell);
                //System.out.print(cellValue + "\t");
                s1 += cellValue + "\t";
            }
            s += s1 + "\n";
        }
        return s;
    }

    //method to get data from a Excel in a list
    public ArrayList<String> readExcel(String filepath, int sheet) throws IOException {
        Workbook workbook = WorkbookFactory.create(new File(filepath));
        ArrayList<String> list = new ArrayList();
        ArrayList<String> list2 = new ArrayList();
        Sheet sheets = workbook.getSheetAt(sheet);
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        // System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheets.rowIterator();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String cadena = "";
            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
                cadena += cellValue + ",";
            }

            String cadena2 = cadena.substring(0,cadena.length()-1);
            list2.add(cadena2);
            list.clear();
        }
        return list2;
    }

// Method to get and object with all the data to create a report
    public Object getDataReport(String request, String response, int actualValue,  String [] data,String parameters){
        TestData object = new TestData();
        String action = data[data.length-3];
        int expectedValue = Integer.parseInt(data[data.length-1]);
        String theData="";

        if(actualValue==expectedValue) {
            object.setAction(action);
            object.setParameters(parameters);
            object.setStatus("Pass");
            object.setRequest(request);
            object.setResponse(response);
            object.setDescription("The actual value "
                    + actualValue + " matches with the expected result "+ expectedValue);

        } else {

            object.setAction(action);
            object.setParameters(parameters);
            object.setStatus("Fail");
            object.setRequest(request);
            object.setResponse(response);
            object.setDescription("The actual value "
                    + actualValue + " does not match with the expected result "+expectedValue);
        }
        for (int i=0;i<data.length-3;i++){
            theData+=data[i]+",";
        }
        object.setData(theData.substring(0,theData.length()-1));
        return object;
    }

    //method to convert object of arraylist into a jsonfile
    public void convertToJSON(ArrayList<Object> list, String jsonpath){
        try {
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter file = new FileWriter(jsonpath);
            file.write(prettyGson.toJson(list));
            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

    }







}
