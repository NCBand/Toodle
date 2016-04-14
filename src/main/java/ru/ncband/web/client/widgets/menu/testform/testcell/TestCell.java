package ru.ncband.web.client.widgets.menu.testform.testcell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.ncband.web.shared.Property;
import ru.ncband.web.shared.classes.Task;

import java.util.Iterator;


public class TestCell extends Composite {
    interface TestCellUiBinder extends UiBinder<HTMLPanel, TestCell> {
    }

    private static TestCellUiBinder ourUiBinder = GWT.create(TestCellUiBinder.class);

    @UiField
    Label question;

    @UiField
    VerticalPanel answers;

    public TestCell() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setTask(Task task){
        Iterator<String> iterator = task.getTexts().iterator();
        question.setText(iterator.next());

        while(iterator.hasNext() && task.getType() != Property.typeText()){
            int i = 0;
        }
    }
}