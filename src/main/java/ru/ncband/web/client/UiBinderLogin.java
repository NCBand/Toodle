package ru.ncband.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
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
    Label errorLabel1;
    @UiField
    Label errorLabel2;

    private boolean tooShort = false;

    @UiHandler("loginButton")
    void doClickSubmit(ClickEvent event) {
        if(tooShort){
            Window.alert("Success!");
        } else {
            Window.alert("Login or password is too short!");
        }
    }

    @UiHandler("loginBox")
    void checkLoginSize(ValueChangeEvent<String> event) {
        if (event.getValue().length() < 6) {
            errorLabel1.setText("Login too short (Size must be > 6)");
            tooShort = true;
        } else {
            tooShort = false;
            errorLabel1.setText("Size is Ok!");
        }
    }

    @UiHandler("passwordBox")
    void checkPasswordSize(ValueChangeEvent<String> event) {
        if (event.getValue().length() < 6) {
            tooShort = true;
            errorLabel2.setText("Password too short (Size must be > 6)");
        } else {
            tooShort = false;
            errorLabel2.setText("Size is Ok!");
        }
    }


    public UiBinderLogin() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
    }
}