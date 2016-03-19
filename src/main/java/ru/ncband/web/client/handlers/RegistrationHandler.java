package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.RegistrationEvent;

public interface RegistrationHandler extends EventHandler {
    void addUser(RegistrationEvent authenticationEvent);
}
