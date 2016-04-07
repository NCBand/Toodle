package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.LoadDataEventHandler;

/**
 * Created by Дом on 07.04.2016.
 */
public class LoadDataEvent extends GwtEvent<LoadDataEventHandler> {
    public static Type<LoadDataEventHandler> TYPE = new Type<LoadDataEventHandler>();

    public Type<LoadDataEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(LoadDataEventHandler handler) {
        handler.onLoadData(this);
    }
}
