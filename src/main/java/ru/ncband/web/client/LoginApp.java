package ru.ncband.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

/**
 * Created by rewweRrr on 16.02.2016.
 */
public class LoginApp implements EntryPoint {
    public void onModuleLoad() {

        RootPanel.get().add(new UiBinderLogin());
    }
}
