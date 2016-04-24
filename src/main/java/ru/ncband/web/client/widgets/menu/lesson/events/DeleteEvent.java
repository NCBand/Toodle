package ru.ncband.web.client.widgets.menu.lesson.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.widgets.menu.lesson.handlers.DeleteEventHandler;

public class DeleteEvent extends GwtEvent<DeleteEventHandler> {
    public static Type<DeleteEventHandler> TYPE = new Type<DeleteEventHandler>();

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type<DeleteEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(DeleteEventHandler handler) {
        handler.onDeleteAnswer(this);
    }
}
