package ru.ncband.web.server.db.classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tasks", schema = "toodle")
public class TasksEntity implements Serializable {
    @GeneratedValue
    private int id;
    private String question;
    private int type;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TasksEntity that = (TasksEntity) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
