package org.example;

import java.util.List;
import java.util.Map;

public interface ImporterInterface {
    List<Student> importStudenti(String sheetName);
    Map<String, Integer> importNote(String sheetName);
    Map<String,Integer> importNote();
    List<Student> importStudenti();
}