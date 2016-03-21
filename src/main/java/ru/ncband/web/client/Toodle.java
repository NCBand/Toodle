package ru.ncband.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.SignInEvent;
import ru.ncband.web.client.events.ErrorAuthEvent;
import ru.ncband.web.client.events.SignUpEvent;
import ru.ncband.web.client.handlers.SignInHandler;
import ru.ncband.web.client.handlers.ErrorAuthHandler;
import ru.ncband.web.client.handlers.SignUpHandler;
import ru.ncband.web.client.services.SignIn;
import ru.ncband.web.client.widgets.header.HeaderWidget;
import ru.ncband.web.client.widgets.login.UiBinderLogin;
import ru.ncband.web.client.widgets.registration.UiBinderRegistration;

public class Toodle implements EntryPoint {
    public void onModuleLoad() {
        SimpleEventBus bus = new SimpleEventBus();
        Defaults.setServiceRoot(GWT.getHostPageBaseURL());

        RootPanel rootPanel = RootPanel.get();
        UiBinderLogin loginUI = new UiBinderLogin(bus);
        UiBinderRegistration regUI = new UiBinderRegistration(bus);
        HeaderWidget headerWidget = new HeaderWidget(bus);

        rootPanel.add(loginUI);
        rootPanel.add(regUI);
        regUI.setVisible(false);
        headerWidget.setVisible(false);

        bus.addHandler(SignInEvent.TYPE, new SignInHandler() {
            @Override
            public void onAuthenticationChanged(SignInEvent authenticationEvent) {
                SignIn checkUser = GWT.create(SignIn.class);
                checkUser.signIn(authenticationEvent.getLogin(), authenticationEvent.getPassword(), new MethodCallback<String>() {
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
                    public void onSuccess(Method method, String id) {
                        RootPanel rootPanel = RootPanel.get();
                        for (Widget widget : rootPanel) {
                            if(widget.getClass().equals(UiBinderLogin.class)) {
                                UiBinderLogin login = (UiBinderLogin) widget;
                                login.setErrorMessage(id);
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

        /*
        bus.addHandler(RegistrationEvent.TYPE, new RegistrationHandler() {
            @Override
            public void addUser(RegistrationEvent authenticationEvent) {
                /*Registr addUser = GWT.create(Registr.class);
                addUser.doRegistration(authenticationEvent.getForm(),
                                new MethodCallback<Null>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {

                            }

                            @Override
                            public void onSuccess(Method method, Null s){
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
        });*/
    }
}