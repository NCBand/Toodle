package ru.ncband.web.client.events;


import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.SignUpHandler;

public class SignUpEvent extends GwtEvent<SignUpHandler> {

    public static Type<SignUpHandler> TYPE = new Type<SignUpHandler>();

    public SignUpEvent(){}

    @Override
    public Type<SignUpHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SignUpHandler handler) {
        handler.onSignUp(this);
    }
}
