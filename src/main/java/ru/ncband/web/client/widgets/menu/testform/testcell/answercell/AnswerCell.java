package ru.ncband.web.client.widgets.menu.testform.testcell.answercell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created by Дом on 26.04.2016.
 */
public class AnswerCell extends Composite {
    interface AnswerCellUiBinder extends UiBinder<HTMLPanel, AnswerCell> {
    }

    private static AnswerCellUiBinder ourUiBinder = GWT.create(AnswerCellUiBinder.class);

    public AnswerCell() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}