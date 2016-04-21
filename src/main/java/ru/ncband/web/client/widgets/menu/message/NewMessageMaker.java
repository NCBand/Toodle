package ru.ncband.web.client.widgets.menu.message;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.events.OutEvent;
import ru.ncband.web.client.services.MessageService;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.properties.BasicProperty;

import java.util.Date;

public class NewMessageMaker extends Composite {
    interface NewMessageMakerUiBinder extends UiBinder<HTMLPanel, NewMessageMaker> {
    }

    private static NewMessageMakerUiBinder ourUiBinder = GWT.create(NewMessageMakerUiBinder.class);

    private EventBus eventBus;
    private boolean done = false;

    @UiField
    VerticalPanel date_field;
    private DateBox dateBox;

    @UiField
    TextArea message;

    @UiField
    Label error;

    @UiHandler("back")
    void back(ClickEvent event){
        clear();
        OutEvent outEvent = new OutEvent();
        eventBus.fireEvent(outEvent);
    }

    @UiHandler("create")
    void createMessage(ClickEvent event){
        MessageService messageService = GWT.create(MessageService.class);
        String dateString = DateTimeFormat.getFormat("dd"+ BasicProperty.dateSeparator()+
                "MM"+ BasicProperty.dateSeparator() +
                "yyyy").format(dateBox.getValue());
        messageService.setMessage(dateString, message.getText(), new MethodCallback<Status>() { //// TODO: 21.04.2016  
            @Override
            public void onFailure(Method method, Throwable throwable) {
                error.setText("Server error");
                done = false;
            }

            @Override
            public void onSuccess(Method method, Status status) {
                error.setText(status.getMsg());
                done = status.getMsg().equals(BasicProperty.done());
            }
        });

        if(done) {
            clear();
            OutEvent outEvent = new OutEvent();
            eventBus.fireEvent(outEvent);
        }
    }

    public NewMessageMaker(EventBus bus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = bus;

        dateBox = new DateBox();
        DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd"+ BasicProperty.dateSeparator()+
                "MM"+ BasicProperty.dateSeparator() +
                "yyyy");
        dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
        dateBox.setValue(new Date());
        date_field.add(dateBox);
    }

    private void clear(){
        dateBox.setValue(new Date());
        error.setText("");
        message.setText("");
    }
}