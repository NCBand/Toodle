package ru.ncband.web.client.widgets.menu.testform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UiTestForm extends Composite{
    interface UiBinderLoginUiBinder extends UiBinder<HTMLPanel, UiTestForm> {
    }

    private static UiBinderLoginUiBinder ourUiBinder = GWT.create(UiBinderLoginUiBinder.class);

    @UiField
    Label text;

    @UiField
    VerticalPanel answers;

    @UiHandler("done")
    void doClickDone(ClickEvent event){

    }

    @UiHandler("back")
    void doClickBack(ClickEvent event){

    }

    public UiTestForm(){

    }
}