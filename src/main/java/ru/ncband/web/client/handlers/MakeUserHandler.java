package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.MakeUserEvent;

public interface MakeUserHandler extends EventHandler {
    void addUser(MakeUserEvent authenticationEvent);
}
