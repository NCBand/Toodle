package ru.ncband.web.binders.login;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

public class Login implements EntryPoint {
    public void onModuleLoad() {
        RootPanel.get().add(new UiBinderLogin());
    }
}
