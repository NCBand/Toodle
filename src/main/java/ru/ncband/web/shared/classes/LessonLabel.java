package ru.ncband.web.shared.classes;

import java.util.List;

public class LessonLabel {
    private List<String> ids = null;
    private List<String> labels = null;

    public LessonLabel() {
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
