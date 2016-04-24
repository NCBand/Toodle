package ru.ncband.web.client.widgets.menu.lesson.task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.widgets.menu.lesson.events.DeleteEvent;
import ru.ncband.web.client.widgets.menu.lesson.handlers.DeleteEventHandler;
import ru.ncband.web.client.widgets.menu.lesson.task.answer.NewAnswerMaker;
import ru.ncband.web.shared.properties.LessonProperty;

public class NewTaskMaker extends Composite {
    interface NewTaskMakerUI extends UiBinder<HTMLPanel, NewTaskMaker> {
    }

    private static NewTaskMakerUI ourUiBinder = GWT.create(NewTaskMakerUI.class);
    private EventBus eventBus;
    private EventBus answerBus;
    private int id = 0;
    private String type;

    @UiField
    TextArea question;

    @UiField
    FileUpload question_image;

    @UiField
    VerticalPanel answers;
    private int count = 0;

    @UiField
    Button add;

    @UiHandler("text")
    void setText(ClickEvent event){
        answers.setVisible(false);
        add.setVisible(false);
        type = LessonProperty.typeText();
    }

    @UiHandler("test")
    void setTest(ClickEvent event){
        answers.setVisible(true);
        add.setVisible(true);
        type = LessonProperty.typeTest();
    }

    @UiHandler("multi")
    void setMulti(ClickEvent event){
        answers.setVisible(true);
        add.setVisible(true);
        type = LessonProperty.typeMultiTest();
    }

    @UiHandler("add")
    void addAnswer(ClickEvent event){
        NewAnswerMaker newAnswerMaker = new NewAnswerMaker(answerBus, count);
        count++;
        answers.add(newAnswerMaker);
    }

    @UiHandler("delete")
    void deleteTask(ClickEvent event){
        DeleteEvent deleteEvent = new DeleteEvent();
        deleteEvent.setId(id);
        eventBus.fireEvent(deleteEvent);
    }

    public NewTaskMaker(EventBus bus, int id) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;
        this.id = id;

        answerBus = new SimpleEventBus();
        answerBus.addHandler(DeleteEvent.TYPE, new DeleteEventHandler() {
            @Override
            public void onDeleteAnswer(DeleteEvent event) {
                for (Widget widget:
                     answers) {
                    if(((NewAnswerMaker)widget).getId() == event.getId()){
                        ((NewAnswerMaker)widget).clean();
                        answers.remove(widget);
                        break;
                    }
                }
            }
        });
    }

    public int getId(){
        return id;
    }

    public void clean(){
        question.setText("");

        for (Widget widget:
             answers) {
            ((NewAnswerMaker)widget).clean();
            widget.removeFromParent();
        }

        answers.clear();
        answerBus = null;
        eventBus = null;
    }

    public void save(){

    }
}