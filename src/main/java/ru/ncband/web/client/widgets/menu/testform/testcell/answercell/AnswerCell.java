package ru.ncband.web.client.widgets.menu.testform.testcell.answercell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ru.ncband.web.shared.classes.Answers;

public class AnswerCell extends Composite {
    interface AnswerCellUiBinder extends UiBinder<HTMLPanel, AnswerCell> {
    }

    private static AnswerCellUiBinder ourUiBinder = GWT.create(AnswerCellUiBinder.class);

    public AnswerCell() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void check(Answers answers){

    }
}