package ru.ncband.web.shared.classes;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.JsonEncoderDecoder;

public class Id {
    private int id = 0;
    private int hash = 0;

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
