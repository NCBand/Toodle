package ru.ncband.web.client.widgets.menu.testform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.OutEvent;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.client.widgets.menu.testform.testcell.TestCell;
import ru.ncband.web.shared.classes.Answers;
import ru.ncband.web.shared.classes.Ids;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.properties.LessonProperty;

import java.util.ArrayList;
import java.util.List;

public class UiTestForm extends Composite{
    interface UiBinderLoginUiBinder extends UiBinder<HTMLPanel, UiTestForm> {
    }

    private static UiBinderLoginUiBinder ourUiBinder = GWT.create(UiBinderLoginUiBinder.class);

    private EventBus eventbus = null;

    @UiField
    Label task;

    @UiField
    VerticalPanel tasks;

    @UiField
    Label error;

    @UiHandler("done")
    void doClickDone(ClickEvent event){
        Answers answers = new Answers();
        List<String> ids = new ArrayList<String>();
        List<String> rights = new ArrayList<String>();

        answers.setRights(rights);
        answers.setIds(ids);
        for (Widget widget:
             tasks) {
                ((TestCell)widget).check(answers);
        }

        TaskService taskService = GWT.create(TaskService.class);
        taskService.check(answers, new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                error.setText("Server error");
                error.getElement().getStyle().setColor("red");
            }

            @Override
            public void onSuccess(Method method, Status answer) {
                error.setText(answer.getMsg());
                if(answer.getMsg().equals(BasicProperty.done())){
                    error.getElement().getStyle().setColor("green");
                }else {
                    error.getElement().getStyle().setColor("red");
                }
            }
        });
    }

    @UiHandler("back")
    void doClickBack(ClickEvent event){
        clear();
        exit();
    }

    private void exit(){
        OutEvent event = new OutEvent();
        eventbus.fireEvent(event);
    }

    public UiTestForm(EventBus bus){
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventbus = bus;
    }

    public void setTask(String id, final String name){
        clear();
        this.task.setText(name);

        final TaskService taskService = GWT.create(TaskService.class);
        taskService.getIds(id, LessonProperty.task(), new MethodCallback<Ids>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                error.setText("Server error");
                error.getElement().getStyle().setColor("red");
            }

            @Override
            public void onSuccess(Method method, Ids ids) {
                for (String id:
                     ids.getIds()) {
                    TestCell testCell = new TestCell();
                    testCell.setTask(id);
                    tasks.add(testCell);
                }
            }
        });
    }

    private void clear(){
        error.setText("");
        task.setText("");

        for (Widget widget:
             tasks) {
            ((TestCell)widget).clear();
        }

        tasks.clear();
    }
}