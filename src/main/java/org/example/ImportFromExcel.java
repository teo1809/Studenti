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

    @Override
    public List<Student> importStudenti(String sheetName) {
        List<Student> studenti = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filename);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = (sheetName == null) ? workbook.getSheetAt(0) : workbook.getSheet(sheetName);
            if (sheet == null) return studenti;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                if (row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                    continue;
                }
                Cell c0 = row.getCell(0);
                Cell c1 = row.getCell(1);
                Cell c2 = row.getCell(2);
                Cell c3 = row.getCell(3);

                if (c0 != null && c1 != null && c2 != null && c3 != null) {
                    String nrMatricol = (c0.getCellType() == CellType.NUMERIC) ? String.valueOf((int) c0.getNumericCellValue()) : c0.getStringCellValue();
                    String prenume = (c1.getCellType() == CellType.NUMERIC) ? String.valueOf((int) c1.getNumericCellValue()) : c1.getStringCellValue();
                    String nume = (c2.getCellType() == CellType.NUMERIC) ? String.valueOf((int) c2.getNumericCellValue()) : c2.getStringCellValue();
                    String formatie = (c3.getCellType() == CellType.NUMERIC) ? String.valueOf((int) c3.getNumericCellValue()) : c3.getStringCellValue();

                    studenti.add(new Student(nrMatricol.trim(), prenume.trim(), nume.trim(), formatie.trim()));
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

    @Override
    public Map<String, Integer> importNote(String sheetName) {
        Map<String, Integer> noteMap = new HashMap<>();

        try (FileInputStream file = new FileInputStream(filename);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = (sheetName == null) ? workbook.getSheetAt(0) : workbook.getSheet(sheetName);
            if (sheet == null) return noteMap;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Cell c0 = row.getCell(0); // nr Matricol
                Cell c1 = row.getCell(1); // nota

                if (c0 != null && c1 != null) {
                    String nrMatricol = (c0.getCellType() == CellType.NUMERIC) ? String.valueOf((int) c0.getNumericCellValue()) : c0.getStringCellValue();

                    int nota = 0;
                    if (c1.getCellType() == CellType.NUMERIC) {
                        nota = (int) c1.getNumericCellValue();
                    } else if (c1.getCellType() == CellType.STRING) {
                        nota = Integer.parseInt(c1.getStringCellValue().trim());
                    }

                    if (!nrMatricol.isEmpty()) {
                        noteMap.put(nrMatricol.trim(), nota);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return noteMap;
    }
}