package ru.ncband.web.client.widgets.setting;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.events.LogOutEvent;
import ru.ncband.web.shared.RegularExpressions;
import ru.ncband.web.shared.classes.Registration;

public class UiBinderSetting extends Composite {
    interface UiBinderSettingUiBinder extends UiBinder<HTMLPanel, UiBinderSetting> {
    }

    private static UiBinderSettingUiBinder ourUiBinder = GWT.create(UiBinderSettingUiBinder.class);

    private EventBus eventBus;

    private boolean changeFirstName = false;
    private boolean changeSecondName = false;
    private boolean changePassword = false;
    private boolean changeEmail = false;
    private boolean changeAge = false;


    @UiField
    TextBox firstNameBox;
    @UiField
    TextBox secondNameBox;
    @UiField
    TextBox passwordCurrentBox;
    @UiField
    TextBox email;
    @UiField
    TextBox ageBox;
    @UiField
    PasswordTextBox passwordBox;
    @UiField
    PasswordTextBox passwordRepeatBox;
    @UiField
    Label errorCurrentPassword;
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
    Label mainError;

    @UiHandler("saveButton")
    void doClickSubmit(ClickEvent event) {
            Registration setting = new Registration();
//          нужно заполнить все поля в setting данными из сессии
        if (changeFirstName){
        setting.setFirstname(firstNameBox.getValue());}
        if (changeSecondName){
        setting.setLastname(secondNameBox.getValue());}
        // нужно в следующем усовии добавить проверку введенный пароль, совпадает ли он с текущим, если нет то не изменять
        if (changePassword){
            setting.setPassword(passwordBox.getValue());}
        if (changeAge){
            setting.setAge(ageBox.getValue());}
        if (changeEmail){
            setting.setMail(email.getValue());}
        //отправить на сервер
            // registrationEvent.setForm(setting);
            // eventBus.fireEvent(registrationEvent);
    }

    @UiHandler("backButton")
    void doBack(ClickEvent event){
        LogOutEvent logOutEvent = new LogOutEvent();
        eventBus.fireEvent(logOutEvent);
    }

    @UiHandler("firstNameBox")
    void checkFirstNameBox(ValueChangeEvent<String> event) {
        changeFirstName = true;
        if (RegularExpressions.test(RegularExpressions.NAME, event.getValue())) {
            statusFirstName.setText("is Ok");
        } else {
            statusFirstName.setText(RegularExpressions.REQUIREMENT_FOR_NAME);
        }
    }

    @UiHandler("secondNameBox")
    void checkSecondNameBox(ValueChangeEvent<String> event) {
        changeSecondName = true;
        if (RegularExpressions.test(RegularExpressions.NAME, event.getValue())) {
            statusSecondName.setText("is Ok");
        } else {
            statusSecondName.setText(RegularExpressions.REQUIREMENT_FOR_NAME);
        }
    }

    @UiHandler("passwordBox")
    void checkPassword(ValueChangeEvent<String> event) {
        changePassword = true;
        if (RegularExpressions.test(RegularExpressions.PASSWORD, event.getValue())) {
            errorPassword.setText("is Ok");
        } else {
            errorPassword.setText(RegularExpressions.REQUIREMENT_FOR_PASSWORD);
        }
    }

    @UiHandler("passwordRepeatBox")
    void checkRepeatPassword(ValueChangeEvent<String> event) {
        if (!event.getValue().equals(passwordBox.getValue())) {
            errorRepeatPassword.setText("doesn't match");
        } else {
            errorRepeatPassword.setText("is Ok!");
        }
    }

    @UiHandler("ageBox")
    void checkAge(ValueChangeEvent<String> event) {
        changeAge = true;
        if (RegularExpressions.test(RegularExpressions.AGE, event.getValue())) {
            errorAge.setText("is Ok");
        } else {
            errorAge.setText(RegularExpressions.REQUIREMENT_FOR_AGE);
        }
    }

    @UiHandler("email")
    void checkEmail(ValueChangeEvent<String> event){
        changeAge = true;
    }


    public UiBinderSetting(EventBus bus) {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
        this.eventBus = bus;
    }

    public void setMainError(String msg){
        mainError.setText(msg);
    }

    private void clear(){

    }

    public boolean isValidForm(){
        return false;
    }
}