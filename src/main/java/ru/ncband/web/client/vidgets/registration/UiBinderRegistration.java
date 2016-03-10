package ru.ncband.web.client.vidgets.registration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.shared.RegularExpressions;

public class UiBinderRegistration extends Composite {
    interface UiBinderRegistrationUiBinder extends UiBinder<HTMLPanel, UiBinderRegistration> {
    }

    private static UiBinderRegistrationUiBinder ourUiBinder = GWT.create(UiBinderRegistrationUiBinder.class);

    private EventBus eventBus;

    private String firstPassword;
    private boolean tooShort = false;

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

    @UiHandler("loginButton")
    void doClickSubmit(ClickEvent event) {
        if (tooShort) {
            Window.alert("Success!");
        } else {
            Window.alert("Login or password is too short!");
        }
    }

    /*
    @UiHandler("firstNameBox")
    void checkFirstNameBox(ValueChangeEvent<String> event) {
        RegularExpressions testString = com.google.gwt.core.shared.GWT.create(RegularExpressions.class);
        if (testString.test(testString.getNAME(), event.getValue())) {
            statusFirstName.setText("is Ok");
        } else {
            statusFirstName.setText(testString.getRequirementForName());
        }
    }

    @UiHandler("secondNameBox")
    void checkSecondNameBox(ValueChangeEvent<String> event) {
        RegularExpressions testString = com.google.gwt.core.shared.GWT.create(RegularExpressions.class);
        if (testString.test(testString.getNAME(), event.getValue())) {
            statusSecondName.setText("is Ok");
        } else {
            statusSecondName.setText(testString.getRequirementForName());
        }
    }


    @UiHandler("loginBox")
    void checkLogin(ValueChangeEvent<String> event) {
        RegularExpressions testString = com.google.gwt.core.shared.GWT.create(RegularExpressions.class);
        if (testString.test(testString.getLOGIN(), event.getValue())) {
            errorPassword.setText("is Ok");
        } else {
            errorPassword.setText(testString.getRequirementForLogin());
        }
    }

    @UiHandler("passwordBox")
    void checkPassword(ValueChangeEvent<String> event) {
        firstPassword = event.getValue();
        RegularExpressions testString = com.google.gwt.core.shared.GWT.create(RegularExpressions.class);
        if (testString.test(testString.getPASSWORD(), firstPassword)) {
            errorPassword.setText("is Ok");
        } else {
            errorPassword.setText(testString.getRequirementForPassword());
        }
    }

    @UiHandler("passwordRepeatBox")
    void checkRepeatPassword(ValueChangeEvent<String> event) {
        if (!event.getValue().equals(firstPassword)) {
            errorRepeatPassword.setText("doesn't match");
        } else {
            errorRepeatPassword.setText("is Ok!");
        }
    }*/

    public UiBinderRegistration(EventBus bus) {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
        this.eventBus = bus;
    }
}