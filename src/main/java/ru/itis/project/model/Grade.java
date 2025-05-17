package ru.itis.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Grade {
    private int student_id;
    private int subject_id;
    private int gradle;
    private int teacher_id;

    public Grade(@JsonProperty("student_id")int student_id,@JsonProperty("subject_id") int subject_id,@JsonProperty("gradle") int gradle,@JsonProperty("teacher_id") int teacher_id) {
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.gradle = gradle;
        this.teacher_id = teacher_id;
    }
    public Grade(){

    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getGradle() {
        return gradle;
    }

    public void setGradle(int gradle) {
        this.gradle = gradle;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }
}
