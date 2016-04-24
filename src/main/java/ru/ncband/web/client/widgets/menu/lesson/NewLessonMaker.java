package ru.ncband.web.client.widgets.menu.lesson;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.events.OutEvent;
import ru.ncband.web.client.widgets.menu.lesson.events.DeleteEvent;
import ru.ncband.web.client.widgets.menu.lesson.handlers.DeleteEventHandler;
import ru.ncband.web.client.widgets.menu.lesson.task.NewTaskMaker;

public class NewLessonMaker extends Composite {
    interface NewTaskMakerUiBinder extends UiBinder<HTMLPanel, NewLessonMaker> {
    }

    private static NewTaskMakerUiBinder ourUiBinder = GWT.create(NewTaskMakerUiBinder.class);
    private EventBus eventBus;
    private EventBus taskBus;

    @UiField
    TextBox name;

    @UiField
    Label error;

    @UiField
    VerticalPanel tasks;
    private int count = 0;


    @UiHandler("back")
    void back(ClickEvent event){
        name.setText("");
        for (Widget widget:
             tasks) {
            ((NewTaskMaker)widget).clean();
        }
        tasks.clear();

        OutEvent outEvent = new OutEvent();
        eventBus.fireEvent(outEvent);
    }

    @UiHandler("add")
    void addTask(ClickEvent event){
        NewTaskMaker newTaskMaker = new NewTaskMaker(taskBus, count);
        count++;
        tasks.add(newTaskMaker);
    }

    @UiHandler("save")
    void save(ClickEvent event){

    }

    public NewLessonMaker(EventBus bus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;
        taskBus = new SimpleEventBus();

        taskBus.addHandler(DeleteEvent.TYPE, new DeleteEventHandler() {
            @Override
            public void onDeleteAnswer(DeleteEvent event) {
                for (Widget widget:
                        tasks) {
                    if(((NewTaskMaker)widget).getId() == event.getId()){
                        ((NewTaskMaker)widget).clean();
                        tasks.remove(widget);
                        break;
                    }
                }
            }
        });
    }
}