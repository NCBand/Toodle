package ru.ncband.web.client.events;


import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.LogOutHandler;

public class LogOutEvent extends GwtEvent<LogOutHandler> {

    public static Type<LogOutHandler> TYPE = new Type<LogOutHandler>();

    public LogOutEvent(){}

    @Override
    public Type<LogOutHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LogOutHandler handler) {
        handler.onAuthenticationChanged(this);
    }
}
