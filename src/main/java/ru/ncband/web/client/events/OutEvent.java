package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.OutEventHandler;

public class OutEvent extends GwtEvent<OutEventHandler> {
    public static Type<OutEventHandler> TYPE = new Type<OutEventHandler>();

    public Type<OutEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(OutEventHandler handler) {
        handler.onOutOfTask(this);
    }
}
