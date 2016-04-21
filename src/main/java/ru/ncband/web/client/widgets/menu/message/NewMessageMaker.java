package ru.ncband.web.client.widgets.menu.message;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class NewMessageMaker extends Composite {
    interface NewMessageMakerUiBinder extends UiBinder<HTMLPanel, NewMessageMaker> {
    }

    private static NewMessageMakerUiBinder ourUiBinder = GWT.create(NewMessageMakerUiBinder.class);

    private EventBus eventBus;


    public NewMessageMaker(EventBus bus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;
    }
}