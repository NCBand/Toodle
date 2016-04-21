package ru.ncband.web.shared.classes;

import java.util.List;

public class Lesson {
    private String id = null;
    private List<String> tasks = null;

    public Lesson() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }
}
