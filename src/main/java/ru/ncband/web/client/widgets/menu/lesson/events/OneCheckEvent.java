package ru.ncband.web.client.widgets.menu.lesson.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.widgets.menu.lesson.handlers.OneCheckEventHandler;

public class OneCheckEvent extends GwtEvent<OneCheckEventHandler> {
    public static Type<OneCheckEventHandler> TYPE = new Type<OneCheckEventHandler>();

    private String id = null;

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type<OneCheckEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(OneCheckEventHandler handler) {
        handler.onOneCheck(this);
    }
}
