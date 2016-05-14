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
    private String answImg;

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
    public String getAnswImg() {
        return answImg;
    }

    public void setAnswImg(String answImg) {
        this.answImg = answImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswersEntity that = (AnswersEntity) o;

        return id == that.id && task == that.task && rght == that.rght && (answ != null ? answ.equals(that.answ) : that.answ == null && answImg.equals(that.answImg));

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (answ != null ? answ.hashCode() : 0);
        result = 31 * result + task;
        result = 31 * result + rght;
        result = 31 * result + answImg.hashCode();
        return result;
    }
}
