package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main() {
        //Student student = new Student("3572", "Teodora", "Olteanu", "221");
       // System.out.println(student);


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


        List<Student> listaStudenti = new ArrayList<>();

        listaStudenti.add(new Student("Andreea", "Popescu", "0123", "221"));
        listaStudenti.add(new Student("Maria","Ionescu","4567",   "212"));
        listaStudenti.add(new Student("Andrei", "Traian", "9103", "211"));
        listaStudenti.add(new Student("Gabriela","Manole","6216",   "222"));

        Student studentCautat = new Student("Andreea", "Popescu",null,  "221");


        if (prezenta(listaStudenti, studentCautat)) {
            System.out.println("Studentul este prezent (True)");
        } else {
            System.out.println("Studentul NU este prezent (False)");
        }
    }


    public static boolean prezenta(List<Student> lista, Student student) {
        for (Student s : lista) {
            if (s.equals(student)) {
                return true;
            }
        }
        return false;
    }
}

