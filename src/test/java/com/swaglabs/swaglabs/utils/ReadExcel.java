package com.swaglabs.swaglabs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
    public Sheet readExcel(String filePath, String fileName, String SheetName) throws IOException {
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook excelWorkbook = null;
        String fileExtension = fileName.substring(fileName.indexOf("."));
        if (fileExtension.equals(".xlsx")) {
            excelWorkbook = new XSSFWorkbook(inputStream);
        } else if (fileExtension.equals(".xls")) {
            excelWorkbook = new HSSFWorkbook(inputStream);
        }
        Sheet excelSheet = excelWorkbook.getSheet(SheetName);
        return excelSheet;
    }

    public void executeScript(Sheet sheet, UIOperation operation, Properties properties) throws Exception {
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(0).toString().length() == 0) {
                System.out.println(row.getCell(1).toString() + "----" + row.getCell(2).toString() + "----"
                        + row.getCell(3).toString() + "----" + row.getCell(4).toString());
                operation.perform(properties, row.getCell(1).toString(), row.getCell(2).toString(),
                        row.getCell(3).toString(), row.getCell(4).toString());
            } else {
                System.out.println("New Testcase->" + row.getCell(0).toString() + " started.");
            }
        }
    }
}
