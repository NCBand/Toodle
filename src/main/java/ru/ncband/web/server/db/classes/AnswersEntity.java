package ru.ncband.web.server.db.classes;

import javax.persistence.*;

@Entity
@Table(name = "answers", schema = "toodle")
public class AnswersEntity {
    @GeneratedValue
    private int id;
    private String answ;
    private int task;
    private int rght;
    private byte[] answImg;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "answ")
    public String getAnsw() {
        return answ;
    }

    public void setAnsw(String answ) {
        this.answ = answ;
    }

    @Basic
    @Column(name = "task")
    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    @Basic
    @Column(name = "rght")
    public int getRght() {
        return rght;
    }

    public void setRght(int rght) {
        this.rght = rght;
    }

    @Basic
    @Column(name = "answ_img")
    public byte[] getAnswImg() {
        return answImg;
    }

    public void setAnswImg(byte[] answImg) {
        this.answImg = answImg;
    }
}
