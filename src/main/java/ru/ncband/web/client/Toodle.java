package ru.ncband.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.vidgets.login.UiBinderLogin;

public class Toodle implements EntryPoint {
    public void onModuleLoad() {
        RootPanel.get().add(new UiBinderLogin());
    }
}
