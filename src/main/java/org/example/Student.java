package org.example;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(prenume, student.prenume) &&
                Objects.equals(nume, student.nume) &&
                Objects.equals(formatieDeStudiu, student.formatieDeStudiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenume, nume, formatieDeStudiu);
    }
}
