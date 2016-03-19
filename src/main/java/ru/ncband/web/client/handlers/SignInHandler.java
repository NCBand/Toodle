package ru.ncband.web.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import ru.ncband.web.client.events.SignInEvent;

public interface SignInHandler extends EventHandler {
    void onAuthenticationChanged(SignInEvent authenticationEvent);
}
