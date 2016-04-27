package ru.ncband.web.client.widgets.menu.lesson.task.answer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.client.widgets.menu.lesson.events.DeleteEvent;
import ru.ncband.web.client.widgets.menu.lesson.events.OneCheckEvent;
import ru.ncband.web.client.widgets.menu.lesson.handlers.OneCheckEventHandler;
import ru.ncband.web.shared.classes.Answer;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.properties.LessonProperty;

public class NewAnswerMaker extends Composite {
    interface NewAnswerMakerUiBinder extends UiBinder<HTMLPanel, NewAnswerMaker> {
    }

    private static NewAnswerMakerUiBinder ourUiBinder = GWT.create(NewAnswerMakerUiBinder.class);
    private EventBus eventBus;
    private String id;
    private String type;

    @UiField
    TextArea text;

    @UiField
    FileUpload upload;

    @UiField
    FormPanel question_image;

    @UiField
    CheckBox right;

    @UiHandler("right")
    void setChecked(ClickEvent event){
        if(type.equals(LessonProperty.typeTest())){
            OneCheckEvent oneCheckEvent = new OneCheckEvent();
            eventBus.fireEvent(oneCheckEvent);
        }
    }

    @UiHandler("upload")
    void setUpload(ChangeEvent event){
        question_image.submit();
    }

    @UiHandler("delete")
    void deleteAnswer(ClickEvent event){
        DeleteEvent deleteEvent = new DeleteEvent();
        deleteEvent.setId(id);
        eventBus.fireEvent(deleteEvent);
    }

    public NewAnswerMaker(EventBus bus, String task_id, String type) {
        initWidget(ourUiBinder.createAndBindUi(this));
        eventBus = bus;
        this.type = type;

        question_image.setEncoding(FormPanel.ENCODING_MULTIPART);
        question_image.setMethod(FormPanel.METHOD_POST);
        question_image.setAction(GWT.getHostPageBaseURL()+"lesson/upload_answer");

        eventBus.addHandler(OneCheckEvent.TYPE, new OneCheckEventHandler() {
            @Override
            public void onOneCheck(OneCheckEvent event) {
                right.setChecked(false);
            }
        });

        TaskService taskService = GWT.create(TaskService.class);
        taskService.newAnswer(task_id, new MethodCallback<String>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, String s) {
                id = s;
                upload.setName(id);
            }
        });
    }

    public String getId(){
        return id;
    }

    void setType(String type){
        this.type = type;
        right.setChecked(false);
    }

    public void clean(){
        text.setValue("");
        eventBus = null;
        type = null;
        id = null;

        TaskService taskService = GWT.create(TaskService.class);
        taskService.deleteAnswer(id, new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Status status) {}
        });
    }

    public void save(){
        Answer answer = new Answer();
        answer.setAnswer(text.getValue());
        answer.setId(id);
        String res = (right.isChecked())? "1": "0";
        answer.setRight(res);

        TaskService taskService = GWT.create(TaskService.class);
        taskService.saveAnswer(answer, new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Status status) {}
        });
    }

    public void end(){
        text.setValue("");
        eventBus = null;
        type = null;
        id = null;
    }
}