package ru.ncband.web.client.widgets.menu;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.LoadDataEvent;
import ru.ncband.web.client.events.LogOutEvent;
import ru.ncband.web.client.events.OutEvent;
import ru.ncband.web.client.handlers.LoadDataEventHandler;
import ru.ncband.web.client.handlers.OutEventHandler;
import ru.ncband.web.client.services.MessageService;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.client.widgets.menu.firstview.UiFisrtView;
import ru.ncband.web.client.widgets.menu.message.NewMessageMaker;
import ru.ncband.web.client.widgets.menu.setting.UiBinderSetting;
import ru.ncband.web.client.widgets.menu.lesson.NewLessonMaker;
import ru.ncband.web.client.widgets.menu.testform.UiTestForm;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.classes.*;

import java.util.Date;
import java.util.Iterator;

public class UiMainSpace extends Composite {
    interface UiMainSpaceUiBinder extends UiBinder<HTMLPanel, UiMainSpace> {
    }

    private static UiMainSpaceUiBinder ourUiBinder = GWT.create(UiMainSpaceUiBinder.class);
    private EventBus eventBus = null;

    private CellList<String> messages = null;

    @UiField
    VerticalPanel left;

    @UiField
    VerticalPanel calendar;

    @UiField
    VerticalPanel message;

    @UiField
    Label date_title;

    @UiField
    MenuBar menu;
    private MenuBar task = null;

    public UiMainSpace(EventBus bus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;

        UiTestForm form = new UiTestForm(bus);
        UiFisrtView firstview = new UiFisrtView();
        UiBinderSetting setting = new UiBinderSetting(bus);
        NewMessageMaker messageMaker = new NewMessageMaker(bus);
        NewLessonMaker taskMaker = new NewLessonMaker(bus);

        form.setVisible(false);
        setting.setVisible(false);
        firstview.setVisible(true);
        messageMaker.setVisible(false);
        taskMaker.setVisible(false);

        left.add(setting);
        left.add(form);
        left.add(firstview);
        left.add(taskMaker);
        left.add(messageMaker);

        final DatePicker datePicker = new DatePicker();
        TextCell textCell = new TextCell();
        messages = new CellList<String>(textCell);

        menu.setAutoOpen(true);
        menu.setAnimationEnabled(true);
        task = new MenuBar();
        task.setAutoOpen(true);
        task.setAnimationEnabled(true);
        menu.addItem(new MenuItem("Your tasks",task));
        menu.addItem(new MenuItem("Add message", new Command() {
            @Override
            public void execute() {
                for (Widget widget:
                     left) {
                    if(widget.getClass().equals(NewMessageMaker.class)){
                        widget.setVisible(true);
                    }else {
                        widget.setVisible(false);
                    }
                }
            }
        }));
        menu.addItem(new MenuItem("Add lesson", new Command() {
            @Override
            public void execute() {
                for (Widget widget:
                left){
                    if(widget.getClass().equals(NewLessonMaker.class)){
                        widget.setVisible(true);
                    }else {
                        widget.setVisible(false);
                    }
                }
            }
        }));
        menu.addItem(new MenuItem("Settings",new Command(){
            @Override
            public void execute() {
                for (Widget widget:
                        left){
                    if(widget.getClass().equals(UiBinderSetting.class)){
                        widget.setVisible(true);
                    }else {
                        widget.setVisible(false);
                    }
                }
            }
        }));
        menu.addItem(new MenuItem("Exit", new Command(){
            @Override
            public void execute() {
                LogOutEvent logOutEvent = new LogOutEvent();
                eventBus.fireEvent(logOutEvent);
            }
        }));

        eventBus.addHandler(LoadDataEvent.TYPE, new LoadDataEventHandler() {
            @Override
            public void onLoadData(LoadDataEvent event) {
                TaskService taskService = GWT.create(TaskService.class);
                taskService.getLabels(new MethodCallback<LessonLabel>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {}

                    @Override
                    public void onSuccess(Method method, LessonLabel lessonLabel) {
                        task.clearItems();
                        Iterator<String> ids = lessonLabel.getIds().iterator();
                        Iterator<String> names = lessonLabel.getLabels().iterator();

                        while(ids.hasNext()){
                            TaskCommand command = new TaskCommand();
                            command.setId(ids.next());
                            String name = names.next();
                            command.setName(name);
                            task.addItem(new MenuItem(name,command));
                        }
                    }
                });

                String dateString = DateTimeFormat.getFormat("dd"+ BasicProperty.dateSeparator()+
                        "MM"+ BasicProperty.dateSeparator() +
                        "yyyy").format(datePicker.getValue());
                date_title.setText(dateString);

                MessageService service = GWT.create(MessageService.class);
                service.getMessages(dateString, new MethodCallback<Messages>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {}

                    @Override
                    public void onSuccess(Method method, Messages res) {
                        messages.setRowCount(res.getMessages().size());
                        messages.setRowData(0, res.getMessages());
                    }
                });
            }
        });

        bus.addHandler(OutEvent.TYPE, new OutEventHandler() {
            @Override
            public void onOutOfTask(OutEvent event) {
                for (Widget widget:
                        left) {
                    if(widget.getClass().equals(UiFisrtView.class)){
                        widget.setVisible(true);
                    }else{
                        widget.setVisible(false);
                    }
                }
            }
        });

        datePicker.setValue(new Date(), true);
        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
                @Override
                public void onValueChange(ValueChangeEvent<Date> event) {
                    String dateString = DateTimeFormat.getFormat("dd"+ BasicProperty.dateSeparator()+
                                                                "MM"+ BasicProperty.dateSeparator() +
                                                                "yyyy").format(event.getValue());
                    date_title.setText(dateString);

                    MessageService service = GWT.create(MessageService.class);
                    service.getMessages(dateString, new MethodCallback<Messages>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {

                        }

                        @Override
                        public void onSuccess(Method method, Messages res) {
                            messages.setRowCount(res.getMessages().size());
                            messages.setRowData(0,res.getMessages());
                        }
                    });
                }
            });


        calendar.add(datePicker);
        message.add(messages);
    }

    private class TaskCommand implements Command{
        private String id = null;
        private String name = null;

        TaskCommand(){}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void execute() {
            TaskService taskService = GWT.create(TaskService.class);
            taskService.getLesson(id, new MethodCallback<Lesson>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {}

                @Override
                public void onSuccess(Method method, Lesson lesson) {
                    for (Widget widget:
                         left) {
                        if(widget.getClass().equals(UiTestForm.class)){
                            widget.setVisible(true);
                            ((UiTestForm)widget).setTask(lesson, name);
                        }else {
                            widget.setVisible(false);
                        }
                    }
                }
            });
        }
    }
}