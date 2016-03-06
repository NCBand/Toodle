package ru.ncband.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.services.UserService;
import ru.ncband.web.client.vidgets.login.UiBinderLogin;
import ru.ncband.web.shared.Id;

public class Toodle implements EntryPoint {
    public void onModuleLoad() {
        RootPanel.get().add(new UiBinderLogin());

        String login = "";
        String password = "";

        UserService userService = GWT.create(UserService.class);
        userService.getUser(login, password, new MethodCallback<Id>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Id id) {

            }
        });
    }
}
