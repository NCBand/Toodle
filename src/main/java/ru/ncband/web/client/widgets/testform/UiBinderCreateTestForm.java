package ru.ncband.web.client.widgets.testform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class UiBinderCreateTestForm extends Composite{
    interface UiBinderLoginUiBinder extends UiBinder<HTMLPanel, UiBinderCreateTestForm> {
    }

    private static UiBinderLoginUiBinder ourUiBinder = GWT.create(UiBinderLoginUiBinder.class);

//    private EventBus eventBus;
//
//    @UiField
//    TextBox loginBox;
//    @UiField
//    PasswordTextBox passwordBox;
//    @UiField
//    Label errorLabel;
//
//    @UiHandler("loginButton")
//    void doClickSubmit(ClickEvent event) {
//        SignInEvent authEvent = GWT.create(SignInEvent.class);
//        authEvent.setLogin(loginBox.getValue());
//        authEvent.setPassword(passwordBox.getValue());
//        eventBus.fireEvent(authEvent);
//    }
//
//    @UiHandler("registration")
//    void doSigning(ClickEvent event){
//        SignUpEvent signup = GWT.create(SignUpEvent.class);
//        eventBus.fireEvent(signup);
//        errorLabel.setText("Sign in");
//    }
//
//    public UiBinderCreateTestForm(EventBus bus) {
//        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
//        initWidget(rootElement);
//        this.eventBus = bus;
//    }
//
//    public void setErrorMessage(String text){
//        errorLabel.setText(text);
//    }
}