package ru.itis.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    private int id;
    private String name;
    private String group;

    public Student(@JsonProperty("id") int id,@JsonProperty("name") String name,@JsonProperty("group") String group) {
        this.id = id;
        this.name = name;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
