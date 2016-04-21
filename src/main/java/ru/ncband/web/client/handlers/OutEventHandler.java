package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.OutEvent;

public interface OutEventHandler extends EventHandler {
    void onOutOfTask(OutEvent event);
}
