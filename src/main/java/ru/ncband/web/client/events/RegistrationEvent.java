package ru.ncband.web.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.ncband.web.client.handlers.RegistrationHandler;
import ru.ncband.web.shared.classes.Registration;

public class RegistrationEvent extends GwtEvent<RegistrationHandler> {

    public static Type<RegistrationHandler> TYPE = new Type<RegistrationHandler>();

    private Registration form = null;

    public RegistrationEvent(){}

    public Registration getForm() {
        return form;
    }

    public void setForm(Registration form) {
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
