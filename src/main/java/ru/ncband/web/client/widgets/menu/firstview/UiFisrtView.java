package ru.ncband.web.client.widgets.menu.firstview;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

public class UiFisrtView extends Composite {
    interface UiFisrtViewUiBinder extends UiBinder<HTMLPanel, UiFisrtView> {
    }

    private static UiFisrtViewUiBinder ourUiBinder = GWT.create(UiFisrtViewUiBinder.class);

    @UiField
    Label message;

    public UiFisrtView() {
        initWidget(ourUiBinder.createAndBindUi(this));
        message.setText("Good work. Nice day.");
    }
}