package ru.ncband.web.client.widgets.menu.testform.testcell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.client.widgets.menu.testform.testcell.answercell.AnswerCell;
import ru.ncband.web.shared.classes.Answers;
import ru.ncband.web.shared.classes.Task;

public class TestCell extends Composite {
    interface TestCellUiBinder extends UiBinder<HTMLPanel, TestCell> {
    }

    private static TestCellUiBinder ourUiBinder = GWT.create(TestCellUiBinder.class);

    @UiField
    Label question;

    @UiField
    VerticalPanel answers;

    @UiField
    Image image;

    public TestCell() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setTask(String id){
        TaskService taskService = GWT.create(TaskService.class);
        taskService.getTask(id, new MethodCallback<Task>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Task task) {

            }
        });
    }

    public void check(Answers answers){
        for (Widget widget:
             this.answers) {
            ((AnswerCell)widget).check(answers);
        }
    }

    public void clear(){

    }
}