package ru.itis.project.repository;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.itis.project.model.Grade;
import ru.itis.project.model.Student;
import ru.itis.project.model.Subject;
import ru.itis.project.model.Teacher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private static final String JOURNAL_FILE = "src/main/resources/journal.xlsx";

    private Workbook getWorkbook() throws IOException {
        FileInputStream file = new FileInputStream(new File(JOURNAL_FILE));
        return new XSSFWorkbook(file);
    }
    public List<Student> readStudents() throws IOException{
        List<Student> studentList = new ArrayList<>();
        try (Workbook workbook = getWorkbook()){
            Sheet sheet = workbook.getSheet("Students");
            for (Row row: sheet){
                int id = (int)row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String group = row.getCell(2).getStringCellValue();
                Student student = new Student(id,name,group);
                studentList.add(student);
            }
        }
        return studentList;
    }

    public List<Teacher> readTeachers() throws IOException{
        List<Teacher> teacherList = new ArrayList<>();
        try(Workbook workbook = getWorkbook()){
            Sheet sheet = workbook.getSheet("Teachers");
            for(Row row: sheet){
                int id = (int)row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String department = row.getCell(2).getStringCellValue();
                Teacher teacher = new Teacher(id,name,department);
                teacherList.add(teacher);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return teacherList;
    }
    public List<Subject> readSubject() throws IOException{
        List<Subject> subjectList = new ArrayList<>();
        try(Workbook workbook = getWorkbook()){
            Sheet sheet = workbook.getSheet("Subject");
            for(Row row: sheet){
                int id = (int)row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                int teacherID = (int)row.getCell(2).getNumericCellValue();
                Subject subject = new Subject(id,name,teacherID);
                subjectList.add(subject);
            }
        }
        return subjectList;
    }
    public List<Grade> readGrage() throws IOException{
        List<Grade> gradeList = new ArrayList<>();
        try(Workbook workbook = getWorkbook()){
            Sheet sheet = workbook.getSheet("Grades");
            for(Row row: sheet){
                int studentId = (int)row.getCell(0).getNumericCellValue();
                int subjectId = (int)row.getCell(1).getNumericCellValue();
                int grade = (int)row.getCell(2).getNumericCellValue();
                int teacherId = (int)row.getCell(3).getNumericCellValue();
                Grade grade1 = new Grade(studentId,subjectId,grade,teacherId);
                gradeList.add(grade1);
            }
        }
        return gradeList;
    }

}
