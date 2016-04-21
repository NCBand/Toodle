package ru.ncband.web.client.widgets.menu.testform.testcell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.classes.Answer;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.classes.Task;
import ru.ncband.web.shared.properties.LessonProperty;

import java.util.Iterator;

public class TestCell extends Composite {
    interface TestCellUiBinder extends UiBinder<HTMLPanel, TestCell> {
    }

    private static TestCellUiBinder ourUiBinder = GWT.create(TestCellUiBinder.class);

    @UiField
    Label question;
    private String id;
    private boolean res;

    @UiField
    VerticalPanel answers;

    public TestCell() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setTask(String task){
        TaskService taskService = GWT.create(TaskService.class);
        taskService.getTask(task, new MethodCallback<Task>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Task task1) {
                Iterator<String> iterator = task1.getTexts().iterator();
                question.setText(iterator.next());
                id = task1.getId();

                if(task1.getType().equals(LessonProperty.typeTest())){
                    while(iterator.hasNext()){
                        RadioButton radioButton = new RadioButton("answer", iterator.next());
                        radioButton.setEnabled(true);
                        answers.add(radioButton);
                    }
                }
                if(task1.getType().equals(LessonProperty.typeMultiTest())){
                    while(iterator.hasNext()){
                        CheckBox checkBox = new CheckBox(iterator.next());
                        checkBox.setEnabled(true);
                        answers.add(checkBox);
                    }
                }
            }
        });
    }

    public boolean check(){
        Answer answer = new Answer();
        answer.setId(id);
        answer.setAnswer(0);

        TaskService taskService = GWT.create(TaskService.class);
        taskService.check(answer, new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                res = false;
            }

            @Override
            public void onSuccess(Method method, Status answer) {
                if(answer.getMsg().equals(BasicProperty.fault())){
                    res = false;
                }else {
                    res = true;
                }
            }
        });
        return res;
    }
}