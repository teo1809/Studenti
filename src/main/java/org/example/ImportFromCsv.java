package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ImportFromCsv implements ImporterInterface {
    private String filename;

    public ImportFromCsv(String filename) {
        this.filename = filename;
    }
    @Override
    public List<Student> importStudenti() {
        return importStudenti(null);
    }
    @Override
    public List<Student> importStudenti(String sursa) {
        List<Student> listaStudenti = new ArrayList<>();
        String fisierDeCitit = (sursa == null) ? this.filename : sursa;

        try (Scanner scanner = new Scanner(new File(fisierDeCitit))) {
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                String[] date = linie.split(",");
                if (date.length >= 4) {
                    listaStudenti.add(new Student(date[0].trim(), date[1].trim(), date[2].trim(), date[3].trim()));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fisierul CSV nu a fost gasit: " + fisierDeCitit);
        }
        return listaStudenti;
    }

    @Override
    public Map<String, Integer> importNote() {
        return importNote(null);
    }

    @Override
    public Map<String, Integer> importNote(String sursa) {
        Map<String, Integer> noteMap = new HashMap<>();
        String fisierDeCitit = (sursa == null) ? this.filename : sursa;

        try (Scanner scanner = new Scanner(new File(fisierDeCitit))) {
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                String[] date = linie.split(",");
                if (date.length >= 2) {
                    noteMap.put(date[0].trim(), Integer.parseInt(date[1].trim()));
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.err.println("Eroare la importul notelor din CSV.");
        }
        return noteMap;
    }
}