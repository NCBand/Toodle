package ru.ncband.web.client.widgets.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.events.SignInEvent;
import ru.ncband.web.client.events.SignUpEvent;

public class UiBinderLogin extends Composite{
    interface UiBinderLoginUiBinder extends UiBinder<HTMLPanel, UiBinderLogin> {
    }

    private static UiBinderLoginUiBinder ourUiBinder = GWT.create(UiBinderLoginUiBinder.class);

    private EventBus eventBus;

    @UiField
    TextBox loginBox;
    @UiField
    PasswordTextBox passwordBox;
    @UiField
    Label errorLabel;

    @UiHandler("loginButton")
    void doClickSubmit(ClickEvent event) {
        SignInEvent signInEvent = GWT.create(SignInEvent.class);
        signInEvent.setLogin(loginBox.getValue());
        signInEvent.setPassword(passwordBox.getValue());
        eventBus.fireEvent(signInEvent);
    }

    @UiHandler("registration")
    void doSigning(ClickEvent event){
        SignUpEvent signup = GWT.create(SignUpEvent.class);
        clear();
        eventBus.fireEvent(signup);
    }

    public UiBinderLogin(EventBus bus) {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
        this.eventBus = bus;
    }

    public void setErrorMessage(String text){
        errorLabel.setText(text);
    }

    public void clear(){
        loginBox.setValue("");
        passwordBox.setValue("");
    }
}