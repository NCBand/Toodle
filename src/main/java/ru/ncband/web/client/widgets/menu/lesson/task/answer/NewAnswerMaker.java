package ru.ncband.web.client.widgets.menu.lesson.task.answer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.widgets.menu.lesson.events.DeleteEvent;

public class NewAnswerMaker extends Composite {
    interface NewAnswerMakerUiBinder extends UiBinder<HTMLPanel, NewAnswerMaker> {
    }

    private static NewAnswerMakerUiBinder ourUiBinder = GWT.create(NewAnswerMakerUiBinder.class);
    private EventBus eventBus;
    private int id = 0;

    @UiField
    TextArea text;

    @UiField
    FileUpload upload;

    @UiHandler("delete")
    void deleteAnswer(ClickEvent event){
        DeleteEvent deleteEvent = new DeleteEvent();
        deleteEvent.setId(id);
        eventBus.fireEvent(deleteEvent);
    }

    public NewAnswerMaker(EventBus bus, int num) {
        initWidget(ourUiBinder.createAndBindUi(this));
        eventBus = bus;
        this.id = num;
    }

    public int getId(){
        return id;
    }

    public void clean(){
        text.setValue("");
        eventBus = null;
    }
}