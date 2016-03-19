package ru.ncband.web.client.widgets.header;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MenuItem;

public class HeaderWidget extends Composite {
    interface HeaderUiBinder extends UiBinder<HTMLPanel, HeaderWidget> {
    }

    private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);
    private SimpleEventBus eventBus;

    @UiField
    SpanElement userName;
    @UiField
    MenuItem homePage;
    @UiField
    MenuItem profile;
    @UiField
    MenuItem settings;
    @UiField
    MenuItem signOut;

    public HeaderWidget(SimpleEventBus bus) {
        initWidget(uiBinder.createAndBindUi(this));
        this.eventBus = bus;
    }

    public void setUserName(String userName) {
        this.userName.setInnerHTML(userName);
    }
}
