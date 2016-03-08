package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.AuthEvent;

public interface AuthHandler extends EventHandler {
    void onAuthenticationChanged(AuthEvent authenticationEvent);
}
