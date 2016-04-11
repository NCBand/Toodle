package ru.ncband.web.client.widgets.menu.firstview;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created by Дом on 11.04.2016.
 */
public class UiFisrtView extends Composite {
    interface UiFisrtViewUiBinder extends UiBinder<HTMLPanel, UiFisrtView> {
    }

    private static UiFisrtViewUiBinder ourUiBinder = GWT.create(UiFisrtViewUiBinder.class);

    public UiFisrtView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}