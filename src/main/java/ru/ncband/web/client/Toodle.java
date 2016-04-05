package ru.ncband.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.RegistrationEvent;
import ru.ncband.web.client.events.SignInEvent;
import ru.ncband.web.client.events.LogOutEvent;
import ru.ncband.web.client.events.SignUpEvent;
import ru.ncband.web.client.handlers.RegistrationHandler;
import ru.ncband.web.client.handlers.SignInHandler;
import ru.ncband.web.client.handlers.LogOutHandler;
import ru.ncband.web.client.handlers.SignUpHandler;
import ru.ncband.web.client.services.SignIn;
import ru.ncband.web.client.widgets.menu.UiMainSpace;
import ru.ncband.web.client.widgets.login.UiBinderLogin;
import ru.ncband.web.client.widgets.registration.UiBinderRegistration;
import ru.ncband.web.shared.Property;
import ru.ncband.web.shared.classes.Status;

public class Toodle implements EntryPoint {
    public void onModuleLoad() {
        SimpleEventBus bus = new SimpleEventBus();
        Defaults.setServiceRoot(GWT.getHostPageBaseURL());

        RootPanel rootPanel = RootPanel.get();
        UiBinderLogin loginUI = new UiBinderLogin(bus);
        UiBinderRegistration regUI = new UiBinderRegistration(bus);
        UiMainSpace mainSpace = new UiMainSpace(bus);

        rootPanel.add(loginUI);
        rootPanel.add(regUI);
        rootPanel.add(mainSpace);
        regUI.setVisible(false);
        mainSpace.setVisible(false);

        bus.addHandler(SignInEvent.TYPE, new SignInHandler() {
            @Override
            public void onAuthenticationChanged(SignInEvent authenticationEvent) {
                SignIn checkUser = GWT.create(SignIn.class);
                checkUser.signIn(authenticationEvent.getLogin(), authenticationEvent.getPassword(), new MethodCallback<Status>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        RootPanel rootPanel = RootPanel.get();
                        for (Widget widget : rootPanel) {
                            if(widget.getClass().equals(UiBinderLogin.class)) {
                                UiBinderLogin login = (UiBinderLogin) widget;
                                login.setErrorMessage("Server error");
                            }
                        }
                    }

                    @Override
                    public void onSuccess(Method method, Status id) {
                        RootPanel rootPanel = RootPanel.get();

                        if (id.getMsg().equals(Property.fault())){
                            for (Widget widget : rootPanel) {
                                if(widget.getClass().equals(UiBinderLogin.class)) {
                                    UiBinderLogin login = (UiBinderLogin) widget;
                                    login.setErrorMessage("Not suitable login or password");
                                }
                            }
                        }else {

                            for (Widget widget : rootPanel) {
                                if (widget.getClass().equals(UiBinderLogin.class)) {
                                    UiBinderLogin login = (UiBinderLogin) widget;
                                    login.clear();
                                }
                                if (widget.getClass().equals(UiMainSpace.class)) {
                                    widget.setVisible(true);
                                } else {
                                    widget.setVisible(false);
                                }
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

        bus.addHandler(LogOutEvent.TYPE, new LogOutHandler() {
            @Override
            public void onAuthenticationChanged(LogOutEvent authenticationEvent) {
                SignIn logOut = GWT.create(SignIn.class);
                logOut.signOut(new MethodCallback<Status>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, Status status) {
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


        bus.addHandler(RegistrationEvent.TYPE, new RegistrationHandler() {
            @Override
            public void addUser(RegistrationEvent registrationEvent) {
                SignIn addUser = GWT.create(SignIn.class);
                addUser.doRegistration( registrationEvent.getForm(),
                                        new MethodCallback<Status>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {
                                RootPanel rootPanel = RootPanel.get();
                                for (Widget widget : rootPanel) {
                                    if (widget.getClass().equals(UiBinderRegistration.class)) {
                                        ((UiBinderRegistration) widget).setMainError("Server error");
                                    }
                                }
                            }

                            @Override
                            public void onSuccess(Method method, Status s){
                                RootPanel rootPanel = RootPanel.get();
                                if(s.getMsg().equals(Property.fault())){
                                    for (Widget widget : rootPanel) {
                                        if (widget.getClass().equals(UiBinderRegistration.class)) {
                                            ((UiBinderRegistration)widget).setMainError("Error");
                                        }
                                    }
                                }else {
                                    for (Widget widget : rootPanel) {
                                        if (!widget.getClass().equals(UiBinderLogin.class)) {
                                            widget.setVisible(false);
                                        } else {
                                            widget.setVisible(true);
                                        }
                                    }
                                }
                            }
                        });
            }
        });
    }
}