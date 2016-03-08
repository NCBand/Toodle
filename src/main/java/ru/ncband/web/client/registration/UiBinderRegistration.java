package ru.ncband.web.client.registration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.shared.RegularExpressions;
import ru.ncband.web.shared.RegularExpressionsImpl;

public class UiBinderRegistration extends Composite {
    interface UiBinderRegistrationUiBinder extends UiBinder<HTMLPanel, UiBinderRegistration> {
    }

    private static UiBinderRegistrationUiBinder ourUiBinder = GWT.create(UiBinderRegistrationUiBinder.class);

    private String firstPassword;

    @UiField
    TextBox loginBox;
    @UiField
    TextBox email;
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
    Label statusFirstName;
    @UiField
    Label statusSecondName;


    private boolean tooShort = false;

    @UiHandler("loginButton")
    void doClickSubmit(ClickEvent event) {
        if (tooShort) {
            Window.alert("Success!");
        } else {
            Window.alert("Login or password is too short!");
        }
    }


    @UiHandler("firstNameBox")
    void checkFirstNameBox(ValueChangeEvent<String> event) {
        RegularExpressionsImpl testString = new RegularExpressions();
        if (testString.test(RegularExpressions.NAME, event.getValue())) {
            statusFirstName.setText("is Ok");
        } else {
            statusFirstName.setText(RegularExpressions.REQUIREMENT_FOR_NAME);
        }
    }

    @UiHandler("secondNameBox")
    void checkSecondNameBox(ValueChangeEvent<String> event) {
        RegularExpressionsImpl testString = new RegularExpressions();
        if (testString.test(RegularExpressions.NAME, event.getValue())) {
            statusSecondName.setText("is Ok");
        } else {
            statusSecondName.setText(RegularExpressions.REQUIREMENT_FOR_NAME);
        }
    }


    @UiHandler("loginBox")
    void checkLogin(ValueChangeEvent<String> event) {
        RegularExpressionsImpl testString = new RegularExpressions();
        if (testString.test(RegularExpressions.LOGIN, event.getValue())) {
            errorPassword.setText("is Ok");
        } else {
            errorPassword.setText(RegularExpressions.REQUIREMENT_FOR_LOGIN);
        }
    }

    @UiHandler("passwordBox")
    void checkPassword(ValueChangeEvent<String> event) {
        firstPassword = event.getValue();
        RegularExpressionsImpl testString = new RegularExpressions();
        if (testString.test(RegularExpressions.PASSWORD, firstPassword)) {
            errorPassword.setText("is Ok");
        } else {
            errorPassword.setText(RegularExpressions.REQUIREMENT_FOR_PASSWORD);
        }
    }

    @UiHandler("passwordRepeatBox")
    void checkRepeatPassword(ValueChangeEvent<String> event) {
        if (!event.getValue().equals(firstPassword)) {
            errorRepeatPassword.setText("doesn't match");
        } else {
            errorRepeatPassword.setText("is Ok!");
        }
    }

    public UiBinderRegistration() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
    }
}