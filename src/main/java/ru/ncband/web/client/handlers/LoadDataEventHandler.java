package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.LoadDataEvent;

public interface LoadDataEventHandler extends EventHandler {
    void onLoadData(LoadDataEvent event);
}
