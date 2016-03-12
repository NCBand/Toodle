package ru.ncband.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.AuthEvent;
import ru.ncband.web.client.events.ErrorAuthEvent;
import ru.ncband.web.client.events.MakeUserEvent;
import ru.ncband.web.client.events.SignUpEvent;
import ru.ncband.web.client.handlers.AuthHandler;
import ru.ncband.web.client.handlers.ErrorAuthHandler;
import ru.ncband.web.client.handlers.MakeUserHandler;
import ru.ncband.web.client.handlers.SignUpHandler;
import ru.ncband.web.client.services.AddUser;
import ru.ncband.web.client.services.CheckUser;
import ru.ncband.web.client.vidgets.login.UiBinderLogin;
import ru.ncband.web.client.vidgets.registration.UiBinderRegistration;
import ru.ncband.web.shared.Id;

import javax.validation.constraints.Null;

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
                CheckUser checkUser = GWT.create(CheckUser.class);
                checkUser.getUser(authenticationEvent.getLogin(), authenticationEvent.getPassword(), new MethodCallback<Id>() {
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

        bus.addHandler(MakeUserEvent.TYPE, new MakeUserHandler() {
            @Override
            public void addUser(MakeUserEvent authenticationEvent) {
                AddUser addUser = GWT.create(AddUser.class);
                addUser.setUser(authenticationEvent.getFirstname(),
                        authenticationEvent.getLastname(),
                        authenticationEvent.getLogin(),
                        authenticationEvent.getMail(),
                        authenticationEvent.getPassword(),
                        authenticationEvent.getAge(),
                        authenticationEvent.getSex(),
                        new MethodCallback<Null>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {

                            }

                            @Override
                            public void onSuccess(Method method, Null aNull) {
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
        });
    }
}