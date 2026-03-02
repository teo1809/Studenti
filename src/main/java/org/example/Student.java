package org.example;

public class Student {
    String numarMatricol;
    String prenume;
    String nume;
    String formatieDeStudiu;

    public Student(String prenume, String nume, String numarMatricol, String formatieDeStudiu) {
        this.prenume = prenume;
        this.nume = nume;
        this.numarMatricol = numarMatricol;
        this.formatieDeStudiu = formatieDeStudiu;
    }

    @Override
    public String toString() {
        return "Student{" +
                "numarMatricol='" + numarMatricol + '\'' +
                ", prenume='" + prenume + '\'' +
                ", nume='" + nume + '\'' +
                ", formatieDeStudiu='" + formatieDeStudiu + '\'' +
                '}';
    }
}
