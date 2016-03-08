package ru.ncband.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.AuthEvent;
import ru.ncband.web.client.handlers.AuthHandler;
import ru.ncband.web.client.services.UserService;
import ru.ncband.web.client.vidgets.login.UiBinderLogin;

public class Toodle implements EntryPoint {
    public void onModuleLoad() {
        final SimpleEventBus bus = new SimpleEventBus();
        RootPanel rootPanel = RootPanel.get();

        UiBinderLogin loginUI = new UiBinderLogin(bus);
        rootPanel.add(loginUI);

        bus.addHandler(AuthEvent.TYPE, new AuthHandler() {
            @Override
            public void onAuthenticationChanged(AuthEvent authenticationEvent) {
                UserService userService = GWT.create(UserService.class);
                userService.getUser(authenticationEvent.getLogin(), authenticationEvent.getPassword(), new MethodCallback<Id>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        //errorLabel.setText("Not suitable login or password");
                        int i = 0;
                    }

                    @Override
                    public void onSuccess(Method method, Id id) {
                        //errorLabel.setText("Good");
                        int i = 0;
                    }
                });
            }
        });
    }
}