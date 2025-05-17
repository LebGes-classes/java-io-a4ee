package ru.itis.project;

import ru.itis.project.model.Grade;
import ru.itis.project.model.Student;
import ru.itis.project.model.Subject;
import ru.itis.project.model.Teacher;
import ru.itis.project.repository.ExcelReader;
import ru.itis.project.repository.JsonSerializer;

import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ExcelReader excelReader = new ExcelReader();
        try {
            List<Grade> gradeList = excelReader.readGrage();
            List<Teacher> teacherList = excelReader.readTeachers();
            List<Subject> subjectList = excelReader.readSubject();
            List<Student> studentList = excelReader.readStudents();
            JsonSerializer.serializeList(studentList,"src/main/resources/json/students.json");
            JsonSerializer.serializeList(gradeList,"src/main/resources/json/grade.json");
            JsonSerializer.serializeList(subjectList,"src/main/resources/json/subject.json");
            JsonSerializer.serializeList(teacherList,"src/main/resources/json/teacher.json");

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}