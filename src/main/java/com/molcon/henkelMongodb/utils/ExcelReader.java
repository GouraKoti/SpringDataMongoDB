package com.molcon.henkelMongodb.utils;

import com.molcon.henkelMongodb.model.Henkel;
import com.molcon.henkelMongodb.repo.HenkelRepo;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

      public void readInput(String source_excel, HenkelRepo henkelRepo) throws IOException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = new XSSFWorkbook(new FileInputStream(source_excel));

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // Using a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row : sheet) {
            String mcid = "";
            if (row.getCell(0) != null) {
                mcid=row.getCell(0).getStringCellValue();
            }

            String commonName = "";
            if (row.getCell(1) != null) {
                commonName = row.getCell(1).getStringCellValue();
            }

            String description = "";
            if (row.getCell(2) != null) {
                description = row.getCell(2).getStringCellValue();
            }

            String sapCs = "";
            if (row.getCell(3) != null) {
                sapCs = row.getCell(3).getStringCellValue();
            }

            Henkel henkel = new Henkel(mcid, commonName, description, sapCs);
            henkelRepo.save(henkel);
        }

        // Closing the workbook
        workbook.close();
    }
}