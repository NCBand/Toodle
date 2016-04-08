package ru.ncband.web.server.db.classes;

import javax.persistence.*;

/**
 * Created by Дом on 08.04.2016.
 */
@Entity
@Table(name = "message", schema = "toodle", catalog = "")
public class MessageEntity {
    private int id;
    private String date;
    private String text;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity entity = (MessageEntity) o;

        if (id != entity.id) return false;
        if (date != null ? !date.equals(entity.date) : entity.date != null) return false;
        if (text != null ? !text.equals(entity.text) : entity.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
