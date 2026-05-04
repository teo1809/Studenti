package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExportToCsv implements ExporterInterface {
    private String filename;

    public ExportToCsv(String filename) {
        this.filename = filename;
    }

    @Override
    public void exportStudenti(List<Student> studenti) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("NR. MATRICOL,PRENUME,NUME,FORMATIUNE\n");
            for (Student s : studenti) {
                writer.append(s.getNumarMatricol()).append(",")
                        .append(s.getPrenume()).append(",")
                        .append(s.getNume()).append(",")
                        .append(s.getFormatieDeStudiu()).append("\n");
            }
            System.out.println("Export CSV studenti reusit: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}