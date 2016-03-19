package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.RegistrationHandler;

public class RegistrationEvent extends GwtEvent<RegistrationHandler> {

    public static Type<RegistrationHandler> TYPE = new Type<RegistrationHandler>();

    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private String age;
    private String mail;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public Type<RegistrationHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RegistrationHandler handler) {
        handler.addUser(this);
    }
}
