package ru.itis.project.service;

import ru.itis.project.model.Grade;
import ru.itis.project.model.Student;
import ru.itis.project.model.Teacher;
import ru.itis.project.repository.JsonSerializer;
import ru.itis.project.repository.TeacherRepository;

import java.io.IOException;
import java.util.List;

public class StudentService {
    private TeacherRepository teacherRepository;
    private static List<Grade> gradeList;

    public StudentService(){
        try {
            teacherRepository = new TeacherRepository();
            gradeList = JsonSerializer.deserializeList("src/main/resources/json/grade.json", Grade.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void checkGrade(Student student){
        System.out.println("Мои оценки");
        System.out.println("Предмет        || Оценка || Учитель");
        for (Grade grade: gradeList){
            if (grade.getStudent_id() == student.getId()){
                Teacher teacher = teacherRepository.findById(grade.getTeacher_id());
                System.out.printf("%-15s || %-4d || %-12s%n", teacher.getDepartment(),grade.getGradle(),teacher.getName());
            }
        }

    }
}
