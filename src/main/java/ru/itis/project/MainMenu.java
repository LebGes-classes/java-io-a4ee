package ru.itis.project;

import ru.itis.project.model.Student;
import ru.itis.project.model.Teacher;
import ru.itis.project.repository.StudentRepository;
import ru.itis.project.repository.TeacherRepository;
import ru.itis.project.service.GradeService;
import ru.itis.project.service.StudentService;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static GradeService gradeService;
    private static TeacherRepository teacherRepository;
    private static StudentRepository studentRepository;
    private static StudentService studentService;

    static {
        try {
            studentService = new StudentService();
            gradeService = new GradeService();
            teacherRepository = new TeacherRepository();
            studentRepository = new StudentRepository();
        } catch (IOException e) {
            System.err.println("Ошибка");
        }
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("\nДобро пожаловать в электронный дневник.");
            System.out.println("Кто вы?");
            System.out.println("1. Студент");
            System.out.println("2. Преподаватель");
            System.out.println("3. Выход");
            System.out.print("Выберите вариант: ");

            try {
                int select = scanner.nextInt();
                scanner.nextLine();
                switch (select) {
                    case 1:
                        selectStudent();
                        break;
                    case 2:
                        selectTeacher();
                        break;
                    case 3:
                        System.out.println("До свидания!");
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор, попробуйте еще раз!");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Произошла ошибка, попробуйте еще раз!");
            }
        }
    }

    public static void selectStudent() {
        while (true) {
            System.out.print("\nПривет! Введи свое имя: ");
            String name = scanner.nextLine();
            Student student = studentRepository.findStudentByName(name);
            if (student != null) {
                studentMenu(student);
                return;

            }
            System.out.println("Студент не найдет. Попробуйте снова или введите выход для возврата");
            if (name.equalsIgnoreCase("выход")){
                return;
            }
        }

    }

    public static void selectTeacher() {
        while (true) {
            System.out.print("\nЗдравствуйте! Введите свое имя: ");
            String name = scanner.nextLine();

            Teacher teacher = teacherRepository.findTeacherByName(name);
            if (teacher != null) {
                teacherMenu(teacher);
                return;
            }

            System.out.println("Преподаватель не найден. Попробуйте снова или введите выход для возврата.");
            if (name.equalsIgnoreCase("выход")) {
                return;
            }
        }
    }

    public static void teacherMenu(Teacher teacher) {
        while (true) {
            System.out.println("\nЗдравствуйте, " + teacher.getName());
            System.out.println("1. Поставить оценку");
            System.out.println("2. Назад");
            System.out.print("Выберите действие: ");
            try {
                int select = scanner.nextInt();
                scanner.nextLine();

                switch (select) {
                    case 1:
                        gradeService.addGrade(teacher);
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Неправильная команда! Попробуйте еще раз.");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Поробуйте еще раз");
            }
        }
    }
    public static void studentMenu(Student student){
        while (true){
            System.out.println("\nЗдравствуйте!" + student.getName());
            System.out.println("1.Посмотреть свои оценки\n" +
                               "2.Выход");
            System.out.print("Выберите действие: ");
            try {
                int select = scanner.nextInt();
                switch (select){
                    case 1:
                        studentService.checkGrade(student);
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Неправильный выбор попробуй еще раз.");
                }
            }
            catch (Exception e){
                System.out.println("Попробуйте еще раз");
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}