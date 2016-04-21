package ru.ncband.web.shared.classes;

import java.util.List;

public class Task {
    private String id;
    private String type;
    private List<String> answer_ids = null;
    private List<String> texts = null;

    public Task(){}

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getAnswer_ids() {
        return answer_ids;
    }

    public void setAnswer_ids(List<String> answer_ids) {
        this.answer_ids = answer_ids;
    }
}
