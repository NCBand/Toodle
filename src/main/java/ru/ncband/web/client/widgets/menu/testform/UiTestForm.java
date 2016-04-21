package ru.ncband.web.client.widgets.menu.testform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.events.OutEvent;
import ru.ncband.web.client.widgets.menu.testform.testcell.TestCell;
import ru.ncband.web.shared.classes.Lesson;

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
        boolean res = true;

        for (Widget widget:
             tasks) {
            if(widget.getClass().equals(TestCell.class)){
                res = res && ((TestCell)widget).check();
            }
        }

        if(res){
            error.setText("done");
        }else {
            error.setText("wrong");
        }
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

    public void setTask(Lesson task, String name){
        tasks.clear();
        this.task.setText(name);
        List<String> ids = task.getTasks();

        for (String example:
             ids) {
            TestCell cell = new TestCell();
            cell.setTask(example);
            tasks.add(cell);
        }
    }

    private void clear(){
        error.setText("");
        task.setText("");
        tasks.clear();
    }
}