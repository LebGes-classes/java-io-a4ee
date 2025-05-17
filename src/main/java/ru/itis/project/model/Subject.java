package ru.itis.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Subject {
    private int id;
    private String name;
    private int teacher_id;


    public Subject(@JsonProperty("id") int id,@JsonProperty("name") String name,@JsonProperty("teacher_id") int teacher_id) {
        this.id = id;
        this.name = name;
        this.teacher_id = teacher_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }
}
