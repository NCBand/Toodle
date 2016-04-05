package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.LogOutEvent;

public interface LogOutHandler extends EventHandler {
    void onAuthenticationChanged(LogOutEvent authenticationEvent);
}
