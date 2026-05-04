package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExportToExcel implements ExporterInterface {
    private String filename;

    public ExportToExcel(String filename) {
        this.filename = filename;
    }

    @Override
    public void exportStudenti(List<Student> studenti) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Studenti");

        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"NR. MATRICOL", "PRENUME", "NUME", "FORMATIUNE"});

        int i = 2;
        for (Student s : studenti) {
            data.put(String.valueOf(i++), new Object[]{
                    s.getNumarMatricol(), s.getPrenume(), s.getNume(), s.getFormatieDeStudiu()
            });
        }

        scriereEfectiva(workbook, sheet, data);
    }

    private void scriereEfectiva(XSSFWorkbook workbook, XSSFSheet sheet, Map<String, Object[]> data) {
        int rowNum = 0;
        for (String key : data.keySet()) {
            Row row = sheet.createRow(rowNum++);
            Object[] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String) cell.setCellValue((String) obj);
                else if (obj instanceof Integer) cell.setCellValue((Integer) obj);
            }
        }
        try (FileOutputStream out = new FileOutputStream(filename)) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}