package ru.ncband.web.shared.classes;

import java.io.Serializable;

public class Status {
    private String msg = null;

    public Status() {
    }

    public Status(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Status " + msg;
    }
}
