package ru.ncband.web.client.widgets.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.ncband.web.client.events.LogOutEvent;
import ru.ncband.web.client.widgets.menu.calendar.UiCalendar;

public class UiMainSpace extends Composite {
    interface UiMainSpaceUiBinder extends UiBinder<HTMLPanel, UiMainSpace> {
    }

    private static UiMainSpaceUiBinder ourUiBinder = GWT.create(UiMainSpaceUiBinder.class);
    private EventBus eventBus = null;

    @UiField
    VerticalPanel left;

    @UiField
    VerticalPanel right;

    @UiHandler("exit")
    void doExit(ClickEvent event) {
        LogOutEvent logOutEvent = GWT.create(LogOutEvent.class);
        eventBus.fireEvent(logOutEvent);
    }

    @UiHandler("tasks")
    void chooseTask(ClickEvent event) {

    }

    @UiHandler("settings")
    void changeSettings(ClickEvent event){

    }


    public UiMainSpace(EventBus bus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;

        UiCalendar calendar = new UiCalendar();
        right.add(calendar);
    }
}