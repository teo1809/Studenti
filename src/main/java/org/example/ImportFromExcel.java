package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ImportFromExcel implements ImporterInterface {
    private String filename;

    public ImportFromExcel(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Student> importStudenti() {
        return importStudenti(null);
    }

    public List<Student> importStudenti(String sheetName) {
        List<Student> studenti = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filename);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = (sheetName == null) ? workbook.getSheetAt(0) : workbook.getSheet(sheetName);
            if (sheet == null) return studenti;

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) continue;

                List<String> rowData = new ArrayList<>();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf((int) cell.getNumericCellValue()));
                            break;
                        default:
                            break;
                    }
                }

                if (rowData.size() >= 4) {
                    studenti.add(new Student(rowData.get(0), rowData.get(1), rowData.get(2), rowData.get(3)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studenti;
    }

    @Override
    public Map<String, Integer> importNote() {
        return importNote(null);
    }

    public Map<String, Integer> importNote(String sheetName) {
        Map<String, Integer> noteMap = new HashMap<>();

        try (FileInputStream file = new FileInputStream(filename);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = (sheetName == null) ? workbook.getSheetAt(0) : workbook.getSheet(sheetName);
            if (sheet == null) return noteMap;

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) continue;

                String nrMatricol = "";
                Integer nota = 0;
                Iterator<Cell> cellIterator = row.cellIterator();
                int colIndex = 0;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (colIndex == 0) {
                        if (cell.getCellType() == CellType.STRING) nrMatricol = cell.getStringCellValue();
                        else if (cell.getCellType() == CellType.NUMERIC) nrMatricol = String.valueOf((int)cell.getNumericCellValue());
                    } else if (colIndex == 1) {
                        if (cell.getCellType() == CellType.NUMERIC) nota = (int) cell.getNumericCellValue();
                        else if (cell.getCellType() == CellType.STRING) nota = Integer.parseInt(cell.getStringCellValue());
                    }
                    colIndex++;
                }
                if (!nrMatricol.isEmpty()) {
                    noteMap.put(nrMatricol, nota);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noteMap;
    }
}