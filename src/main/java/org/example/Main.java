package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


   /*
    1) lista cu studenti
    2) o functie care returneaza true daca un student e prezent (se afla in lista)
    Student - prenume, nume, formatiune de studiu -> dupa astea verific, apelez functia si vad daca e cineva prezent sau
    nu
    list.add(new Student ("0123", "Alex"
    print(prezenta(new student(alex)   ->ar trebui sa afiseze true pe ecran
    nu tin cont de nr matricol ->poti sa pui null la nr matricol
    studenti creati de noi, nu fisier text



    public static bool prezenta(list<StudentLista>, Student student){
    for(Student s:lista){
    if(s.equals(student)
    return true;
    }
    //suprascrii equals -> equals intern cauta in camputile fara nrmatricol
    }
    return false;
     */


//complex= nr de date pe care il contine structura de date
// treeset, hashset
//arbore binar de cautare
//log spune cate cifre are numarul in binar
//33 de biti pt 8 miliarde; log in baza 2 din 8 miliarde =33
//prezenta cu complex o(1)


//citire din fisier, o functie; citire(parametru fisierul)
/*
list<student> citire(string filename){creez lista, citesc fisierul, separ dupa virgula, adaug in lista de array}
creeare fisier text -> studenti.CSV (comma separated values)
 0175, Alex, Dan, C221
 0125, Mihai, Pop, C153
 split dupa virgula



 //sortare dupa nume


 citirea notelor unui student dintr-un fiiser
 note.csv -> are
 key, value
 0123, nota


      key, value
 Map<String, Integer> citireNote( String fileName){ citeste si creeaza map}

 Map<..>=  new hashmap<>()(HASMAP/TREEMAP)
 nu se aduaga cu add -> put

 map.get(152) -> funtioneaza in o(1)
 functie care primeste un student si returneaza nota


 Integer nota(map<string, integer> note, Student s){
 return note.get(Student.nrmatr);
 }


Student cautat= new student (null, "alex", "doro", "221")
prezente(.. cautat) {}
nota( notestudenti, cautat){
map<string,integer> citireNota()
cream alt map
map<student,integer> notestudenti = createMap(studenti, notestudenti(fisierele))
}                                   facuta cu foreach

createMap a.i. sa mearga cautarea fara sa stiu care e nrmatricol -tema




 */


public class Main {
    public static void main(String[] args) {
        //Student student = new Student("3572", "Teodora", "Olteanu", "221");
        // System.out.println(student);
        List<Student> listaDinFisier = citireStudenti("studenti.csv");


        afisareListaNormala(listaDinFisier);

        sortareDupaNumeSiFormatiune(listaDinFisier);

        afisareListaSortata(listaDinFisier);

        Map<String, Integer> citireNote = citireNote("noteStudenti.csv");

        for (Map.Entry<String, Integer> intrare : citireNote.entrySet()) {
            System.out.println("Student: " + intrare.getKey() + " Nota: " + intrare.getValue());
        }

        List<Student> listaStudenti = new ArrayList<>();
        listaStudenti.add(new Student("0123", "Andreea", "Popescu", "221"));
        listaStudenti.add(new Student("4567", "Maria", "Ionescu", "212"));
        listaStudenti.add(new Student("9103", "Andrei", "Traian", "211"));
        listaStudenti.add(new Student("6216", "Gabriela", "Manole", "222"));

        Set<Student> setStudenti = new HashSet<>(listaStudenti);

//        setStudenti.add(new Student("Andreea", "Popescu", "0123", "221"));
//        setStudenti.add(new Student("Maria", "Ionescu", "4567", "212"));
//        setStudenti.add(new Student("Andrei", "Traian", "9103", "211"));
//        setStudenti.add(new Student("Gabriela", "Manole", "6216", "222"));
        Student studentCautat = new Student("0123", "Andreea", "Popescu", "221");

        System.out.println();
        if (prezenta(setStudenti, studentCautat)) {
            System.out.println("Studentul este prezent!");
        } else {
            System.out.println("Studentul NU este prezent!");
        }

        Integer notaStudent = nota(citireNote, studentCautat);

        if (notaStudent != null) {
            System.out.println("Nota studentului " + studentCautat.getNume() + " este: " + notaStudent);
        } else {
            System.out.println("nu exista o nota pentru acest student! " + studentCautat.getNumarMatricol());
        }

        // map pentru cautarea studentului fara nrMatricol
        Map<Student, Integer> mapNoteStudenti = createMapNoteStudenti(listaDinFisier, citireNote);

        Student studentCautatFaraNrMatricol = new Student(null, "Andreea", "Popescu", "221");

        printNota(mapNoteStudenti, studentCautatFaraNrMatricol);




        List<Student> lista = new ArrayList<>();
        lista.add(new Student("0123", "Andreea", "Popescu", "221"));
        lista.add(new Student("4567", "Maria", "Ionescu", "212"));

        ExporterConfiguration conf1 = new ExporterConfiguration("Lista1.csv", "lista1");
        ExporterConfiguration conf2 = new ExporterConfiguration("Lista2.csv", "lista2");

        Exporter e1 = new Exporter(conf1, lista);
        Exporter e2 = new Exporter(conf2, lista);
        e1.export();
        e2.export();


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

    private static void afisareListaNormala(List<Student> listaDinFisier) {
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

    //    public static boolean prezenta(List<Student> lista, Student student) {
//        for (Student s : lista) {
//            if (s.equals(student)) {
//                return true;
//            }
//        }
//        return false;
//    }
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





}


