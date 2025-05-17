package ru.itis.project.repository;

import ru.itis.project.model.Teacher;

import java.io.IOException;
import java.util.List;

public class TeacherRepository {
    ExcelReader excelReader = new ExcelReader();
    List<Teacher> teacherList = excelReader.readTeachers();

    public TeacherRepository() throws IOException {
    }
    public Teacher findTeacherByName(String name){
        for (Teacher teacher: teacherList){
            if (teacher.getName().equals(name)){
                return teacher;
            }
        }
        System.out.println("Учителя с таким именем нет");
        return null;
    }

    public Teacher findById(int id){
        for (Teacher teacher: teacherList){
            if (teacher.getId() == id){
                return teacher;
            }
        }
        return null;
    }
}
