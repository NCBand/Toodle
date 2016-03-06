package ru.ncband.web.client.registration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class UiBinderRegistration extends Composite{
    interface UiBinderRegistrationUiBinder extends UiBinder<HTMLPanel, UiBinderRegistration> {
    }

    private static UiBinderRegistrationUiBinder ourUiBinder = GWT.create(UiBinderRegistrationUiBinder.class);

    private String firstPassword;

    @UiField
    TextBox loginBox;
    @UiField
    PasswordTextBox passwordBox;
    @UiField
    PasswordTextBox passwordRepeatBox;
    @UiField
    Label errorLogin;
    @UiField
    Label errorPassword;
    @UiField
    Label errorRepeatPassword;
    @UiField
    TextBox firstNameBox;

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
            errorLogin.setText("must be more than 6 characters");
            tooShort = true;
        } else {
            tooShort = false;
            errorLogin.setText("is Ok!");
        }
    }

    @UiHandler("passwordBox")

    void checkPassword(ValueChangeEvent<String> event) {
        firstPassword = event.getValue();
        RegExp regExp = RegExp.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");
        if (regExp.test(firstPassword)) {
            tooShort = true;
            errorPassword.setText("is Ok");
        } else {
            tooShort = false;
            errorPassword.setText("must contain uppercase and lowercase letters, numbers");
        }
    }



    @UiHandler("passwordRepeatBox")
    void checkRepeatPassword(ValueChangeEvent<String> event) {
        if (!event.getValue().equals(firstPassword)) {
            tooShort = true;
            errorRepeatPassword.setText("doesn't match");
        } else {
            tooShort = false;
            errorRepeatPassword.setText("is Ok!");
        }
    }


    public UiBinderRegistration() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
    }
}