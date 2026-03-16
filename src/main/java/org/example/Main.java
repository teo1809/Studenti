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
 */


public class Main {
    public static void main() {
        //Student student = new Student("3572", "Teodora", "Olteanu", "221");
        // System.out.println(student);
        List<Student> listaDinFisier = citire("studenti.csv");


        afisareListaNormala(listaDinFisier);

        sortareDupaNumeSiFormatiune(listaDinFisier);

        afisareListaSortata(listaDinFisier);


        List<Student> listaStudenti = new ArrayList<>();
        listaStudenti.add(new Student("0123","Andreea", "Popescu",  "221"));
        listaStudenti.add(new Student("4567","Maria", "Ionescu",  "212"));
        listaStudenti.add(new Student("9103", "Andrei", "Traian", "211"));
        listaStudenti.add(new Student("6216","Gabriela", "Manole", "222"));

        Set<Student> setStudenti = new HashSet<>(listaStudenti);

//        setStudenti.add(new Student("Andreea", "Popescu", "0123", "221"));
//        setStudenti.add(new Student("Maria", "Ionescu", "4567", "212"));
//        setStudenti.add(new Student("Andrei", "Traian", "9103", "211"));
//        setStudenti.add(new Student("Gabriela", "Manole", "6216", "222"));
        Student studentCautat = new Student( "0123","Andreea", "Popescu", "221");

        System.out.println();
        if (prezenta(setStudenti, studentCautat)) {
            System.out.println("Studentul este prezent!");
        } else {
            System.out.println("Studentul NU este prezent!");
        }
    }

    private static void afisareListaSortata(List<Student> listaDinFisier) {
        System.out.println();
        System.out.println("studentii sortati alfabetic dupa nume:");
        for (Student s : listaDinFisier) {
            System.out.println(s.getNume() + " " + s.getPrenume()+ " "+s.getNumarMatricol()+" "+ s.getFormatieDeStudiu());
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

    private static void sortareDupaNumeSiFormatiune(List<Student> listaDinFisier) {
        Collections.sort(listaDinFisier, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
               // return s1.nume.compareTo(s2.nume);//crescator; descresc inversez s1 cu s2
                if(s1.formatieDeStudiu.equals(s2.formatieDeStudiu)){
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
    public static List<Student> citire(String fileName) {
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
}



