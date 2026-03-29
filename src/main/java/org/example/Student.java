package org.example;

import java.util.Objects;

public class Student {
    String numarMatricol;
    String prenume;
    String nume;
    String formatieDeStudiu;
    Integer nota;


//    @Override
//    public int compareTo(Student o) {
//        return this.nume.compareTo(o.nume);
//    }

    public Student( String numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.prenume = prenume;
        this.nume = nume;
        this.numarMatricol = numarMatricol;
        this.formatieDeStudiu = formatieDeStudiu;

    }
    public Student( String numarMatricol, String prenume, String nume, String formatieDeStudiu, Integer nota) {
        this.prenume = prenume;
        this.nume = nume;
        this.numarMatricol = numarMatricol;
        this.formatieDeStudiu = formatieDeStudiu;
        this.nota=nota;

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
        if (o == null ) return false;
        Student student = (Student) o;
        return Objects.equals(prenume, student.prenume) &&
                Objects.equals(nume, student.nume) &&
                Objects.equals(formatieDeStudiu, student.formatieDeStudiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenume, nume, formatieDeStudiu);
    }



    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }
    public String getNumarMatricol() { return numarMatricol; }
    public String getFormatieDeStudiu() { return formatieDeStudiu; }

}
