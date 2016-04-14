package ru.ncband.web.server.db.classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "answers", schema = "toodle")
public class AnswersEntity {
    @GeneratedValue
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
    @Column(name = "answ")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "task")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "rght")
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

        return id == that.id && taskId == that.taskId &&
                right == that.right && (answer != null ? answer.equals(that.answer) : that.answer == null);

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
