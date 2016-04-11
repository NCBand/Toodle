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
import ru.ncband.web.client.handlers.LoadDataEventHandler;
import ru.ncband.web.client.services.MessageService;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.shared.Property;
import ru.ncband.web.shared.classes.Messages;
import ru.ncband.web.shared.classes.Task;
import ru.ncband.web.shared.classes.TaskLabel;

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
    private MenuBar task;

    public UiMainSpace(EventBus bus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;

        final DatePicker datePicker = new DatePicker();
        TextCell textCell = new TextCell();
        messages = new CellList<String>(textCell);

        menu.setAutoOpen(true);
        menu.setAnimationEnabled(true);
        task = new MenuBar();
        task.setAutoOpen(true);
        task.setAnimationEnabled(true);
        menu.addItem(new MenuItem("Task",task));
        menu.addItem(new MenuItem("Settings",new Command(){
            @Override
            public void execute() {

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
                taskService.getLabels(new MethodCallback<TaskLabel>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {}

                    @Override
                    public void onSuccess(Method method, TaskLabel taskLabel) {
                        task.clearItems();
                        Iterator<Integer> ids = taskLabel.getIds().iterator();
                        Iterator<String> names = taskLabel.getLabels().iterator();

                        while(ids.hasNext()){
                            TaskCommand command = new TaskCommand();
                            command.setId(ids.next());
                            task.addItem(new MenuItem(names.next(),command));
                        }
                    }
                });

                String dateString = DateTimeFormat.getFormat("dd"+ Property.dateSeparator()+
                        "MM"+ Property.dateSeparator() +
                        "yyyy").format(datePicker.getValue());

                MessageService service = GWT.create(MessageService.class);
                service.getMessages(dateString, new MethodCallback<Messages>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, Messages res) {
                        messages.setRowCount(res.getMessages().size());
                        messages.setRowData(0, res.getMessages());
                    }
                });
            }
        });

        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
                @Override
                public void onValueChange(ValueChangeEvent<Date> event) {
                    String dateString = DateTimeFormat.getFormat("dd"+ Property.dateSeparator()+
                                                                "MM"+ Property.dateSeparator() +
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
        datePicker.setValue(new Date(), true);

        calendar.add(datePicker);
        message.add(messages);
    }

    private class TaskCommand implements Command{
        Integer id = null;

        TaskCommand(){}

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public void execute() {
            TaskService taskService = GWT.create(TaskService.class);
            taskService.getTask(id.toString(), new MethodCallback<Task>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {}

                @Override
                public void onSuccess(Method method, Task task) {

                }
            });
        }
    }
}