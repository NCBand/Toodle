package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.RegistrationHandler;
import ru.ncband.web.shared.classes.UserForm;

public class RegistrationEvent extends GwtEvent<RegistrationHandler> {

    public static Type<RegistrationHandler> TYPE = new Type<RegistrationHandler>();

    private UserForm form = null;

    public RegistrationEvent(){}

    public UserForm getForm() {
        return form;
    }

    public void setForm(UserForm form) {
        this.form = form;
    }

    @Override
    public Type<RegistrationHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RegistrationHandler handler) {
        handler.addUser(this);
    }
}
