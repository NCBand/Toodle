package ru.ncband.web.server.classes;

import java.io.Serializable;

public class Id implements Serializable{
    private String lesson_id = null;
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

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }
}
