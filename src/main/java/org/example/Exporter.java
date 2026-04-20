package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Exporter {
    ExporterConfiguration config;
    List<Student> lista;

    public Exporter(ExporterConfiguration config, List<Student> lista) {
        this.config = config;
        this.lista = lista;
    }

    public void export() {
        System.out.println("Incepem exportul in: " + config.numeFisier);

        try (FileWriter writer = new FileWriter(config.numeFisier)) {

            writer.write("NrMatricol,Prenume,Nume,Grupa\n");

            for (Student s : lista) {
                String linieCsv = s.getNumarMatricol() + "," +
                        s.getPrenume() + "," +
                        s.getNume() + "," +
                        s.getFormatieDeStudiu() + "\n";

                writer.write(linieCsv);
            }

            System.out.println("exportat cu succes");

        } catch (IOException e) {
            System.out.println("eroare la scrierea fisierului " + e.getMessage());
        }
    }
}