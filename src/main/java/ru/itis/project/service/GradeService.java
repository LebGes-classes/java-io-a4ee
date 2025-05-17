package ru.itis.project.service;

import ru.itis.project.model.Grade;
import ru.itis.project.model.Student;
import ru.itis.project.model.Teacher;
import ru.itis.project.repository.ExcelReader;
import ru.itis.project.repository.JsonSerializer;

import java.io.IOException;
import java.util.*;

public class GradeService {
    private ExcelReader excelReader = new ExcelReader();
    private List<Grade> gradeList;
    private List<Student> studentList;
    private Scanner scanner = new Scanner(System.in);
    private static final int MAX_SCORE = 5;
    private static final int MIN_SCORE = 1;

    public GradeService() {
        try {
            this.studentList = JsonSerializer.deserializeList("src/main/resources/json/students.json", Student.class);
            this.gradeList = JsonSerializer.deserializeList("src/main/resources/json/grade.json", Grade.class);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addGrade(Teacher teacher) throws IOException {
        if (studentList == null || studentList.isEmpty()) {
            System.out.println("Список студентов пуст или не загружен!");
            return;
        }

        System.out.println("\nСписок студентов:");
        for (Student student : studentList) {
            System.out.printf("%d. %s Группа: %s)\n",
                    student.getId(), student.getName(), student.getGroup());
        }

        Student selectedStudent = null;
        while (selectedStudent == null) {
            System.out.print("\nВведите ID студента: ");
            try {
                int id = scanner.nextInt();

                if (id == 0) {
                    System.out.println("Отмена");
                    return;
                }

                for (Student student : studentList) {
                    if (student.getId() == id) {
                        selectedStudent = student;
                        break;
                    }
                }

                if (selectedStudent == null) {
                    System.out.println("Студент с таким ID не найден! Попробуйте еще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        int score;
        while (true) {
            System.out.print("Введите оценку: ");
            try {
                score = scanner.nextInt();
                scanner.nextLine();

                if (score >= MIN_SCORE && score <= MAX_SCORE) {
                    break;
                }
                System.out.println("Оценка должна быть от " + MIN_SCORE + " до " + MAX_SCORE);
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число от 1 до 5!");
                scanner.nextLine();
            }
        }

        Grade newGrade = new Grade(selectedStudent.getId(), teacher.getId(), score, teacher.getId());
        gradeList.add(newGrade);

        try {
            JsonSerializer.serializeList(gradeList, "src/main/resources/json/grade.json");
            System.out.println("Оценка успешно добавлена для студента " +
                    selectedStudent.getName() + "!");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении оценки: " + e.getMessage());
            throw e;
        }
    }

}
