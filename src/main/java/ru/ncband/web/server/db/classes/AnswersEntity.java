package ru.ncband.web.server.db.classes;

import javax.persistence.*;

/**
 * Created by Дом on 08.04.2016.
 */
@Entity
@Table(name = "answers", schema = "toodle", catalog = "")
public class AnswersEntity {
    private int id;
    private String answer;
    private int taskId;
    private int right;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "task_id")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "right")
    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswersEntity that = (AnswersEntity) o;

        if (id != that.id) return false;
        if (taskId != that.taskId) return false;
        if (right != that.right) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + taskId;
        result = 31 * result + right;
        return result;
    }
}
