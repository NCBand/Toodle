package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.SignUpEvent;

public interface SignUpHandler extends EventHandler {
    void onAuthenticationChanged(SignUpEvent authenticationEvent);
}
