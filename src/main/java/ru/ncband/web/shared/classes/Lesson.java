package ru.ncband.web.shared.classes;

import java.util.List;

public class Lesson {
    int id = 0;
    List<Task> tasks = null;

    public Lesson() {}

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
