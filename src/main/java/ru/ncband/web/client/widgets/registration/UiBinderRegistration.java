package ru.ncband.web.client.widgets.registration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.events.LogOutEvent;
import ru.ncband.web.client.events.RegistrationEvent;
import ru.ncband.web.shared.classes.UserForm;
import ru.ncband.web.shared.RegularExpressions;

public class UiBinderRegistration extends Composite {
    interface UiBinderRegistrationUiBinder extends UiBinder<HTMLPanel, UiBinderRegistration> {
    }

    private static UiBinderRegistrationUiBinder ourUiBinder = GWT.create(UiBinderRegistrationUiBinder.class);

    private EventBus eventBus;

    private String firstPassword;

    @UiField
    TextBox firstNameBox;
    private boolean firstname = false;
    @UiField
    TextBox secondNameBox;
    private boolean secondname = false;
    @UiField
    TextBox loginBox;
    private boolean login = false;
    @UiField
    TextBox email;
    private boolean mail = false;
    @UiField
    TextBox ageBox;
    private boolean age = false;
    @UiField
    PasswordTextBox passwordBox;
    private boolean password = false;
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
    @UiField
    Label mainError;

    @UiHandler("loginButton")
    void doClickSubmit(ClickEvent event) {
        if(passwordBox.getValue().equals(passwordRepeatBox.getValue()) && password && login
                && firstname && secondname && age && mail) {
            RegistrationEvent registrationEvent = new RegistrationEvent();
            UserForm userForm = new UserForm();

            userForm.setLogin(loginBox.getValue());
            userForm.setPassword(passwordBox.getValue());
            userForm.setAge(ageBox.getValue());
            userForm.setSex(sexBox.getSelectedValue());
            userForm.setFirstname(firstNameBox.getValue());
            userForm.setLastname(secondNameBox.getValue());
            userForm.setMail(email.getValue());

            registrationEvent.setForm(userForm);
            eventBus.fireEvent(registrationEvent);
        }else {
            mainError.setText("Not valid form");
        }
    }

    @UiHandler("backButton")
    void doBack(ClickEvent event){
        LogOutEvent logOutEvent = new LogOutEvent();
        clear();
        eventBus.fireEvent(logOutEvent);
    }

    @UiHandler("firstNameBox")
    void checkFirstNameBox(ValueChangeEvent<String> event) {
        if (RegularExpressions.test(RegularExpressions.NAME, event.getValue())) {
            statusFirstName.setText("is Ok");
            firstname = true;
        } else {
            statusFirstName.setText(RegularExpressions.REQUIREMENT_FOR_NAME);
            firstname = false;
        }
    }

    @UiHandler("secondNameBox")
    void checkSecondNameBox(ValueChangeEvent<String> event) {
        if (RegularExpressions.test(RegularExpressions.NAME, event.getValue())) {
            statusSecondName.setText("is Ok");
            secondname = true;
        } else {
            statusSecondName.setText(RegularExpressions.REQUIREMENT_FOR_NAME);
            secondname = false;
        }
    }

    @UiHandler("loginBox")
    void checkLogin(ValueChangeEvent<String> event) {
        if (RegularExpressions.test(RegularExpressions.LOGIN, event.getValue())) {
            errorLogin.setText("is Ok");
            login = true;
        } else {
            errorLogin.setText(RegularExpressions.REQUIREMENT_FOR_LOGIN);
            login = false;
        }
    }

    @UiHandler("ageBox")
    void checkAge(ValueChangeEvent<String> event) {
        if (RegularExpressions.test(RegularExpressions.AGE, event.getValue())) {
            errorAge.setText("is Ok");
            age = true;
        } else {
            errorAge.setText(RegularExpressions.REQUIREMENT_FOR_AGE);
            age = false;
        }
    }

    @UiHandler("passwordBox")
    void checkPassword(ValueChangeEvent<String> event) {
        firstPassword = event.getValue();
        if (RegularExpressions.test(RegularExpressions.PASSWORD, firstPassword)) {
            errorPassword.setText("is Ok");
            password = true;
        } else {
            errorPassword.setText(RegularExpressions.REQUIREMENT_FOR_PASSWORD);
            password = false;
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

    public void setMainError(String msg){
        mainError.setText(msg);
    }

    public void clear(){
        firstNameBox.setValue("");
        secondNameBox.setValue("");
        loginBox.setValue("");
        passwordBox.setValue("");
        passwordRepeatBox.setValue("");
        email.setValue("");
        ageBox.setValue("");

        firstname = false;
        secondname = false;
        login = false;
        password = false;
        mail = false;
        age = false;
    }
}