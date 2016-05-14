package ru.ncband.web.shared.classes;

import java.util.List;

public class Lesson {
    private String id = null;
    private String name = null;
    private List<Task> tasks = null;

    public Lesson() {}

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
