package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.ErrorAuthEvent;

public interface ErrorAuthHandler extends EventHandler {
    void onAuthenticationChanged(ErrorAuthEvent authenticationEvent);
}
