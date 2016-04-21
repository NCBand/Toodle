package ru.ncband.web.shared.classes;

public class Answer {
    private String id;
    private int answer;

    public Answer() {}

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
