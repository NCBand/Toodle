package ru.ncband.web.client.vidgets.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.events.AuthEvent;

public class UiBinderLogin extends Composite{
    interface UiBinderLoginUiBinder extends UiBinder<HTMLPanel, UiBinderLogin> {
    }

    private static UiBinderLoginUiBinder ourUiBinder = GWT.create(UiBinderLoginUiBinder.class);

    private final EventBus eventBus;

    @UiField
    TextBox loginBox;
    @UiField
    PasswordTextBox passwordBox;
    @UiField
    Label errorLabel;

    @UiHandler("loginButton")
    void doClickSubmit(ClickEvent event) {// TODO: 06.03.2016
        AuthEvent authEvent = GWT.create(AuthEvent.class);
        authEvent.setLogin(loginBox.getValue());
        authEvent.setPassword(passwordBox.getValue());
        eventBus.fireEvent(authEvent);
    }

    public UiBinderLogin(EventBus bus) {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
        this.eventBus = bus;
    }

    public void setErrorMessage(String text){
        errorLabel.setText(text);
    }
}