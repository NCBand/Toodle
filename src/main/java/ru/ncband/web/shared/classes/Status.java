package ru.ncband.web.shared.classes;

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
}
