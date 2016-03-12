package ru.ncband.web.shared;

public class Id {
    private int id;
    private int hash;

    public Id(){}

    public Id( int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
}
