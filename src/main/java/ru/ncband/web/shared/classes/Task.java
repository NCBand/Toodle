package ru.ncband.web.shared.classes;

import java.util.List;

public class Task {
    int type;
    List<String> texts = null;

    public Task(){}

    public int getType() {
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }
}
