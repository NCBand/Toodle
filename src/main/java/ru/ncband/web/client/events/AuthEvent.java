package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.AuthHandler;

public class AuthEvent extends GwtEvent<AuthHandler> {

    public static Type<AuthHandler> TYPE = new Type<AuthHandler>();

    private String login;
    private String password;

    @Override
    public Type<AuthHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AuthHandler handler) {
        handler.onAuthenticationChanged(this);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
