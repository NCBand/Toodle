package ru.ncband.web.server.classes;

public class Id {
    private String id = null;
    private String hash = null;

    public Id(){}

    public Id( String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
