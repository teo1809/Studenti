package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void printNota_returnsCorrectValueWhenStudentExists() {
        // arrange
        Map<Student, Integer> mapNoteStudenti = new HashMap<>();
        Student s1 = new Student("0123", "Andreea", "Popescu", "221");
        mapNoteStudenti.put(s1, 10);
        // act
        boolean gasit = mapNoteStudenti.containsKey(s1);
        Integer notaObtinuta = mapNoteStudenti.get(s1);
        // assert
        assertTrue(gasit);
        assertEquals(10, notaObtinuta);
    }
    @Test
    void printNota_returnsNullWhenStudentDoesNotExist() {
            // arrange
            Map<Student, Integer> mapNoteStudenti = new HashMap<>();
            mapNoteStudenti.put(new Student("0123", "Andreea", "Popescu", "221"), 10);
            Student studentInexistent = new Student(null, "Mihai", "Pop", "221");
            // act
            boolean gasit = mapNoteStudenti.containsKey(studentInexistent);
            // assert
            assertFalse(gasit);
    }
    @Test
    void printNota_findsAndReturnsGradeWhenStudentMatchesWithoutMatricol() {
        // arrange
        Map<Student, Integer> mapNote = new HashMap<>();
        Student studentInBaza = new Student("0123", "Andreea", "Popescu", "221");
        mapNote.put(studentInBaza, 10);

        Student cautatFaraMatricol = new Student(null, "Andreea", "Popescu", "221");

        // act & assert
        assertTrue(mapNote.containsKey(cautatFaraMatricol));
        assertEquals(10, mapNote.get(cautatFaraMatricol));
    }
    @Test
    void prezenta_returnsTrueWhenStudentIsInCollection() {
        // arrange
        List<Student> studenti = new ArrayList<>();
        Student s = new Student("4567", "Maria", "Ionescu", "212");
        studenti.add(s);
        // act
        boolean estePrezent = Main.prezenta(studenti, s);
        // assert
        assertTrue(estePrezent);
    }
    @Test
    void prezenta_returnsFalseWhenStudentIsMissing() {
        // arrange
        List<Student> studenti = new ArrayList<>();
        studenti.add(new Student("4567", "Maria", "Ionescu", "212"));
        Student cautat = new Student("0000", "Necunoscut", "Nume", "000");
        // act
        boolean estePrezent = Main.prezenta(studenti, cautat);
        // assert
        assertFalse(estePrezent);
    }
    @Test
    void prezenta_returnsFalseWhenCollectionIsEmpty() {
        // arrange
        List<Student> listaGoala = new ArrayList<>();
        Student studentCautat = new Student("0123", "Andreea", "Popescu", "221");
        // act
        boolean rezultat = Main.prezenta(listaGoala, studentCautat);
        // assert
        assertFalse(rezultat, "returneaza flase daca colectia e goala");
    }
    @Test
    void sortareDupaNumeSiFormatiuneTest() {
        // arrange
        List<Student> lista = new ArrayList<>();
        Student s1 = new Student("1", "Zoe", "Vasile", "222");
        Student s2 = new Student("2", "Carina", "Ionescu", "221");
        Student s3 = new Student("3", "Ana", "Pop", "221");
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        // act
        Main.sortareDupaNumeSiFormatiune(lista);
        // assert
        assertEquals(s3, lista.get(0));
        assertEquals(s2, lista.get(1));
        assertEquals(s1, lista.get(2));
    }
    @Test
    void sortareDupaNumeSiFormatiune_ordersAlphabeticallyWhenInSameFormation() {
        // arrange
        List<Student> lista = new ArrayList<>();
        Student s1 = new Student("3", "Vasile", "Popescu", "221");
        Student s2 = new Student("1", "Ana", "Ionescu", "221");
        Student s3 = new Student("2", "Mihai", "Andrei", "221");
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        // act
        Main.sortareDupaNumeSiFormatiune(lista);
        // assert
        assertEquals(s3, lista.get(0));
        assertEquals(s2, lista.get(1));
        assertEquals(s1, lista.get(2));
    }
}