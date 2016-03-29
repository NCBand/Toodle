package ru.ncband.web.client.widgets.menu.workspace.workmenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class HeaderWidget extends Composite {
    interface HeaderUiBinder extends UiBinder<HTMLPanel, HeaderWidget> {
    }

    private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);
    private SimpleEventBus eventBus;


    public HeaderWidget(SimpleEventBus bus) {
        initWidget(uiBinder.createAndBindUi(this));
        this.eventBus = bus;
    }
}
