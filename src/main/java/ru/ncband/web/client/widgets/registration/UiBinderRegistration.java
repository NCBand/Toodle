package ru.ncband.web.client.widgets.registration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.events.ErrorAuthEvent;
import ru.ncband.web.client.events.RegistrationEvent;
import ru.ncband.web.shared.RegularExpressions;
import ru.ncband.web.shared.classes.Registration;

public class UiBinderRegistration extends Composite {
    interface UiBinderRegistrationUiBinder extends UiBinder<HTMLPanel, UiBinderRegistration> {
    }

    private static UiBinderRegistrationUiBinder ourUiBinder = GWT.create(UiBinderRegistrationUiBinder.class);

    private EventBus eventBus;

    private String firstPassword;

    @UiField
    TextBox firstNameBox;
    @UiField
    TextBox secondNameBox;
    @UiField
    TextBox loginBox;
    @UiField
    TextBox email;
    @UiField
    TextBox ageBox;
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
    Label errorAge;
    @UiField
    Label statusFirstName;
    @UiField
    Label statusSecondName;
    @UiField
    ListBox sexBox;

    @UiHandler("loginButton")
    void doClickSubmit(ClickEvent event) {
        if(passwordBox.getValue().equals(passwordRepeatBox.getValue())) {
            RegistrationEvent registrationEvent = new RegistrationEvent();
            Registration registration = new Registration();
            registration.setLogin(loginBox.getValue());
            registration.setPassword(passwordBox.getValue());
            registration.setAge(ageBox.getValue());
            registration.setSex(sexBox.getSelectedValue());
            registration.setFirstname(firstNameBox.getValue());
            registration.setLastname(secondNameBox.getValue());
            eventBus.fireEvent(registrationEvent);
        }else {
            Window.setTitle("Wrong!");
        }
    }

    @UiHandler("backButton")
    void doBack(ClickEvent event){
        ErrorAuthEvent errorAuthEvent = new ErrorAuthEvent();
        eventBus.fireEvent(errorAuthEvent);
    }

    @UiHandler("firstNameBox")
    void checkFirstNameBox(ValueChangeEvent<String> event) {
        if (RegularExpressions.test(RegularExpressions.NAME, event.getValue())) {
            statusFirstName.setText("is Ok");
        } else {
            statusFirstName.setText(RegularExpressions.REQUIREMENT_FOR_NAME);
        }
    }

    @UiHandler("secondNameBox")
    void checkSecondNameBox(ValueChangeEvent<String> event) {
        if (RegularExpressions.test(RegularExpressions.NAME, event.getValue())) {
            statusSecondName.setText("is Ok");
        } else {
            statusSecondName.setText(RegularExpressions.REQUIREMENT_FOR_NAME);
        }
    }

    @UiHandler("loginBox")
    void checkLogin(ValueChangeEvent<String> event) {
        if (RegularExpressions.test(RegularExpressions.LOGIN, event.getValue())) {
            errorLogin.setText("is Ok");
        } else {
            errorLogin.setText(RegularExpressions.REQUIREMENT_FOR_LOGIN);
        }
    }

    @UiHandler("ageBox")
    void checkAge(ValueChangeEvent<String> event) {
        if (RegularExpressions.test(RegularExpressions.AGE, event.getValue())) {
            errorAge.setText("is Ok");
        } else {
            errorAge.setText(RegularExpressions.REQUIREMENT_FOR_AGE);
        }
    }

    @UiHandler("passwordBox")
    void checkPassword(ValueChangeEvent<String> event) {
        firstPassword = event.getValue();
        if (RegularExpressions.test(RegularExpressions.PASSWORD, firstPassword)) {
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

    public UiBinderRegistration(EventBus bus) {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
        this.eventBus = bus;
    }

    void clear(){

    }
}