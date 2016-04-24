package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.FreshEvent;

public interface FreshEventHandler extends EventHandler {
    void onFresh(FreshEvent event);
}
