package ru.ncband.web.shared.classes;


import java.util.List;

public class Answer {
    private int id;
    private List<Integer> answers = null;

    public Answer() {}

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
