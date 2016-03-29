package ru.ncband.web.client.widgets.menu.workspace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class UiWorkSpace extends Composite {
    interface UiWorkSpaceUiBinder extends UiBinder<HTMLPanel, UiWorkSpace> {
    }

    private static UiWorkSpaceUiBinder ourUiBinder = GWT.create(UiWorkSpaceUiBinder.class);

    public UiWorkSpace() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}