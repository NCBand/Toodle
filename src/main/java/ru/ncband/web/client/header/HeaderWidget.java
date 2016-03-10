package ru.ncband.web.client.header;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MenuItem;

public class HeaderWidget extends Composite {
    interface HeaderUiBinder extends UiBinder<HTMLPanel, HeaderWidget> {
    }

    private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);

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

    public HeaderWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setUserName(String userName) {
        this.userName.setInnerHTML(userName);
    }
}
