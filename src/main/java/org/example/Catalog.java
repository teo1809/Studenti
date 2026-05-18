package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Catalog {
    private static final Catalog instance = new Catalog();

    // previne instantiere din exterior
    private Catalog() {
    }

    //metoda publica pentru instanta privata
    public static Catalog getInstance() {
        return instance;
    }

    public static Integer nota(Map<String, Integer> note, Student s) {
        return note.get(s.getNumarMatricol());
    }

    private static void afisareListaSortata(List<Student> listaDinFisier) {
        System.out.println();
        System.out.println("studentii sortati alfabetic dupa nume:");
        for (Student s : listaDinFisier) {
            System.out.println(s.getNume() + " " + s.getPrenume() + " " + s.getNumarMatricol() + " " + s.getFormatieDeStudiu());
        }
    }

    static void afisareListaNormala(List<Student> listaDinFisier) {
        System.out.println("studentii cititi din fisier:");
        if (listaDinFisier.isEmpty()) {
            System.out.println("nu exista studenti");
        } else {
            for (Student s : listaDinFisier) {
                System.out.println(s);
            }
        }
    }

    public static void sortareDupaNumeSiFormatiune(List<Student> listaDinFisier) {
        Collections.sort(listaDinFisier, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // return s1.nume.compareTo(s2.nume);//crescator; descresc inversez s1 cu s2
                if (s1.formatieDeStudiu.equals(s2.formatieDeStudiu)) {
                    return s1.nume.compareTo(s2.nume);
                }
                return s1.formatieDeStudiu.compareTo(s2.formatieDeStudiu);
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });
    }

    public static boolean prezenta(Collection<Student> colectie, Student student) {
        return colectie.contains(student);
    }

    public static List<Student> citireStudenti(String fileName) {
        List<Student> listaCreata = new ArrayList<>();

        try {
            File fisier = new File(fileName);
            Scanner cititor = new Scanner(fisier);

            while (cititor.hasNextLine()) {
                String linie = cititor.nextLine();
                String[] date = linie.split(",");

                if (date.length == 4) {
                    String nrMatricol = date[0].trim();
                    String prenume = date[1].trim();
                    String nume = date[2].trim();
                    String formatie = date[3].trim();
                    Student student1 = new Student(nrMatricol, prenume, nume, formatie);

                    listaCreata.add(student1);

                }
            }
            cititor.close();
        } catch (FileNotFoundException e) {
            System.out.println("nu exista fisierul");
            e.printStackTrace();
        }
        return listaCreata;
    }

    public static Map<String, Integer> citireNote(String fileName) {
        Map<String, Integer> hartaCreata = new HashMap<>();

        try {
            File fisier = new File(fileName);
            Scanner cititor = new Scanner(fisier);

            while (cititor.hasNextLine()) {
                String linie = cititor.nextLine();
                String[] date = linie.split(",");

                if (date.length == 2) {
                    String nume = date[0].trim();
                    Integer valoare = Integer.parseInt(date[1].trim());
                    hartaCreata.put(nume, valoare);
                }

            }
            cititor.close();
        } catch (FileNotFoundException e) {
            System.out.println("nu exista fisierul");
            e.printStackTrace();
        }
        return hartaCreata;
    }

    public static Map<Student, Integer> createMapNoteStudenti(List<Student> studenti, Map<String, Integer> noteDupaNrMatricol) {
        Map<Student, Integer> mapFinal = new HashMap<>();

        for (Student s : studenti) {
            Integer nota = noteDupaNrMatricol.get(s.getNumarMatricol());

            if (nota != null) {
                mapFinal.put(s, nota);
            }
        }
        return mapFinal;
    }

    private static void printNota(Map<Student, Integer> mapNoteStudenti, Student studentCautatFaraNrMatricol) {
        System.out.println("cautare nota fara nrMatricol");

        if (mapNoteStudenti.containsKey(studentCautatFaraNrMatricol)) {
            System.out.println("nota pentru " + studentCautatFaraNrMatricol.getNume() +
                    " este: " + mapNoteStudenti.get(studentCautatFaraNrMatricol));
        } else {
            System.out.println("studentul nu exista");
        }
    }


    public static void exportaStudentiExcel(List<Student> listaStudenti, String numeFisier) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Student Details");

        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"NR. MATRICOL", "PRENUME", "NUME", "FORMATIUNE"});

        int i = 2;
        for (Student s : listaStudenti) {
            data.put(String.valueOf(i), new Object[]{
                    s.getNumarMatricol(),
                    s.getPrenume(),
                    s.getNume(),
                    s.getFormatieDeStudiu()
            });
            i++;
        }
        int rowNum = 0;
        for (String key : data.keySet()) {
            Row row = sheet.createRow(rowNum++);
            Object[] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
                else if (obj == null)
                    cell.setCellValue("");
            }
        }

        try (FileOutputStream out = new FileOutputStream(numeFisier)) {
            workbook.write(out);
            System.out.println(numeFisier + " written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}