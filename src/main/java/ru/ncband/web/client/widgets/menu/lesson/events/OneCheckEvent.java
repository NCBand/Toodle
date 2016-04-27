package ru.ncband.web.client.widgets.menu.lesson.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.widgets.menu.lesson.handlers.OneCheckEventHandler;

/**
 * Created by Дом on 27.04.2016.
 */
public class OneCheckEvent extends GwtEvent<OneCheckEventHandler> {
    public static Type<OneCheckEventHandler> TYPE = new Type<OneCheckEventHandler>();

    public Type<OneCheckEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(OneCheckEventHandler handler) {
        handler.onOneCheck(this);
    }
}
