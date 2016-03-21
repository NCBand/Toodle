package ru.ncband.web.client.events;


import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.ErrorAuthHandler;

public class ErrorAuthEvent extends GwtEvent<ErrorAuthHandler> {

    public static Type<ErrorAuthHandler> TYPE = new Type<ErrorAuthHandler>();

    public ErrorAuthEvent(){}

    @Override
    public Type<ErrorAuthHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ErrorAuthHandler handler) {
        handler.onAuthenticationChanged(this);
    }
}
