package ru.ncband.web.client.widgets.menu.setting;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.LogOutEvent;
import ru.ncband.web.client.events.OutEvent;
import ru.ncband.web.client.services.UserService;
import ru.ncband.web.shared.RegularExpressions;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.classes.UserForm;
import ru.ncband.web.shared.properties.BasicProperty;

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

    private boolean dlt = false;

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
        UserForm setting = new UserForm();
        if (changeFirstName) {
            setting.setFirstname(firstNameBox.getValue());
        }
        if (changeSecondName) {
            setting.setLastname(secondNameBox.getValue());
        }

        if (changePassword) {
            if(!passwordBox.getValue().equals(passwordRepeatBox.getValue())){
                mainError.setText("Passwords aren't equal");
                return;
            }
            setting.setPassword(passwordBox.getValue());
        }
        if (changeAge) {
            setting.setAge(ageBox.getValue());
        }
        if (changeEmail) {
            setting.setMail(email.getValue());
        }

        UserService userService = GWT.create(UserService.class);
        userService.changeUserData(setting, new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                mainError.setText("Server error");
            }

            @Override
            public void onSuccess(Method method, Status status) {
                mainError.setText(status.getMsg());
            }
        });


        OutEvent outEvent = new OutEvent();
        clear();
        eventBus.fireEvent(outEvent);
    }

    @UiHandler("delete")
    void delete(ClickEvent event){
        UserService userService = GWT.create(UserService.class);
        userService.deleteUser(new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                mainError.setText("Server Error");
                dlt = false;
            }

            @Override
            public void onSuccess(Method method, Status status) {
                if(status.getMsg().equals(BasicProperty.done())){
                    dlt = true;
                }else {
                    mainError.setText("Server Error");
                    dlt = false;
                }
            }
        });

        if (dlt) {
            LogOutEvent logOutEvent = new LogOutEvent();
            eventBus.fireEvent(logOutEvent);
        }
    }

    @UiHandler("backButton")
    void doBack(ClickEvent event){
        OutEvent outEvent = new OutEvent();
        clear();
        eventBus.fireEvent(outEvent);
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

    private void clear(){
        firstNameBox.setValue("");
        secondNameBox.setValue("");
        passwordBox.setValue("");
        passwordRepeatBox.setValue("");
        email.setValue("");
        ageBox.setValue("");

        mainError.setText("");

        changeAge = false;
        changeSecondName = false;
        changeFirstName = false;
        changeEmail = false;
        changePassword = false;
    }
}