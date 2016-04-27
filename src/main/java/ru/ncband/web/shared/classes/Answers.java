package ru.ncband.web.shared.classes;


import java.util.List;

public class Answers {
    private List<String> rights = null;
    private List<String> ids = null;

    public Answers() {
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getRights() {
        return rights;
    }

    public void setRights(List<String> rights) {
        this.rights = rights;
    }
}
