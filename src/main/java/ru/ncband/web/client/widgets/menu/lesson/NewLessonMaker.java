package ru.ncband.web.client.widgets.menu.lesson;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.LoadDataEvent;
import ru.ncband.web.client.events.OutEvent;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.client.widgets.menu.lesson.events.DeleteEvent;
import ru.ncband.web.client.widgets.menu.lesson.handlers.DeleteEventHandler;
import ru.ncband.web.client.widgets.menu.lesson.task.NewTaskMaker;
import ru.ncband.web.shared.classes.Lesson;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.classes.Task;

import java.util.ArrayList;

public class NewLessonMaker extends Composite {
    interface NewTaskMakerUiBinder extends UiBinder<HTMLPanel, NewLessonMaker> {
    }

    private static NewTaskMakerUiBinder ourUiBinder = GWT.create(NewTaskMakerUiBinder.class);
    private EventBus eventBus;
    private EventBus taskBus;
    private String id;

    @UiField
    TextBox name;

    @UiField
    Label error;

    @UiField
    VerticalPanel tasks;

    @UiHandler("back")
    void back(ClickEvent event){
        name.setText("");
        for (Widget widget:
             tasks) {
            ((NewTaskMaker)widget).clean();
        }
        tasks.clear();

        TaskService taskService = GWT.create(TaskService.class);
        taskService.delete(id, new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Status status) {
                for (Widget widget:
                        tasks) {
                    ((NewTaskMaker)widget).clean();
                }
                OutEvent outEvent = new OutEvent();
                eventBus.fireEvent(outEvent);
            }
        });
    }

    @UiHandler("add")
    void addTask(ClickEvent event){
        NewTaskMaker newTaskMaker = new NewTaskMaker(taskBus, id);
        tasks.add(newTaskMaker);
    }

    @UiHandler("save")
    void save(ClickEvent event){
        Lesson lesson = new Lesson();
        lesson.setId(id);
        lesson.setName(name.getText());

        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        for(Widget widget:
                tasks){
            taskArrayList.add(((NewTaskMaker)widget).save());
            ((NewTaskMaker)widget).end();
        }
        lesson.setTasks(taskArrayList);

        TaskService taskService = GWT.create(TaskService.class);
        taskService.saveLesson(lesson, new MethodCallback<Status>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                LoadDataEvent loadDataEvent = new LoadDataEvent();
                eventBus.fireEvent(loadDataEvent);
                OutEvent outEvent = new OutEvent();
                eventBus.fireEvent(outEvent);
            }

            @Override
            public void onSuccess(Method method, Status status) {
                LoadDataEvent loadDataEvent = new LoadDataEvent();
                eventBus.fireEvent(loadDataEvent);
                OutEvent outEvent = new OutEvent();
                eventBus.fireEvent(outEvent);
            }
        });

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
                    if(((NewTaskMaker)widget).getId().equals(event.getId())){
                        ((NewTaskMaker)widget).clean();
                        tasks.remove(widget);
                        break;
                    }
                }
            }
        });

        TaskService taskService = GWT.create(TaskService.class);
        taskService.newLesson(new MethodCallback<String>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, String s) {
                id = s;
            }
        });
    }
}