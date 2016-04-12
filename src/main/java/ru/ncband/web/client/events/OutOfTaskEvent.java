package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.OutOfTaskEventHandler;

public class OutOfTaskEvent extends GwtEvent<OutOfTaskEventHandler> {
    public static Type<OutOfTaskEventHandler> TYPE = new Type<OutOfTaskEventHandler>();

    public Type<OutOfTaskEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(OutOfTaskEventHandler handler) {
        handler.onOutOfTask(this);
    }
}
