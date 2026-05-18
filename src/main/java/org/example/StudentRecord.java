package org.example;

import java.util.Objects;

public record StudentRecord(
        String numarMatricol,
        String prenume,
        String nume,
        String formatieDeStudiu
) {
    public StudentRecord {

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
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