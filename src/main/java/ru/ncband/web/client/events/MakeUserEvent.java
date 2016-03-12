package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.MakeUserHandler;

public class MakeUserEvent extends GwtEvent<MakeUserHandler> {

    public static Type<MakeUserHandler> TYPE = new Type<MakeUserHandler>();

    @Override
    public Type<MakeUserHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(MakeUserHandler handler) {
        handler.addUser(this);
    }
}
