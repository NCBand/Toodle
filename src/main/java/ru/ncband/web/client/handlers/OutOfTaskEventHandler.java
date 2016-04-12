package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.OutOfTaskEvent;

public interface OutOfTaskEventHandler extends EventHandler {
    void onOutOfTask(OutOfTaskEvent event);
}
