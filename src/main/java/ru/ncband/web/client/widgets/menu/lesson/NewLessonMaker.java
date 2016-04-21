package ru.ncband.web.client.widgets.menu.lesson;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class NewLessonMaker extends Composite {
    interface NewTaskMakerUiBinder extends UiBinder<HTMLPanel, NewLessonMaker> {
    }

    private static NewTaskMakerUiBinder ourUiBinder = GWT.create(NewTaskMakerUiBinder.class);
    private EventBus eventBus;

    public NewLessonMaker(EventBus bus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;
    }
}