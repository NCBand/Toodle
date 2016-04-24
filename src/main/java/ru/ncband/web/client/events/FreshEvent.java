package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.FreshEventHandler;

public class FreshEvent extends GwtEvent<FreshEventHandler> {
    public static Type<FreshEventHandler> TYPE = new Type<FreshEventHandler>();

    public Type<FreshEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(FreshEventHandler handler) {
        handler.onFresh(this);
    }
}
