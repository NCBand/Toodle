package ru.ncband.web.shared.classes;

import java.util.List;

public class Ids {
    private String type = null;
    private List<String> ids = null;

    public Ids() {
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
