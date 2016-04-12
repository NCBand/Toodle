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
import ru.ncband.web.client.events.OutOfTaskEvent;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.shared.Property;
import ru.ncband.web.shared.classes.Answer;
import ru.ncband.web.shared.classes.Task;

import java.util.Iterator;

public class UiTestForm extends Composite{
    interface UiBinderLoginUiBinder extends UiBinder<HTMLPanel, UiTestForm> {
    }

    private static UiBinderLoginUiBinder ourUiBinder = GWT.create(UiBinderLoginUiBinder.class);

    private EventBus eventbus = null;

    @UiField
    Label task;

    @UiField
    Label text;

    @UiField
    VerticalPanel answers;

    @UiField
    Label error;

    @UiHandler("done")
    void doClickDone(ClickEvent event){
        Answer answer = new Answer();
        TaskService taskService = GWT.create(TaskService.class);
        taskService.check(answer, new MethodCallback<Answer>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                error.setText("Server error");
            }

            @Override
            public void onSuccess(Method method, Answer answer) {

            }
        });
    }

    @UiHandler("back")
    void doClickBack(ClickEvent event){
        clear();
        exit();
    }

    private void exit(){
        OutOfTaskEvent event = new OutOfTaskEvent();
        eventbus.fireEvent(event);
    }

    public UiTestForm(EventBus bus){
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventbus = bus;
    }

    public void setTask(Task task, String name){
        this.task.setText(name);
        Iterator<String> list = task.getTexts().iterator();

        text.setText(list.next());
        setAnswers(list, task.getType());
    }

    private void setAnswers(Iterator<String> iterator, int type){
        if(type == Property.typeTest()){

        }
        if(type == Property.typeMultiTest()){

        }
    }

    public void clear(){
        error.setText("");
        task.setText("");
        text.setText("");
        answers.clear();
    }
}