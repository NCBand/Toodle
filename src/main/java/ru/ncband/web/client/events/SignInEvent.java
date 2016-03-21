package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.SignInHandler;

public class SignInEvent extends GwtEvent<SignInHandler> {

    public static Type<SignInHandler> TYPE = new Type<SignInHandler>();

    private String login = null;
    private String password = null;

    public SignInEvent(){}

    @Override
    public Type<SignInHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SignInHandler handler) {
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
