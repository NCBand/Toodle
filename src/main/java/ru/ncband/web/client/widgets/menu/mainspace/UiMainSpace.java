package ru.ncband.web.client.widgets.menu.mainspace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created by Дом on 28.03.2016.
 */
public class UiMainSpace extends Composite {
    interface UiMainSpaceUiBinder extends UiBinder<HTMLPanel, UiMainSpace> {
    }

    private static UiMainSpaceUiBinder ourUiBinder = GWT.create(UiMainSpaceUiBinder.class);

    public UiMainSpace() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}