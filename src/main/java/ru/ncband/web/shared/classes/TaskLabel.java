package ru.ncband.web.shared.classes;

import java.util.List;

public class TaskLabel {
    private List<Integer> ids = null;
    private List<String> labels = null;

    public TaskLabel() {
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
