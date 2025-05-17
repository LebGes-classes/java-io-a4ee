package ru.itis.project.repository;

import ru.itis.project.model.Student;

import java.io.IOException;
import java.util.List;

public class StudentRepository {
    private ExcelReader excelReader = new ExcelReader();
    private List<Student> studentList;

    public StudentRepository() throws IOException {
        studentList = excelReader.readStudents();
    }

    public Student findStudentByName(String name){
        for (Student student: studentList){
            if (student.getName().equals(name)){
                return student;
            }
        }
        return null;
    }
}
