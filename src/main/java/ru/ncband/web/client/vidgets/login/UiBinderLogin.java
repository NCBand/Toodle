package ru.ncband.web.client.vidgets.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

public class UiBinderLogin extends Composite{
    interface UiBinderLoginUiBinder extends UiBinder<HTMLPanel, UiBinderLogin> {
    }

    private static UiBinderLoginUiBinder ourUiBinder = GWT.create(UiBinderLoginUiBinder.class);

    @UiField
    TextBox loginBox;
    @UiField
    PasswordTextBox passwordBox;
    @UiField
    Label errorLabel;

    @UiHandler("loginButton")
    void doClickSubmit(ClickEvent event) {// TODO: 06.03.2016  

    }

    public UiBinderLogin() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
    }
}