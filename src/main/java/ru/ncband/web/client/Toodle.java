package ru.ncband.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.AuthEvent;
import ru.ncband.web.client.events.ErrorAuthEvent;
import ru.ncband.web.client.events.SignUpEvent;
import ru.ncband.web.client.handlers.AuthHandler;
import ru.ncband.web.client.handlers.ErrorAuthHandler;
import ru.ncband.web.client.handlers.SignUpHandler;
import ru.ncband.web.client.services.UserService;
import ru.ncband.web.client.vidgets.login.UiBinderLogin;
import ru.ncband.web.client.vidgets.registration.UiBinderRegistration;
import ru.ncband.web.shared.Id;

public class Toodle implements EntryPoint {
    public void onModuleLoad() {
        SimpleEventBus bus = new SimpleEventBus();

        RootPanel rootPanel = RootPanel.get();
        UiBinderLogin loginUI = new UiBinderLogin(bus);
        UiBinderRegistration regUI = new UiBinderRegistration(bus);

        rootPanel.add(loginUI);
        rootPanel.add(regUI);
        regUI.setVisible(false);

        bus.addHandler(AuthEvent.TYPE, new AuthHandler() {
            @Override
            public void onAuthenticationChanged(AuthEvent authenticationEvent) {
                UserService userService = GWT.create(UserService.class);
                userService.getUser(authenticationEvent.getLogin(), authenticationEvent.getPassword(), new MethodCallback<Id>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        RootPanel rootPanel = RootPanel.get();
                        for (Widget widget : rootPanel) {
                            if(widget.getClass().equals(UiBinderLogin.class)) {
                                UiBinderLogin login = (UiBinderLogin) widget;
                                login.setErrorMessage("Not suitable login or password");
                            }
                        }
                    }

                    @Override
                    public void onSuccess(Method method, Id id) {
                        RootPanel rootPanel = RootPanel.get();
                        for (Widget widget : rootPanel) {
                            if(widget.getClass().equals(UiBinderLogin.class)) {
                                UiBinderLogin login = (UiBinderLogin) widget;
                                login.setErrorMessage("Good");
                            }
                        }
                    }
                });
            }
        });

        bus.addHandler(SignUpEvent.TYPE, new SignUpHandler() {
            @Override
            public void onSignUp(SignUpEvent signUpEvent) {
                RootPanel rootPanel = RootPanel.get();
                for (Widget widget : rootPanel) {
                    if(!widget.getClass().equals(UiBinderRegistration.class)) {
                        widget.setVisible(false);
                    }else {
                        widget.setVisible(true);
                    }
                }
            }
        });

        bus.addHandler(ErrorAuthEvent.TYPE, new ErrorAuthHandler() {
            @Override
            public void onAuthenticationChanged(ErrorAuthEvent authenticationEvent) {
                RootPanel rootPanel = RootPanel.get();
                for (Widget widget : rootPanel) {
                    if(!widget.getClass().equals(UiBinderLogin.class)) {
                        widget.setVisible(false);
                    }else {
                        widget.setVisible(true);
                    }
                }
            }
        });
    }
}