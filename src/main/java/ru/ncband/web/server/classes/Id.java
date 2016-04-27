package ru.ncband.web.server.classes;

import java.io.Serializable;

public class Id implements Serializable{
    private String id = null;
    private String hash = null;

    public Id(){}

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
