package ru.ncband.web.client.widgets.menu.lesson.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.widgets.menu.lesson.events.DeleteEvent;

public interface DeleteEventHandler extends EventHandler {
    void onDeleteAnswer(DeleteEvent event);
}
