package ru.ncband.web.client.widgets.menu.lesson.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.widgets.menu.lesson.handlers.DeleteEventHandler;

public class DeleteEvent extends GwtEvent<DeleteEventHandler> {
    public static Type<DeleteEventHandler> TYPE = new Type<DeleteEventHandler>();

    private String id = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type<DeleteEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(DeleteEventHandler handler) {
        handler.onDeleteAnswer(this);
    }
}
