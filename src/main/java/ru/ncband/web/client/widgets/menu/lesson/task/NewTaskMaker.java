package ru.ncband.web.client.widgets.menu.lesson.task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.client.widgets.menu.lesson.events.DeleteEvent;
import ru.ncband.web.client.widgets.menu.lesson.handlers.DeleteEventHandler;
import ru.ncband.web.client.widgets.menu.lesson.task.answer.NewAnswerMaker;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.classes.Task;
import ru.ncband.web.shared.properties.LessonProperty;

public class NewTaskMaker extends Composite {
    interface NewTaskMakerUI extends UiBinder<HTMLPanel, NewTaskMaker> {
    }

    private static NewTaskMakerUI ourUiBinder = GWT.create(NewTaskMakerUI.class);
    private EventBus eventBus;
    private EventBus answerBus;
    private String type;
    private String id;

    @UiField
    TextArea question;

    @UiField
    FileUpload upload;

    @UiField
    FormPanel question_image;

    @UiField
    VerticalPanel answers;

    @UiField
    Button add;

    @UiField
    RadioButton text;
    @UiField
    RadioButton test;
    @UiField
    RadioButton multi;

    @UiHandler("test")
    void setTest(ClickEvent event){
        add.setVisible(true);
        type = LessonProperty.typeTest();
        answers.setVisible(true);

        for(Widget widget:
        answers){
            ((NewAnswerMaker)widget).setType(type);
        }
    }

    @UiHandler("text")
    void setText(ClickEvent clickEvent){
        add.setVisible(false);
        type = LessonProperty.typeTest();
        answers.setVisible(false);

        for(Widget widget:
                answers){
            ((NewAnswerMaker)widget).setType(type);
        }
    }

    @UiHandler("multi")
    void setMulti(ClickEvent event){
        add.setVisible(true);
        type = LessonProperty.typeMultiTest();
        answers.setVisible(true);

        for(Widget widget:
                answers){
            ((NewAnswerMaker)widget).setType(type);
        }
    }

    @UiHandler("add")
    void addAnswer(ClickEvent event){
        NewAnswerMaker newAnswerMaker = new NewAnswerMaker(answerBus, id, type);
        answers.add(newAnswerMaker);
    }

    @UiHandler("delete")
    void deleteTask(ClickEvent event){
        DeleteEvent deleteEvent = new DeleteEvent();
        deleteEvent.setId(id);
        eventBus.fireEvent(deleteEvent);
    }

    @UiHandler("upload")
    void saveImage(ChangeEvent event){
        question_image.submit();
    }

    public NewTaskMaker(EventBus bus, String lesson_id) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;
        TaskService taskService = GWT.create(TaskService.class);
        taskService.newTask(lesson_id, new MethodCallback<String>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, String s) {
                id = s;

                text.setName(s);
                test.setName(s);
                multi.setName(s);
                upload.setName(id);
            }
        });

        answerBus = new SimpleEventBus();
        answerBus.addHandler(DeleteEvent.TYPE, new DeleteEventHandler() {
            @Override
            public void onDeleteAnswer(DeleteEvent event) {
                for (Widget widget:
                     answers) {
                    if(((NewAnswerMaker)widget).getId().equals(event.getId())){
                        ((NewAnswerMaker)widget).clean();
                        answers.remove(widget);
                        break;
                    }
                }
            }
        });

        question_image.setEncoding(FormPanel.ENCODING_MULTIPART);
        question_image.setMethod(FormPanel.METHOD_POST);
        question_image.setAction(GWT.getHostPageBaseURL()+"lesson/upload_task");
    }

    public String getId(){
        return id;
    }

    public void clean(){
        question.setText("");

        for (Widget widget:
             answers) {
            ((NewAnswerMaker)widget).clean();
            widget.removeFromParent();
        }

        TaskService taskService = GWT.create(TaskService.class);
        taskService.deleteTask(id, new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Status status) {
                answerBus = null;
                eventBus = null;
                id = null;

                for (Widget widget:
                        answers) {
                    ((NewAnswerMaker) widget).clean();
                }
                answers.clear();
            }
        });
    }

    public void save(){
        Task task = new Task();
        task.setId(id);
        task.setQuestion(question.getText());
        task.setType(type);

        TaskService taskService = GWT.create(TaskService.class);
        taskService.saveTask(task, new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Status status) {}
        });

        for (Widget widget:
                answers) {
            if (type.equals(LessonProperty.typeText())) {
                ((NewAnswerMaker)widget).clean();
                widget.removeFromParent();
            }else {
                ((NewAnswerMaker)widget).save();
            }
        }
        end();
    }

    public void end(){
        question.setText("");

        for (Widget widget:
                answers) {
            ((NewAnswerMaker)widget).end();
            widget.removeFromParent();
        }

        id = null;
        answers.clear();
        answerBus = null;
        eventBus = null;
    }
}