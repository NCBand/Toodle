package ru.ncband.web.server.db.classes;

import javax.persistence.*;

@Entity
@Table(name = "tasks", schema = "toodle")
public class TasksEntity {
    private int id;
    private String question;
    private int type;
    private int lesson;
    private byte[] taskImage;

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
    @Column(name = "lesson")
    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TasksEntity that = (TasksEntity) o;

        return id == that.id && type == that.type &&
                lesson == that.lesson && (question != null ? question.equals(that.question) : that.question == null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + lesson;
        return result;
    }

    @Basic
    @Column(name = "task_image")
    public byte[] getTaskImage() {
        return taskImage;
    }

    public void setTaskImage(byte[] taskImage) {
        this.taskImage = taskImage;
    }
}
